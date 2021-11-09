package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;


/**
 * The class for square.
 * <p>Inherited from Rectangle.</p>
 *
 * @see Rectangle
 */
class Square extends Rectangle {


	/**
	 * Constructor of Square.
	 *
	 * @param z_arg z_order
	 * @param n_arg name
	 * @param x_arg x-coordinate of the top-left corner
	 * @param y_arg y-coordinate of the top-left corner
	 * @param l_arg length
	 * @throws ShapeOutOfMapException when the arguments are illegal for the map
	 * @see Rectangle#Rectangle(int, String, float, float, float, float)
	 */
	Square(int z_arg, String n_arg, float x_arg, float y_arg, float l_arg) throws ShapeOutOfMapException {
		super(z_arg, n_arg, x_arg, y_arg, l_arg, l_arg);
	}


	@Override
	public String toString() {
		return String.format("%s@Square [top-left] = (%.2f, %.2f) [length] = %.2f",
				getName(), getX(), getY(), getWidth());
	}
}