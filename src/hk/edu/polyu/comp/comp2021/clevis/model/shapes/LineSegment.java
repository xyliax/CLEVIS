package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

class LineSegment extends SimpleShape {
    protected float endX, endY;

    LineSegment(int z_arg, String n_arg, float x1_arg, float y1_arg, float x2_arg, float y2_arg) {
        super(z_arg, n_arg, x1_arg, y1_arg);
        setEndX(x2_arg);
        setEndY(y2_arg);
    }

    LineSegment(float x1_arg, float y1_arg, float x2_arg, float y2_arg) {
        this(DEF_Z_ARG, DEF_NAME_ARG, x1_arg, y1_arg, x2_arg, y2_arg);
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX_arg) {
        endX = endX_arg;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY_arg) {
        endY = endY_arg;
    }

    @Override
    public void move(float dx_arg, float dy_arg) {
        super.move(dx_arg, dy_arg);
        this.endX += dx_arg;
        this.endY += dy_arg;
    }

    @Override
    public boolean hasIntersection(Shape shape) {
        if (shape instanceof LineSegment) {
            // FIXME: 9/11/2021 
            return false;
        } else if (shape instanceof Circle) {
            // FIXME: 9/11/2021 
            return false;
        } else return shape.hasIntersection(this);
    }

    @Override
    public String toString() {
        return String.format(
                "%s@LineSegment [ends1] = (%.2f, %.2f) [ends2] = (%.2f, %.2f)",
                name, x, y, endX, endY);
    }
}
