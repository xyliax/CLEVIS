package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;
import hk.edu.polyu.comp.comp2021.clevis.view.ClevisIO;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

// TODO: 17/11/2021 format

/**
 * The class for shape management.
 * <p>This class is responsible for all the actions on any shape.</p>
 * <p>The only access to shape classes, and the connection between shape and users.</p>
 * <p>It sets some basic rules of shape-relevent actions</p>
 *
 * @see Shape
 * @see Clevis
 */
public class ShapeManager implements Serializable {

	/**
	 * The default value for point radius used in picking.
	 */
	public static final float DEF_PD = 0.05f;

	/**
	 * The maximum bound of the X-coordinate.
	 */
	protected static final float CX_MAX_VALUE = Float.MAX_VALUE;

	/**
	 * The minimum bound of the X-coordinate.
	 */
	protected static final float CX_MIN_VALUE = -Float.MAX_VALUE;

	/**
	 * The maximum bound of the Y-coordinate.
	 */
	protected static final float CY_MAX_VALUE = Float.MAX_VALUE;

	/**
	 * The minimum bound of the Y-coordinate.
	 */
	protected static final float CY_MIN_VALUE = -Float.MAX_VALUE;
	private static final float pointRadius = DEF_PD;
	private final ClevisIO io;
	private HashMap<String, Shape> shapeStorage;
	private int Z_ORDER;

	/**
	 * Constructor for ShapeManager.
	 * <p>Intialize the shape storage and trashcan; set Z_order to 0.</p>
	 */
	public ShapeManager(ClevisIO clevisIO) {
		shapeStorage = new HashMap<>();
		Z_ORDER = 0;
		io = clevisIO;
	}


	/**
	 * Returns an exact copy of this shape manager.
	 *
	 * @return the exact copy of this shape manager
	 */
	public ShapeManager getClone() {
		Object clone = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this.shapeStorage);
			objectOutputStream.flush();
			objectOutputStream.close();
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
			clone = objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		ShapeManager copy = new ShapeManager(this.io);
		copy.setShapeStorage((HashMap) clone);
		return copy;
	}

	private void setShapeStorage(HashMap<String, Shape> shapeStorage) {
		this.shapeStorage = shapeStorage;
	}

	/**
	 * Creates a new line segments and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when the arguments are illegal
	 * @throws IllegalNameException   when the name has been defined
	 * @see LineSegment#LineSegment(int, String, float, float, float, float)
	 */
	public void createLineSegment(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		float x1_arg = (float) args.get(1), y1_arg = (float) args.get(2),
				x2_arg = (float) args.get(3), y2_arg = (float) args.get(4);

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! '%s' has been defined!", n_arg));

		LineSegment newLineSegment = new LineSegment(Z_ORDER, n_arg, x1_arg, y1_arg, x2_arg, y2_arg);
		shapeStorage.put(newLineSegment.getName(), newLineSegment);
		Z_ORDER++;

	}

	/**
	 * Creates a new circle and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when the arguments are illegal
	 * @throws IllegalNameException   when the name has been defined
	 * @see Circle#Circle(int, String, float, float, float)
	 */
	public void createCircle(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		float x_arg = (float) args.get(1), y_arg = (float) args.get(2),
				r_arg = (float) args.get(3);

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));

		Circle newCircle = new Circle(Z_ORDER, n_arg, x_arg, y_arg, r_arg);
		shapeStorage.put(newCircle.getName(), newCircle);
		Z_ORDER++;
	}

	/**
	 * Creates a new rectangle and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when the arguments are illegal
	 * @throws IllegalNameException   when the name has been defined
	 * @see Rectangle#Rectangle(int, String, float, float, float, float)
	 */
	public void createRectangle(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		float x_arg = (float) args.get(1), y_arg = (float) args.get(2),
				w_arg = (float) args.get(3), h_arg = (float) args.get(4);

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));

		Rectangle newRectangle = new Rectangle(Z_ORDER, n_arg, x_arg, y_arg, w_arg, h_arg);
		shapeStorage.put(newRectangle.getName(), newRectangle);
		Z_ORDER++;
	}

	/**
	 * Creates a new square and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when the arguments are illegal
	 * @throws IllegalNameException   when the name has been defined
	 * @see Square#Square(int, String, float, float, float)
	 */
	public void createSquare(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		float x_arg = (float) args.get(1), y_arg = (float) args.get(2),
				l_arg = (float) args.get(3);

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));

		Square newSquare = new Square(Z_ORDER++, n_arg, x_arg, y_arg, l_arg);
		shapeStorage.put(newSquare.getName(), newSquare);
		Z_ORDER++;
	}

	/**
	 * Creates a new group and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has been defined
	 * @throws InGroupMovementException when attempting to move grouped shapes
	 */
	public void createGroup(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		Object[] ni_args = Arrays.copyOfRange(args.toArray(), 1, args.size());

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));
		if (Arrays.stream(ni_args).anyMatch(ni -> !containsShape((String) ni)))
			throw new IllegalNameException(n_arg + " member list has undefined name!");
		if (Arrays.stream(ni_args).anyMatch(ni -> isGroupedShape((String) ni)))
			throw new InGroupMovementException("In group shape cannot be used directly!");


		GroupShape newGroup = new GroupShape(Z_ORDER++, n_arg);
		shapeStorage.put(newGroup.getName(), newGroup);
		for (Object ni : ni_args)
			newGroup.invite(shapeStorage.get((String) ni));
	}

	/**
	 * Disbands a group and move it from storage to trashcan.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name does not represent a group
	 * @throws InGroupMovementException when attempting to move grouped shapes
	 * @see GroupShape#disband()
	 */
	public void disbandGroup(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (!(shapeStorage.get(n_arg) instanceof GroupShape grouper))
			throw new IllegalNameException(n_arg + " is not a GroupShape!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		grouper.disband();
		shapeStorage.remove(n_arg);
	}

	/**
	 * Deletes a shape and move it from storage to trashcan.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has not been defined
	 * @throws InGroupMovementException when attempting to move grouped shapes
	 */
	public void deleteShape(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);

		if (!containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has not been defined!", n_arg));
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		Shape shapeToBeDeleted = shapeStorage.get(n_arg);
		if (shapeToBeDeleted instanceof GroupShape) {
			for (Shape aMember : ((GroupShape) shapeToBeDeleted).getGroupMembers())
				deleteShape(Collections.singletonList(aMember.getName()));
		}
		shapeStorage.remove(n_arg);
	}

	/**
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when attempting to move out of map
	 * @see #intersect(Shape, Shape)
	 * @see #moveShape(List)
	 */
	public void pickMoveShape(List<Object> args) throws InModelException {
		float x_arg = (float) args.get(0), y_arg = (float) args.get(1),
				dx_arg = (float) args.get(2), dy_arg = (float) args.get(3);

		Circle pickPoint = new Circle(x_arg, y_arg, pointRadius);
		List<Shape> shapeList = new ArrayList<>(shapeStorage.values());
		shapeList.sort(Shape::compareTo);
		for (Shape shape : shapeList) {
			if (intersect(shape, pickPoint)) {
				io.printResult(shape + " is picked!");
				moveShape(Arrays.asList(shape.getName(), dx_arg, dy_arg));
				return;
			}
		}
		io.printResult("No shape has been picked-and-moved!");
	}

	/**
	 * Moves a shape by (dx,dy).
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has not been defined
	 * @throws InGroupMovementException when attemting to move grouped shapes
	 * @throws ShapeOutOfMapException   when attempting to move out of map
	 * @see Shape#move(float, float)
	 */
	public void moveShape(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);
		float dx_arg = (float) args.get(1), dy_arg = (float) args.get(2);

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		Shape shapeToBeMoved = shapeStorage.get(n_arg);
		shapeToBeMoved.move(dx_arg, dy_arg);
	}

	/**
	 * Determine whether two shape objects have any intersections.
	 * Use reflective operations to invoke the correct methods defined in IntersectionJudge.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when some names are not defined
	 * @throws InGroupMovementException when attemting to move grouped shapes
	 * @see IntersectionJudge
	 */
	public void hasIntersection(List<Object> args) throws InModelException {
		String n1_arg = (String) args.get(0);
		String n2_arg = (String) args.get(1);

		if (!containsShape(n1_arg))
			throw new IllegalNameException(n1_arg + " has not been defined!");
		if (!containsShape(n2_arg))
			throw new IllegalNameException(n2_arg + " has not been defined!");
		if (isGroupedShape(n1_arg) || isGroupedShape(n2_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");
		if (intersect(shapeStorage.get(n1_arg), shapeStorage.get(n2_arg)))
			io.printResult(String.format("TRUE (%s and %s)", n1_arg, n2_arg));
		else
			io.printResult(String.format("FALSE (%s and %s)", n1_arg, n2_arg));
	}

	/**
	 * Prints out a rectangle and its area representing the bounding box of a shape.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has not been defined
	 * @throws InGroupMovementException when attemting to move grouped shapes
	 */
	public void boundingbox(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		Shape shape = shapeStorage.get(n_arg);
		float x_p = shape.leftMost(), y_p = shape.upMost(),
				w_p = shape.rightMost() - x_p, h_p = y_p - shape.downMost();

		try {
			Rectangle box = new Rectangle(-1, "Bounding box for " + n_arg, x_p, y_p, w_p, h_p);
			io.printResult(box.toString());
			io.printResult(String.format("The area of the bounding box is %.2f", w_p * h_p));
		} catch (IllegalShapeException illegalShapeException) {
			io.printResult(String.format("The bounding box for %s does not exist!", n_arg));
		}
	}

	/**
	 * Lists a shape.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has not been defined
	 * @throws InGroupMovementException when attemting to move grouped shapes
	 * @see #listWithIndents(String, String)
	 */
	public void listOneShape(List<Object> args) throws InModelException {
		String n_arg = (String) args.get(0);

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		listWithIndents(n_arg, "");
	}


	private boolean intersect(Shape shapeOne, Shape shapeTwo) {
		Class<? extends Shape> classOne = shapeOne.getClass();
		Class<? extends Shape> classTwo = shapeTwo.getClass();
		if (shapeOne.isaGroup())
			return ((GroupShape) shapeOne).getGroupMembers().stream().anyMatch(m -> intersect(m, shapeTwo));
		if (shapeTwo.isaGroup())
			return ((GroupShape) shapeOne).getGroupMembers().stream().anyMatch(m -> intersect(m, shapeTwo));
		try {
			Method method = IntersectionJudge.class.getDeclaredMethod("intersects", classOne, classTwo);
			return (boolean) method.invoke(IntersectionJudge.class, shapeOne, shapeTwo);
		} catch (ReflectiveOperationException reflectiveOperationException) {
			reflectiveOperationException.printStackTrace();
			return false;
		}
	}

	private boolean isGroupedShape(String n) {
		return shapeStorage.get(n).isGrouped();
	}

	private boolean containsShape(String n) {
		return shapeStorage.containsKey(n);
	}

	private void listWithIndents(String n, String indents) {
		io.println(indents + shapeStorage.get(n));
	}

	private void listGroup(String n, String indents) {
		GroupShape grouper = (GroupShape) shapeStorage.get(n);
		for (Shape aMember : grouper.getGroupMembers()) {
			listWithIndents(aMember.getName(), indents);
			if (aMember instanceof GroupShape)
				listGroup(aMember.getName(), indents + "\t");
		}
	}

	/**
	 * Lists all existing shape.
	 * <p>Identify grouping relationships using indentations.</p>
	 * <p>The index is displayed before each shape.</p>
	 * <p>Use indentation to indicate grouping relationships.</p>
	 *
	 * @param args Always empty
	 * @see Shape#compareTo(Shape)
	 * @see #listWithIndents(String, String)
	 * @see #listGroup(String, String)
	 */
	public void listAllShapes(List<Object> args) {
		List<Shape> shapeList = new ArrayList<>(shapeStorage.values());
		if (shapeList.isEmpty())
			io.printResult("No shape in current session!");
		shapeList.sort(Shape::compareTo);

		for (Shape aShape : shapeList) {
			io.printResult(shapeList.size() + " Shapes in total!");
			listWithIndents(aShape.getName(), "");
			if (aShape instanceof GroupShape)
				listGroup(aShape.getName(), "\t\t");
		}
	}
}
