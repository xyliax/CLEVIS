package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * GroupShape
 */
class GroupShape extends Shape {
	protected List<Shape> groupMembers;

	GroupShape(int z_arg, String n_arg) {
		super(z_arg, n_arg);
		groupMembers = new ArrayList<>();
	}

	public void invite(Shape member) {
		member.setGrouper(this);
		groupMembers.add(member);
	}

	public List<Shape> getGroupMembers() {
		return groupMembers;
	}

	public String getNameList() {
		List<String> nameList = new ArrayList<>();
		for (Shape aMember : groupMembers) {
			nameList.add(aMember.getName());
		}
		return nameList.toString();
	}

	public void disband() {
		for (Shape aMember : groupMembers) {
			aMember.setGrouper(aMember);
		}
	}

	@Override
	public void move(float dx, float dy) {
		for (Shape groupMember : groupMembers) {
			groupMember.move(dx, dy);
		}
	}

	@Override
	public boolean hasIntersection(Shape shape) {
		for (Shape aMember : groupMembers) {
			if (aMember.hasIntersection(shape)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s@Group [size] = %d [members] = %s",
				name, groupMembers.size(), getNameList());
	}
}
