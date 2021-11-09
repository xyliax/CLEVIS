package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

class Rectangle extends SimpleShape {
	private float width;
	private float height;

	Rectangle(int z_arg, String n_arg, float x_arg, float y_arg, float w_arg, float h_arg) {
		super(z_arg, n_arg, x_arg, y_arg);
		setWidth(w_arg);
		setHeight(h_arg);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float w_arg) {
		width = w_arg;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float h_arg) {
		height = h_arg;
	}

	@Override
	public String toString() {
		return String.format(
				"%s@Rectangle [top-left] = (%.2f, %.2f) [width] = %.2f [height] = %.2f",
				name, x, y, width, height);
	}

	@Override
	public boolean hasIntersection(Shape shape) {
		float x1 = x, x2 = x + width, y1 = y, y2 = y - height;
		LineSegment l1 = new LineSegment(x1, y1, x1, y2);
		LineSegment l2 = new LineSegment(x1, y2, x2, y2);
		LineSegment l3 = new LineSegment(x2, y2, x1, y2);
		LineSegment l4 = new LineSegment(x1, y2, x1, y1);
		return l1.hasIntersection(shape) || l2.hasIntersection(shape)
				|| l3.hasIntersection(shape) || l4.hasIntersection(shape);
	}
}
