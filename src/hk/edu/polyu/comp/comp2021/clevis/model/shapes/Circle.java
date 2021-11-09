package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

class Circle extends SimpleShape {
	private float radius;

	public Circle(int z_arg, String n_arg, float x_arg, float y_arg, float r_arg) {
		super(z_arg, n_arg, x_arg, y_arg);
		setRadius(r_arg);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float r_arg) {
		radius = r_arg;
	}

	@Override
	public String toString() {
		return String.format("%s@Circle [center] = (%.2f, %.2f) [radius] = %.2f",
				name, x, y, radius);
	}

	@Override
	public boolean hasIntersection(Shape shape) {
		if (shape instanceof Circle) {
			float x1 = x, x2 = ((Circle) shape).getX(),
					y1 = y, y2 = ((Circle) shape).getY(),
					r1 = radius, r2 = ((Circle) shape).getRadius();
			float v = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
			return (r1 - r2) * (r1 - r2) <= v && v <= (r1 + r2) * (r1 + r2);
		} else return shape.hasIntersection(this);
	}
}