package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

/**
 * brief implementation of Shape
 * Very important!!!
 * 1.methods should check arguments validness, but where?
 * 2.methods should check if this is in a group
 */
abstract class SimpleShape extends Shape {
    protected float x, y;

    SimpleShape(int z_arg, String n_arg, float x_arg, float y_arg) {
        super(z_arg, n_arg);
        setX(x_arg);
        setY(y_arg);
    }

    public float getX() {
        return x;
    }

    public void setX(float x_arg) {
        x = x_arg;
    }

    public float getY() {
        return y;
    }

    public void setY(float y_arg) {
        y = y_arg;
    }

    @Override
    public void move(float dx_arg, float dy_arg) {
        setX(x + dx_arg);
        setY(y + dy_arg);
    }

}




