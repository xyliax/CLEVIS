package hk.edu.polyu.comp.comp2021.clevis.model.shapes;


class Square extends Rectangle {

    Square(int z_arg, String n_arg, float x_arg, float y_arg, float l_arg) {
        super(z_arg, n_arg, x_arg, y_arg, l_arg, l_arg);
    }

    @Override
    public String toString() {
        return String.format("%s@Square [top-left] = (%.2f, %.2f) [length] = %.2f",
                name, x, y, getWidth());
    }
}
