package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.IllegalShapeException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeOutOfMapException;


/**
 * The class for line segment.
 * <p>The defining point of a line segment is its ends_1.</p>
 *
 * @see SimpleShape
 */
class LineSegment extends SimpleShape {
	private float endX;
	private float endY;

	/**
	 * Constructor of LineSegment, specifically designed for temporary objects.
	 *
	 * @param x1_arg x-coordinate of ends_1
	 * @param y1_arg y-coordinate of ends_1
	 * @param x2_arg x-coordinate of ends_2
	 * @param y2_arg y-coordinate of ends_2
	 * @throws ShapeOutOfMapException when the arguments are illegal for the map
	 * @throws IllegalShapeException  when the two ends coinside
	 * @see #LineSegment(int, String, float, float, float, float)
	 */
	LineSegment(float x1_arg, float y1_arg, float x2_arg, float y2_arg) throws ShapeOutOfMapException, IllegalShapeException {
		this(DEF_Z_ARG, DEF_NAME_ARG, x1_arg, y1_arg, x2_arg, y2_arg);
	}

	/**
	 * Constructor of LineSegment.
	 *
	 * @param z_arg  z_order
	 * @param n_arg  name
	 * @param x1_arg x-coordinate of ends_1
	 * @param y1_arg y-coordinate of ends_1
	 * @param x2_arg x-coordinate of ends_2
	 * @param y2_arg y-coordinate of ends_2
	 * @throws ShapeOutOfMapException when the arguments are illegal for the map
	 * @throws IllegalShapeException  when the two ends coinside
	 * @see SimpleShape#SimpleShape(int, String, float, float)
	 */
	LineSegment(int z_arg, String n_arg, float x1_arg, float y1_arg, float x2_arg, float y2_arg) throws ShapeOutOfMapException, IllegalShapeException {
		super(z_arg, n_arg, x1_arg, y1_arg);
		if (x2_arg == x1_arg && y2_arg == y1_arg)
			throw new IllegalShapeException("The two ends cannot coinside!");
		setEndX(x2_arg);
		setEndY(y2_arg);
	}

	@Override
	float upMost() {
		return Math.max(y, endY);
	}

	@Override
	float downMost() {
		return Math.min(y, endY);
	}

	@Override
	float leftMost() {
		return Math.min(x, endX);
	}

	@Override
	float rightMost() {
		return Math.max(x, endX);
	}

	@Override
	void move(float dx_arg, float dy_arg) throws ShapeOutOfMapException {
		super.move(dx_arg, dy_arg);
		this.endX += dx_arg;
		this.endY += dy_arg;
	}

	@Override
	public String toString() {
		return String.format(
				"%s@LineSegment [ends1] = (%.2f, %.2f) [ends2] = (%.2f, %.2f)",
				getName(), getX(), getY(), getEndX(), getEndY());
	}

	/**
	 * Getter of endX.
	 *
	 * @return the x-coordinate of ends_2
	 * @see #endX
	 */
	float getEndX() {
		return endX;
	}

	/**
	 * Setter of endX.
	 *
	 * @param endX_arg the x-coordinate to be set to eds_2
	 * @see #endX
	 */
	void setEndX(float endX_arg) {
		endX = endX_arg;
	}

	/**
	 * Getter of endY.
	 *
	 * @return the y-coordinate of ends_2
	 * @see #endY
	 */
	float getEndY() {
		return endY;
	}

	/**
	 * Setter of endY.
	 *
	 * @param endY_arg the y-coordinate to be set to ends_2
	 * @see #endY
	 */
	void setEndY(float endY_arg) {
		endY = endY_arg;
	}
}