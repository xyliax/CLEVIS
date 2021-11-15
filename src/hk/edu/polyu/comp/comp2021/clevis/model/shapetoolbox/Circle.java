package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeOutOfMapException;

/**
 * The class for circle.
 * <p>The defining point of a circle is its center.</p>
 *
 * @see SimpleShape
 */
class Circle extends SimpleShape {
	private float radius;

	/**
	 * Constructor of Circle.
	 *
	 * @param z_arg z_order
	 * @param n_arg name
	 * @param x_arg x-coordinate of the center
	 * @param y_arg y-coordinate of the center
	 * @param r_arg radius
	 * @throws ShapeOutOfMapException when the arguments are illegal for the map
	 * @see SimpleShape#SimpleShape(int, String, float, float)
	 */
	Circle(int z_arg, String n_arg, float x_arg, float y_arg, float r_arg) throws ShapeOutOfMapException {
		super(z_arg, n_arg, x_arg, y_arg);
		setRadius(r_arg);

		if (this.isInMap())
			throw new ShapeOutOfMapException("Failure! Circle out of map!");
	}

	/**
	 * Constructor of Cicle, specifically designed for temporary objects.
	 *
	 * @param x_arg x-coordinate of the center
	 * @param y_arg y-coordinate of the center
	 * @param r_arg radius
	 * @throws ShapeOutOfMapException when the arguments are illegal for the map
	 * @see #Circle(int, String, float, float, float)
	 */
	Circle(float x_arg, float y_arg, float r_arg) throws ShapeOutOfMapException {
		this(DEF_Z_ARG, DEF_NAME_ARG, x_arg, y_arg, r_arg);
	}


	/**
	 * Getter of radius.
	 *
	 * @return the radius of this circle
	 * @see #radius
	 */
	float getRadius() {
		return radius;
	}

	/**
	 * Setter of radius.
	 *
	 * @param r_arg the radius to be set
	 * @see #radius
	 */
	void setRadius(float r_arg) {
		radius = r_arg;
	}


	@Override
	float upMost() {
		return y + radius;
	}

	@Override
	float downMost() {
		return y - radius;
	}

	@Override
	float leftMost() {
		return x - radius;
	}

	@Override
	float rightMost() {
		return x + radius;
	}

	@Override
	public String toString() {
		return String.format("%s@Circle [center] = (%.2f, %.2f) [radius] = %.2f",
				getName(), getX(), getY(), getRadius());
	}
}