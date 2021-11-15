package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

/**
 *
 */
interface IntersectionJudge {
	/**
	 * @param ls1
	 * @param ls2
	 * @return
	 */
	static boolean intersects(LineSegment ls1, LineSegment ls2) {
		return false;
	}

	/**
	 * @param ls1
	 * @param c1
	 * @return
	 */
	static boolean intersects(LineSegment ls1, Circle c1) {
		return false;
	}

	/**
	 * @param ls1
	 * @param r1
	 * @return
	 */
	static boolean intersects(LineSegment ls1, Rectangle r1) {
		return false;
	}

	/**
	 * @param ls1
	 * @param s1
	 * @return
	 */
	static boolean intersects(LineSegment ls1, Square s1) {
		return false;
	}

	/**
	 * @param ls1
	 * @param g1
	 * @return
	 */
	static boolean intersects(LineSegment ls1, GroupShape g1) {
		return false;
	}

	/**
	 * @param c1
	 * @param ls1
	 * @return
	 */
	static boolean intersects(Circle c1, LineSegment ls1) {
		return false;
	}

	/**
	 * @param c1
	 * @param c2
	 * @return
	 */
	static boolean intersects(Circle c1, Circle c2) {
		return false;
	}

	/**
	 * @param c1
	 * @param r1
	 * @return
	 */
	static boolean intersects(Circle c1, Rectangle r1) {
		return false;
	}

	/**
	 * @param c1
	 * @param s1
	 * @return
	 */
	static boolean intersects(Circle c1, Square s1) {
		return false;
	}

	/**
	 * @param c1
	 * @param g1
	 * @return
	 */
	static boolean intersects(Circle c1, GroupShape g1) {
		return false;
	}

	/**
	 * @param r1
	 * @param ls1
	 * @return
	 */
	static boolean intersects(Rectangle r1, LineSegment ls1) {
		return false;
	}

	/**
	 * @param r1
	 * @param c1
	 * @return
	 */
	static boolean intersects(Rectangle r1, Circle c1) {
		return false;
	}

	/**
	 * @param r1
	 * @param r2
	 * @return
	 */
	static boolean intersects(Rectangle r1, Rectangle r2) {
		return false;
	}

	/**
	 * @param r1
	 * @param s1
	 * @return
	 */
	static boolean intersects(Rectangle r1, Square s1) {
		return false;
	}

	/**
	 * @param r1
	 * @param g1
	 * @return
	 */
	static boolean intersects(Rectangle r1, GroupShape g1) {
		return false;
	}

	/**
	 * @param s1
	 * @param ls1
	 * @return
	 */
	static boolean intersects(Square s1, LineSegment ls1) {
		return false;
	}

	/**
	 * @param s1
	 * @param c1
	 * @return
	 */
	static boolean intersects(Square s1, Circle c1) {
		return false;
	}

	/**
	 * @param s1
	 * @param r1
	 * @return
	 */
	static boolean intersects(Square s1, Rectangle r1) {
		return false;
	}

	/**
	 * @param s1
	 * @param s2
	 * @return
	 */
	static boolean intersects(Square s1, Square s2) {
		return false;
	}

	/**
	 * @param s1
	 * @param g1
	 * @return
	 */
	static boolean intersects(Square s1, GroupShape g1) {
		return false;
	}

	/**
	 * @param g1
	 * @param ls1
	 * @return
	 */
	static boolean intersects(GroupShape g1, LineSegment ls1) {
		return false;
	}

	/**
	 * @param g1
	 * @param c1
	 * @return
	 */
	static boolean intersects(GroupShape g1, Circle c1) {
		return false;
	}

	/**
	 * @param g1
	 * @param r1
	 * @return
	 */
	static boolean intersects(GroupShape g1, Rectangle r1) {
		return false;
	}

	/**
	 * @param g1
	 * @param s1
	 * @return
	 */
	static boolean intersects(GroupShape g1, Square s1) {
		return false;
	}

	/**
	 * @param g1
	 * @param g2
	 * @return
	 */
	static boolean intersects(GroupShape g1, GroupShape g2) {
		return false;
	}
}
