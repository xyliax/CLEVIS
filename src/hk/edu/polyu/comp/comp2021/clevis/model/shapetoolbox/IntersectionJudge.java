package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

import java.util.Arrays;


/**
 * Interface for intersectionJudge.
 * <p>line-line; line-circle; circle-circle;</p>
 * <p>Are capable for judging anything.</p>
 *
 * @see #intersects(LineSegment, LineSegment)
 * @see #intersects(LineSegment, Circle)
 * @see #intersects(Circle, Circle)
 */
interface IntersectionJudge {

	/**
	 * @param ls1 line segment 1
	 * @param r1  rectangle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(LineSegment ls1, Rectangle r1) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(ls1, side));
	}

	/**
	 * Crucial method!
	 *
	 * @param ls1 line segment 1
	 * @param ls2 line segment 2
	 * @return whether they have any intersections
	 */
	static boolean intersects(LineSegment ls1, LineSegment ls2) {
		float x1_1 = ls1.getX(), y1_1 = ls1.getY(), x1_2 = ls1.getEndX(), y1_2 = ls1.getEndY(),
				x2_1 = ls2.getX(), y2_1 = ls2.getY(), x2_2 = ls2.getEndX(), y2_2 = ls2.getEndY();

		if (Math.max(x2_1, x2_2) < Math.min(x1_1, x1_2)
				|| Math.max(y2_1, y2_2) < Math.min(y1_1, y1_2)
				|| Math.max(x1_1, x1_2) < Math.min(x2_1, x2_2)
				|| Math.max(y1_1, y1_2) < Math.min(y2_1, y2_2))
			return false;
		return !((((x1_1 - x2_1) * (y2_2 - y2_1) - (y1_1 - y2_1) * (y2_2 - y2_1)) *
				((x1_2 - x2_1) * (y2_2 - y2_1) - (y1_2 - y2_1) * (x2_2 - x2_1))) > 0)
				&& !(((x2_1 - x1_1) * (y1_2 - y1_1) - (y2_1 - y1_1) * (x1_2 - x1_1)) *
				((x2_2 - x1_1) * (y1_2 - y1_1) - (y2_2 - y1_1) * (x1_2 - x1_1)) > 0);
	}

	/**
	 * @param ls1 line segment 1
	 * @param s1  square 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(LineSegment ls1, Square s1) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(ls1, side));
	}

	/**
	 * Crucial method!
	 *
	 * @param c1 circle 1
	 * @param c2 circle 2
	 * @return whether they have any intersections
	 */
	static boolean intersects(Circle c1, Circle c2) {
		float x1 = c1.getX(), y1 = c1.getY(), r1 = c1.getRadius(),
				x2 = c2.getX(), y2 = c2.getY(), r2 = c2.getRadius();

		float d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		return (r1 - r2) * (r1 - r2) <= d || d <= (r1 + r2) * (r1 + r2);
	}

	/**
	 * @param c1 circle 1
	 * @param r1 rectangle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Circle c1, Rectangle r1) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(c1, side));
	}

	/**
	 * @param c1  circle 1
	 * @param ls1 line segment 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Circle c1, LineSegment ls1) {
		return intersects(ls1, c1);
	}

	/**
	 * Crucial method!
	 *
	 * @param ls1 line segment 1
	 * @param c1  circle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(LineSegment ls1, Circle c1) {
		float x1 = ls1.getX(), y1 = ls1.getY(), x2 = ls1.getEndX(), y2 = ls1.getEndY(),
				xc = c1.getX(), yc = c1.getY(), rc = c1.getRadius();

		float d1 = (x1 - xc) * (x1 - xc) + (y1 - yc) * (y1 - yc);
		float d2 = (x2 - xc) * (x2 - xc) + (y2 - yc) * (y2 - yc);
		if (d1 == rc * rc && d2 == rc * rc)
			return true;
		else if (d1 < rc * rc && d2 < rc * rc)
			return false;
		else if (d1 < rc * rc || d2 < rc * rc)
			return true;
		float v = (y1 - y2) * xc + (x2 - x1) * yc + x1 * y2 - x2 * y1;
		if (v * v > ((y1 - y2) * (y1 - y2) + (x2 - x1) * (x2 - x1)) * rc * rc)
			return false;
		return (xc - x1) * (x2 - x1) + (yc - y1) * (y2 - y1) > 0
				&& (xc - x2) * (x1 - x2) + (yc - y2) * (y1 - y2) > 0;
	}

	/**
	 * @param c1 circle 1
	 * @param s1 square 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Circle c1, Square s1) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(c1, side));
	}

	/**
	 * @param r1 rectangle
	 * @param c1 circle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Rectangle r1, Circle c1) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(c1, side));
	}

	/**
	 * @param r1 rectangle 1
	 * @param r2 rectangle 2
	 * @return whether they have any intersections
	 */
	static boolean intersects(Rectangle r1, Rectangle r2) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(r2, side));
	}

	/**
	 * @param r1  rectangle 1
	 * @param ls1 line segment 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Rectangle r1, LineSegment ls1) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(ls1, side));
	}

	/**
	 * @param r1 rectangle 1
	 * @param s1 square 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Rectangle r1, Square s1) {
		return Arrays.stream(r1.getSides()).anyMatch(side -> intersects(s1, side));
	}


	/**
	 * @param s1  square 1
	 * @param ls1 line segment 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Square s1, LineSegment ls1) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(ls1, side));
	}

	/**
	 * @param s1 square 1
	 * @param c1 circle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Square s1, Circle c1) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(c1, side));
	}

	/**
	 * @param s1 square 1
	 * @param r1 rectangle 1
	 * @return whether they have any intersections
	 */
	static boolean intersects(Square s1, Rectangle r1) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(r1, side));
	}

	/**
	 * @param s1 square 1
	 * @param s2 square 2
	 * @return whether they have any intersections
	 */
	static boolean intersects(Square s1, Square s2) {
		return Arrays.stream(s1.getSides()).anyMatch(side -> intersects(s2, side));
	}
}
