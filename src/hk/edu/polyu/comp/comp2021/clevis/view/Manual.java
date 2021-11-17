package hk.edu.polyu.comp.comp2021.clevis.view;


/**
 * enum class for user manual.
 */
public enum Manual {

	/**
	 * CLEVIS
	 */
	CLEVIS("""
			.................................CLEVIS-USER-MANUAL.................................
						
					COMMAND:     rectangle -n -x -y -w -h
					Description: Create a rectangle
					ARGS:        [n]: -String- name of the rectangle
								 [x]: -float-  the abscissa of the top left corner
								 [y]: -float-  the ordinate of the top left corner
								 [w]: -float-  the width of the rectangle
								 [h]: -float-  the height of the rectangle
											 
					COMMAND:     line -n -x1 -y1 -x2 -y2
					Description: Create a line segment
					ARGS:        [n]:  -String- name of the line segment
								 [x1]: -float-  the abscissa of the first end
								 [y1]: -float-  the ordinate of the first end
								 [x2]: -float-  the abscissa of the second end
								 [y2]: -float-  the ordinate of the second end
											 
					COMMAND:     circle -n -x -y -r
					Description: Create a circle
					ARGS:        [n]: -String-  name of the circle
								 [x]: -float-   the abscissa of the center
								 [y]: -float-   the ordinate of the center
								 [r]: -float-   the radius of the circle
											 
					COMMAND:     square -n -x -y -l
					Description: Create a square
					ARGS:        [n]: -String-  name of the square
								 [x]: -float-   the abscissa of the top left corner
								 [y]: -float-   the ordinate of the top left corner
								 [l]: -float-   the length of the square
											 
					COMMAND:     group -n [-n...]
					Description: Create a group consisting of several existing shapes
					ARGS:        [n]:    -String- the group name
								 [n...]: -String- the members' name
											 
					COMMAND:     ungroup -n
					Description: Disband a group
					ARGS:        [n]: -String- the name of the shape
											 
					COMMAND:     delete -n
					Description: Delete a shape
					ARGS:        [n]: -String- name of the shape
												  
					COMMAND:     boundingbox -n
					Description: Get the bounding box of a shape
					ARGS:        [n] -String- the shape name needs to be bounded
								
					COMMAND:     move -n -dx -dy
					Description: Move a shape
					ARGS:        [n]: -String- name of the shape to be moved
								 [dx]: -float- the horizontal length of movement
								 [dy]: -float- the vertical length of movement
											 
					COMMAND:     pick-and-move -x -y -dx -dy
					Description: Pick a shape at a position and move it
					ARGS:        [x]:  -float- the abscissa of the point
								 [y]:  -float- the ordinate of the point
								 [dx]: -float- the horizontal length of movement
								 [dy]: -float- the vertical length of movement
											 
					COMMAND:     intersect -n1 -n2
					Description: Determine whether two shapes have any intersections
					ARGS:        [n1]: -String- the name of one shape
								 [n2]: -String- the name of the other shape
											 
					COMMAND:     list -n
					Description: List a shape's detail
					ARGS:        [n]: -String- name of the shape
								
					COMMAND:     listAll
					Description: List all shapes, using indentations to indicate grouping relationships
					
					COMMAND:     quit
					Description: Terminate the Clevis session
			"""),

	/**
	 * RECTANGLE
	 */
	RECTANGLE("""
					COMMAND:     rectangle -n -x -y -w -h
					Description: Create a rectangle
					ARGS:        [n]: -String- name of the rectangle
								 [x]: -float-  the abscissa of the top left corner
								 [y]: -float-  the ordinate of the top left corner
								 [w]: -float-  the width of the rectangle
								 [h]: -float-  the height of the rectangle
			"""
	),

	/**
	 * LINE
	 */
	LINE("""
					COMMAND:     line -n -x1 -y1 -x2 -y2
					Description: Create a line segment
					ARGS:        [n]:  -String- name of the line segment
								 [x1]: -float-  the abscissa of the first end
								 [y1]: -float-  the ordinate of the first end
								 [x2]: -float-  the abscissa of the second end
								 [y2]: -float-  the ordinate of the second end
			"""),

	/**
	 * CIRCLE
	 */
	CIRCLE("""
					COMMAND:     circle -n -x -y -r
					Description: Create a circle
					ARGS:        [n]: -String-  name of the circle
								 [x]: -float-   the abscissa of the center
								 [y]: -float-   the ordinate of the center
								 [r]: -float-   the radius of the circle
			"""),

	/**
	 * SQUARE
	 */
	SQUARE("""
					COMMAND:     square -n -x -y -l
					Description: Create a square
					ARGS:        [n]: -String-  name of the square
								 [x]: -float-   the abscissa of the top left corner
								 [y]: -float-   the ordinate of the top left corner
								 [l]: -float-   the length of the square
			"""),

	/**
	 * GROUP
	 */
	GROUP("""
					COMMAND:     group -n [-n...]
					Description: Create a group consisting of several existing shapes
					ARGS:        [n]:    -String- the group name
								 [n...]: -String- the members' name
			"""),

	/**
	 * UNGROUP
	 */
	UNGROUP("""
					COMMAND:     ungroup -n
					Description: Disband a group
					ARGS:        [n]: -String- the name of the shape
			"""),

	/**
	 * DELETE
	 */
	DELETE("""
					COMMAND:     delete -n
					Description: Delete a shape
					ARGS:        [n]: -String- name of the shape
			"""),

	/**
	 * BOUNDINGBOX
	 */
	BOUNDINGBOX("""
					COMMAND:     boundingbox -n
					Description: Get the bounding box of a shape
					ARGS:        [n] -String- the shape name needs to be bounded
			"""),

	/**
	 * MOVE
	 */
	MOVE("""
					COMMAND:     move -n -dx -dy
					Description: Move a shape
					ARGS:        [n]: -String- name of the shape to be moved
								 [dx]: -float- the horizontal length of movement
								 [dy]: -float- the vertical length of movement
			"""),
	/**
	 * PICK_AND_MOVE
	 */
	PICK_AND_MOVE("""
					COMMAND:     pick-and-move -x -y -dx -dy
					Description: Pick a shape at a position and move it
					ARGS:        [x]:  -float- the abscissa of the point
								 [y]:  -float- the ordinate of the point
								 [dx]: -float- the horizontal length of movement
								 [dy]: -float- the vertical length of movement
			"""),

	/**
	 * INTERSECT
	 */
	INTERSECT("""
					COMMAND:     intersect -n1 -n2
					Description: Determine whether two shapes have any intersections
					ARGS:        [n1]: -String- the name of one shape
								 [n2]: -String- the name of the other shape
			"""),

	/**
	 * LIST
	 */
	LIST("""
					COMMAND:     list -n
					Description: List a shape's detail
					ARGS:        [n]: -String- name of the shape
			"""),

	/**
	 * LISTALL
	 */
	LISTALL("""
					COMMAND:     listAll
					Description: List all shapes, using indentations to indicate grouping relationships
			"""),

	/**
	 * QUIT
	 */
	QUIT("""
					COMMAND:     quit
					Description: Terminate the Clevis session
			""");

	private String text;

	Manual(String text) {
		this.setText(text);
	}

	/**
	 * Getter of text
	 *
	 * @return the user manual
	 */
	public String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}
}
