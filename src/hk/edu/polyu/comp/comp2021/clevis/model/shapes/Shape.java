package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import java.io.Serializable;
import java.util.Date;


abstract class Shape implements Serializable, Comparable<Shape> {
	protected static int DEF_Z_ARG = Integer.MIN_VALUE;
	protected static String DEF_NAME_ARG = "DEFAULT_TEMPORARY_SHAPE";
	protected String name;
	protected int zOrder;
	protected Date date;
	private Shape grouper;

	protected Shape(int z_arg, String n_arg) {
		setName(n_arg);
		setzOrder(z_arg);
		setDate(new Date());
		setGrouper(this);
	}

	protected String getName() {
		return name;
	}

	protected void setName(String n_arg) {
		name = n_arg;
	}

	protected boolean isGrouped() {
		return grouper != this;
	}

	public int getzOrder() {
		return zOrder;
	}

	protected void setzOrder(int z_arg) {
		zOrder = z_arg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date_arg) {
		date = date_arg;
	}

	public Shape getGrouper() {
		return grouper;
	}

	protected void setGrouper(Shape grouper_arg) {
		grouper = grouper_arg;
	}

	@Override
	public int compareTo(Shape another) {
		return another.getzOrder() - this.zOrder;
	}

	public abstract void move(float dx_arg, float dy_arg);

	public abstract boolean hasIntersection(Shape shape);
}