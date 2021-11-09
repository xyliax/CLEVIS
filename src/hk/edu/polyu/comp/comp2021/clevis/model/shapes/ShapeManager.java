package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShapeManager implements Serializable {
    private static int Z_ORDER = 0;
    private final HashMap<String, Shape> shapeStorage;
    private final HashMap<String, Shape> shapeTrashcan;

    public ShapeManager() {
        shapeStorage = new HashMap<>();
        shapeTrashcan = new HashMap<>();
        System.out.println("ShapeManager initialized");
    }

    public boolean containShape(String n) {
        return shapeStorage.containsKey(n);
    }

    public int shapeNumber() {
        return shapeStorage.size();
    }

    public void createCircle(String n, float x, float y, float r) {
        Circle newCircle = new Circle(
                Z_ORDER++, n, x, y, r);
        shapeStorage.put(newCircle.getName(), newCircle);
    }

    public void createRectangle(String n, float x, float y, float w, float h) {
        Rectangle newRectangle = new Rectangle(Z_ORDER++, n, x, y, w, h);
        shapeStorage.put(newRectangle.getName(), newRectangle);
    }

    public void createSquare(String n, float x, float y, float l) {
        Square newSquare = new Square(Z_ORDER++, n, x, y, l);
        shapeStorage.put(newSquare.getName(), newSquare);
    }

    public void createLineSegment(String n, float x1, float y1, float x2, float y2) {
        LineSegment newLineSegment = new LineSegment(Z_ORDER++, n, x1, y1, x2, y2);
        shapeStorage.put(newLineSegment.getName(), newLineSegment);
    }

    public void createGroup(String n, String... members) {
        GroupShape newGroup = new GroupShape(Z_ORDER++, n);
        shapeStorage.put(newGroup.getName(), newGroup);
        for (String aMember : members) {
            newGroup.invite(shapeStorage.get(aMember));

        }
    }

    public void disbandGroup(String n) {
        GroupShape grouper = (GroupShape) shapeStorage.get(n);
        grouper.disband();
        discard(n);
    }

    public void deleteShape(String n) {
        Shape shapeToBeDeleted = shapeStorage.get(n);
        if (shapeToBeDeleted instanceof GroupShape) {
            for (Shape aMember : ((GroupShape) shapeToBeDeleted).getGroupMembers()) {
                deleteShape(aMember.getName());
            }
        }
        discard(n);
    }

    public void moveShape(String n, float dx, float dy) {
        Shape shapeToBeMoved = shapeStorage.get(n);
        shapeToBeMoved.move(dx, dy);
    }

    public boolean isGroupedShape(String n) {
        return shapeStorage.get(n).isGrouped();
    }

    public void list(String n, String indents) {
        System.out.println(indents + shapeStorage.get(n));
    }

    public HashMap<String, Shape> getShapeStorage() {
        return shapeStorage;
    }

    public HashMap<String, Shape> getShapeTrashcan() {
        return shapeTrashcan;
    }

    public Date getShapeDate(String n) {
        return shapeStorage.get(n).getDate();
    }

    public boolean hasIntersection(String n1, String n2) {
        Shape shape1 = shapeStorage.get(n1);
        Shape shape2 = shapeStorage.get(n2);
        return shape1.hasIntersection(shape2);
    }

    public void listAllShapes() {
        List<Shape> shapeList = new ArrayList<>(shapeStorage.values());
        shapeList.sort(Shape::compareTo);
        int counter = 0;
        for (Shape aShape : shapeList) {
            System.out.printf("%4s", (counter++) + ": ");
            list(aShape.getName(), "");
            if (aShape instanceof GroupShape) {
                listGroup(aShape.getName(), "\t\t");
            }
        }
    }

    private void listGroup(String n, String indents) {
        GroupShape grouper = (GroupShape) shapeStorage.get(n);
        for (Shape aMember : grouper.getGroupMembers()) {
            list(aMember.getName(), indents);
            if (aMember instanceof GroupShape) {
                listGroup(aMember.getName(), indents + "\t");
            }
        }
    }

    private void discard(String n) {
        Shape shapeToBeDiscarded = shapeStorage.get(n);
        shapeStorage.remove(n);
        shapeTrashcan.put(shapeToBeDiscarded.getName(), shapeToBeDiscarded);
    }

    private void recycle(String n) {
        Shape shapeToBeRecycled = shapeTrashcan.get(n);
        shapeTrashcan.remove(n);
        shapeStorage.put(shapeToBeRecycled.getName(), shapeToBeRecycled);
    }
}
