package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The class for shape management.
 * <p>This class is responsible for all the actions on any shape.</p>
 * <p>The only access to shape classes, and the connection between shape and users.</p>
 * <p>It sets some basic rules of shape-relevent actions</p>
 *
 * @see Shape
 * @see hk.edu.polyu.comp.comp2021.clevis.model.Clevis
 */
public class ShapeManager implements Serializable {

	/**
	 * The maximum bound of the X-coordinate.
	 */
	protected static final float CX_MAX_VALUE = Float.MAX_VALUE;

	/**
	 * The minimum bound of the X-coordinate.
	 */
	protected static final float CX_MIN_VALUE = Float.MIN_VALUE;

	/**
	 * The maximum bound of the Y-coordinate.
	 */
	protected static final float CY_MAX_VALUE = Float.MAX_VALUE;

	/**
	 * The minimum bound of the Y-coordinate.
	 */
	protected static final float CY_MIN_VALUE = Float.MIN_VALUE;

	private final HashMap<String, Shape> shapeStorage;
	private final HashMap<String, Shape> shapeTrashcan;
	private int Z_ORDER;


	/**
	 * Constructor for ShapeManager.
	 * <p>Intialize the shape storage and trashcan; set Z_order to 0.</p>
	 */
	public ShapeManager() {
		shapeStorage = new HashMap<>();
		shapeTrashcan = new HashMap<>();
		Z_ORDER = 0;
		System.out.println("ShapeManager initialized");
	}


	/**
	 * Creates a new line segments and puts it into storage.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws ShapeOutOfMapException when the arguments are illegal
	 * @throws IllegalNameException   when the name has been defined
	 * @see LineSegment#LineSegment(int, String, float, float, float, float)
	 */
	public void createLineSegment(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		float x1_arg = (float) args[1], y1_arg = (float) args[2],
				x2_arg = (float) args[3], y2_arg = (float) args[4];

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));

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
	public void createCircle(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		float x_arg = (float) args[1], y_arg = (float) args[2],
				r_arg = (float) args[3];

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
	public void createRectangle(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		float x_arg = (float) args[1], y_arg = (float) args[2],
				w_arg = (float) args[3], h_arg = (float) args[4];

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
	public void createSquare(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		float x_arg = (float) args[1], y_arg = (float) args[2],
				l_arg = (float) args[3];

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
	public void createGroup(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		Object[] ni_args = Arrays.copyOfRange(args, 1, args.length);

		if (containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has been defined!", n_arg));
		if (Arrays.stream(ni_args).anyMatch(ni -> isGroupedShape((String) ni)))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		GroupShape newGroup = new GroupShape(Z_ORDER++, (String) args[0]);
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
	public void disbandGroup(Object... args) throws ClevisException {
		String n_arg = (String) args[0];

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (!(shapeStorage.get(n_arg) instanceof GroupShape))
			throw new IllegalNameException(n_arg + " is not a GroupShape!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		GroupShape grouper = (GroupShape) shapeStorage.get(n_arg);
		grouper.disband();
		discard((String) args[0]);
	}


	/**
	 * Deletes a shape and move it from storage to trashcan.
	 *
	 * @param args arguments from outside, will be split into correct parts
	 * @throws IllegalNameException     when the name has not been defined
	 * @throws InGroupMovementException when attempting to move grouped shapes
	 * @see #discard(String)
	 */
	public void deleteShape(Object... args) throws ClevisException {
		String n_arg = (String) args[0];

		if (!containsShape(n_arg))
			throw new IllegalNameException(String.format("Duplicate name! %s has not been defined!", n_arg));
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		Shape shapeToBeDeleted = shapeStorage.get(n_arg);
		if (shapeToBeDeleted instanceof GroupShape) {
			for (Shape aMember : ((GroupShape) shapeToBeDeleted).getGroupMembers())
				deleteShape(aMember.getName());
		}
		discard(n_arg);
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
	public void moveShape(Object... args) throws ClevisException {
		String n_arg = (String) args[0];
		float dx_arg = (float) args[1], dy_arg = (float) args[2];

		if (!containsShape(n_arg))
			throw new IllegalNameException(n_arg + " has not been defined!");
		if (isGroupedShape(n_arg))
			throw new InGroupMovementException("In group shape cannot be used directly!");

		Shape shapeToBeMoved = shapeStorage.get(n_arg);
		shapeToBeMoved.move(dx_arg, dy_arg);
	}

	/**
	 * @param args
	 * @throws ClevisException
	 */
	public void pickMoveShape(Object... args) throws ClevisException{
		float x = (float) args[0], y = (float) args[1],
				dx = (float) args[2], dy = (float) args[3];

		Circle pickPoint;
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
	public void listAllShapes(Object... args) {
		List<Shape> shapeList = new ArrayList<>(shapeStorage.values());
		shapeList.sort(Shape::compareTo);

		int counter = 0;
		for (Shape aShape : shapeList) {
			System.out.printf("%4s", (counter++) + ": ");
			listWithIndents(aShape.getName(), "");
			if (aShape instanceof GroupShape)
				listGroup(aShape.getName(), "\t\t");
		}
	}


	/**
	 * This method judges if a shape is grouped.
	 *
	 * @param n the name of a shape
	 * @return whether a shape is grouped
	 * @see Shape#isGrouped()
	 */
	public boolean isGroupedShape(String n) {
		return shapeStorage.get(n).isGrouped();
	}


	private boolean containsShape(String n) {
		return shapeStorage.containsKey(n);
	}


	private void listWithIndents(String n, String indents) {
		System.out.println(indents + shapeStorage.get(n));
	}


	private void listGroup(String n, String indents) {
		GroupShape grouper = (GroupShape) shapeStorage.get(n);
		for (Shape aMember : grouper.getGroupMembers()) {
			listWithIndents(aMember.getName(), indents);
			if (aMember instanceof GroupShape)
				listGroup(aMember.getName(), indents + "\t");
		}
	}


	private void discard(String n) {
		Shape shapeToBeDiscarded = shapeStorage.get(n);
		shapeStorage.remove(n);
		shapeTrashcan.put(shapeToBeDiscarded.getName(), shapeToBeDiscarded);
	}
}
