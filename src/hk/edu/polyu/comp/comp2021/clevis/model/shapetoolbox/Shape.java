package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeOutOfMapException;

import java.io.Serializable;


/**
 * The abstract class for all kinds of shape.
 * <p>Defines some common fields and methods of all shapes.</p>
 * <p>Compares by the z_order, later created shape has larger z_order.</p>
 *
 * @see SimpleShape
 * @see GroupShape
 */
abstract class Shape implements Serializable, Comparable<Shape> {

	/**
	 * Default z_order for some special shape objects.
	 */
	protected static int DEF_Z_ARG = Integer.MIN_VALUE;

	/**
	 * Default name for some special shape objects.
	 */
	protected static String DEF_NAME_ARG = "DEFAULT_TEMPORARY_SHAPE";

	/**
	 * name for this shape.
	 */
	protected String name;

	/**
	 * z_order for this shape.
	 */
	protected int zOrder;

	/**
	 * The gouper of this shape, default to be itself.
	 */
	protected Shape grouper;

	/**
	 * whther this shape is a group shape.
	 */
	protected boolean aGroup;

	/**
	 * Constructor of Shape.
	 *
	 * @param z_arg z_order
	 * @param n_arg name
	 * @see SimpleShape#SimpleShape(int, String, float, float)
	 * @see GroupShape#GroupShape(int, String)
	 */
	Shape(int z_arg, String n_arg) {
		setName(n_arg);
		setzOrder(z_arg);
		setGrouper(this);
	}

	/**
	 * Getter of name.
	 *
	 * @return the name of this Shape
	 * @see #name
	 */
	String getName() {
		return name;
	}

	/**
	 * Setter of name.
	 *
	 * @param n_arg the name to be set
	 * @see #name
	 */
	void setName(String n_arg) {
		name = n_arg;
	}

	/**
	 * Judges if this shape is in a group.
	 *
	 * @return whether this shape is in a group
	 * @see #getGrouper()
	 */
	boolean isGrouped() {
		return getGrouper() != this;
	}

	/**
	 * Getter of grouper.
	 *
	 * @return grouper of this shape
	 * @see #grouper
	 */
	protected Shape getGrouper() {
		return grouper;
	}

	/**
	 * Setter of grouper.
	 *
	 * @param grouper_arg the shape to be set as grouper
	 * @see #grouper
	 */
	void setGrouper(Shape grouper_arg) {
		grouper = grouper_arg;
	}

	/**
	 * Getter of aGroup.
	 *
	 * @return whether it is a group
	 * @see #aGroup
	 */
	boolean isaGroup() {
		return aGroup;
	}

	/**
	 * Setter of aGroup.
	 *
	 * @param aGroup whether it is a group
	 * @see #aGroup
	 */
	void setaGroup(boolean aGroup) {
		this.aGroup = aGroup;
	}

	/**
	 * This method moves this shape.
	 * <p>Implemented differently between simpleshapes and groupshapes.</p>
	 *
	 * @param dx_arg the increasing value of x-coordiante
	 * @param dy_arg the increasing value of y-coordinate
	 * @throws ShapeOutOfMapException when attempting to move out of map
	 * @see SimpleShape#move(float, float)
	 * @see GroupShape#move(float, float)
	 */
	abstract void move(float dx_arg, float dy_arg) throws ShapeOutOfMapException;

	/**
	 * This method gets the largest y-coordinate of this shape.
	 *
	 * @return the largest y-coordinate of this shape
	 * @see LineSegment#upMost()
	 * @see Circle#upMost()
	 * @see Rectangle#upMost()
	 * @see GroupShape#upMost()
	 */
	abstract float upMost();

	/**
	 * This method gets the smallest y-coordinate of this shape.
	 *
	 * @return the smallest y-coordinate of this shape
	 * @see LineSegment#downMost()
	 * @see Circle#downMost()
	 * @see Rectangle#downMost()
	 * @see GroupShape#downMost()
	 */
	abstract float downMost();

	/**
	 * This method gets the smallest x-coordinate of this shape.
	 *
	 * @return the smallest x-coordinate of this shape
	 * @see LineSegment#leftMost()
	 * @see Circle#leftMost()
	 * @see Rectangle#leftMost()
	 * @see GroupShape#leftMost()
	 */
	abstract float leftMost();

	/**
	 * This method gets the largest x-coordinate of this shape.
	 *
	 * @return the largest x-coordinate of this shape
	 * @see LineSegment#rightMost()
	 * @see Circle#rightMost()
	 * @see Rectangle#rightMost()
	 * @see GroupShape#rightMost()
	 */
	abstract float rightMost();

	@Override
	public int compareTo(Shape another) {
		return another.getzOrder() - this.zOrder;
	}

	/**
	 * Getter of zOrder.
	 *
	 * @return the z_order of this shape
	 * @see #zOrder
	 */
	int getzOrder() {
		return zOrder;
	}

	/**
	 * Setter of zOrder.
	 *
	 * @param z_arg z_order to be set
	 * @see #zOrder
	 */
	void setzOrder(int z_arg) {
		zOrder = z_arg;
	}
}