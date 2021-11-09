package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import java.util.ArrayList;
import java.util.List;

import static hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager.*;


/**
 * The class for group of shape, whose members are temporarily locked.
 *
 * @see Shape
 */
class GroupShape extends Shape {
	private final List<Shape> groupMembers;


	/**
	 * Constructor of GroupShape.
	 *
	 * @param z_arg z_order
	 * @param n_arg name
	 * @see Shape#Shape(int, String)
	 */
	GroupShape(int z_arg, String n_arg) {
		super(z_arg, n_arg);
		groupMembers = new ArrayList<>();
	}


	/**
	 * Invites a new Shape member to this group.
	 *
	 * @param member the Shape member to be invited
	 * @see #setGrouper(Shape)
	 */
	void invite(Shape member) {
		member.setGrouper(this);
		groupMembers.add(member);
	}


	/**
	 * Releases all members in this group.
	 *
	 * @see #setGrouper(Shape)
	 */
	void disband() {
		groupMembers.forEach(aMember -> aMember.setGrouper(this));
	}


	/**
	 * Getter of groupMembers.
	 *
	 * @return the List of all the members in this group
	 * @see #groupMembers
	 */
	List<Shape> getGroupMembers() {
		return groupMembers;
	}

	private String getNameList() {
		List<String> nameList = new ArrayList<>();
		groupMembers.forEach(aMember -> nameList.add(aMember.getName()));
		return nameList.toString();
	}


	@Override
	void move(float dx_arg, float dy_arg) throws ShapeOutOfMapException {
		if (rightMost() + dx_arg > CX_MAX_VALUE || leftMost() + dx_arg < CX_MIN_VALUE
				|| upMost() + dy_arg > CY_MAX_VALUE || downMost() + dy_arg < CY_MIN_VALUE)
			throw new ShapeOutOfMapException("Failure! Some shape in this group would be out of map!");

		for (Shape aMember : groupMembers)
			aMember.move(dx_arg, dy_arg);
	}

	@Override
	float upMost() {
		float upmost = CY_MIN_VALUE;
		for (Shape aMember : groupMembers)
			upmost = Math.max(upmost, aMember.upMost());
		return upmost;
	}

	@Override
	float downMost() {
		float downmost = CY_MAX_VALUE;
		for (Shape aMember : groupMembers)
			downmost = Math.min(downmost, aMember.downMost());
		return downmost;
	}

	@Override
	float leftMost() {
		float leftmost = CX_MAX_VALUE;
		for (Shape aMember : groupMembers)
			leftmost = Math.min(leftmost, aMember.leftMost());
		return leftmost;
	}

	@Override
	float rightMost() {
		float rightmost = CX_MIN_VALUE;
		for (Shape aMember : groupMembers)
			rightmost = Math.max(rightmost, aMember.rightMost());
		return rightmost;
	}

	@Override
	public String toString() {
		return String.format("%s@Group [size] = %d [members] = %s",
				getName(), getGroupMembers().size(), getNameList());
	}
}