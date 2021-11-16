package hk.edu.polyu.comp.comp2021.clevis;

/**
 *
 */
public enum Manual {
	/**
	 * display all
	 */
	CLEVIS("""
			........................CLEVIS-USER-MANUAL........................
			COMMAND:     rectangle n x y w h
			Description: It is used to create a rectangle which has its width
			             and height in a certain location.
			ARGS:        [n]: -String- name of the rectangle to be created
			                         [x]: -float- the abscissa of vertex of the upper left
			                             corner of the rectangle
			                         [y]: -float- the ordinate of vertex of the upper left
			                             corner of the rectangle
			                         [w]: -float- the width of the rectangle
			                         [h]: -float- the height of the rectangle
			                         
			            COMMAND:     line n x1 y1 x2 y2
			            Description: It is used to create a new line segment which has its
			                         two ends’ location
			            ARGS:        [n]: -String-  name of the line segment
			                         [x1]: -float-  the abscissa of the first end
			                         [y1]: -float-  the ordinate of the first end
			                         [x2]: -float-  the abscissa of the second end
			                         [y2]: -float-  the ordinate of the second en
			                         
			            COMMAND:     circle n x y r
			            Description: It is used to create a new circle which has its
			                         radius and the center location
			            ARGS         [n]: -String-  name of the circle
			                         [x]: -float-  the abscissa of the center
			                         [y]: -float-  the ordinate of the center
			                         [r]: -float-  the radius length of the circle
			                         
			            COMMAND:     square n x y l
			            Description: It is used to create a square which has its side
			                         length in a certain location
			            ARGS         [n]: -String-  name of the square
			                         [x]: -float- the abscissa of vertex of the upper left
			                              corner of the square
			                         [y]: -float- the ordinate of vertex of the upper left
			                              corner of the square
			                         [l]: -float- the side length of the square
			                         
			            COMMAND:     group n n1 n2 ...
			            Description: It is used to group existing shapes and they cannot
			                         be controlled by following command until the group
			                         ungrouped.
			            ARGS         [n]: -String-  the group name
			                         [n1]: -String- the first shape’s name
			                         [n2]: -String- the second shape’s name
			                         
			            COMMAND:     ungroup n
			            Description: It is used to ungroup an existing group 
			            ARGS:        [n]: -String- the name of an existing group to be 
			                              ungrouped 
			                         
			            COMMAND:     delete n 
			            Description: It is used to delete a shape, which can be a single
			                         shape or a group’s whole members 
			            ARGS:        [n]: -String- the name of an existing group to be 
			                              deleted 
			                              
			            COMMAND:     boundingbox n 
			            Description: It is used to create a circumscribed rectangle of 
			                         an existing shape which could be a group. 
			            ARGS:        [n] -String- the shape name needs to be bounded 
			            
			            COMMAND:     move n dx dy 
			            Description: It is used to move a shape horizontally and 
			                         vertically which can be a group. 
			            ARGS:        [n]: -String- the shape name needs to be bounded 
			                         [dx]: -float- the horizontal length of movement 
			                         [dy]: -float- the vertical length of movement
			                         
			            COMMAND:     pick-and-move x y dx dy 
			            Description: It is used to choose a shape that contains the 
			                         input point and move the shape horizontally and 
			                         vertically. 
			            ARGS:        [x]: -float- the abscissa of the point  
			                         [y]: -float- the ordinate of the point  
			                         [dx]: -float- the horizontal length of movement 
			                         [dy]: -float- the vertical length of movement 
			                         
			            COMMAND:     intersect n1 n2
			            Description: It is used to report whether two shapes outlines 
			                         share any common points or not.
			            ARGS:        [n1]: -String- the name of one shape
			                         [n2]: -String- the name of the other shape
			                         
			            COMMAND:     list n
			            Description: It is used to list the types of information used 
			                         to construct the shape or all the shapes directly
			                         contained in the group
			            ARGS:        [n]: -String- the shape name to be list
			            
			            COMMAND:     listAll
			            Description: It is used to list the basic information about all
			                         the shapes that have been drawn in decreasing 
			                         Z-order and using indentation to indicate the 
			                         containing relation between group shapes and their 
			                         component shapes.
			            
			            COMMAND:     quit
			            Description: It is used to terminate the execution of Clevis
			"""),

	/**
	 *
	 */
	TITLE("""
			........................CLEVIS-USER-MANUAL........................
			"""),
	/**
	 *
	 */
	RECTANGLE("""
			COMMAND:     rectangle n x y w h 
			Description: It is used to create a rectangle which has its width 
			             and height in a certain location. 
			ARGS:        [n]: -String- name of the rectangle to be created 
			                         [x]: -float- the abscissa of vertex of the upper left
			                             corner of the rectangle 
			                         [y]: -float- the ordinate of vertex of the upper left 
			                             corner of the rectangle 
			                         [w]: -float- the width of the rectangle 
			                         [h]: -float- the height of the rectangle 
			"""
	),
	/**
	 *
	 */
	LINE("""
			COMMAND:     line n x1 y1 x2 y2 
			            Description: It is used to create a new line segment which has its
			                         two ends’ location 
			            ARGS:        [n]: -String-  name of the line segment 
			                         [x1]: -float-  the abscissa of the first end 
			                         [y1]: -float-  the ordinate of the first end 
			                         [x2]: -float-  the abscissa of the second end 
			                         [y2]: -float-  the ordinate of the second end
			"""),
	/**
	 *
	 */
	CIRCLE("""
			COMMAND:     circle n x y r 
			            Description: It is used to create a new circle which has its 
			                         radius and the center location
			            ARGS         [n]: -String-  name of the circle
			                         [x]: -float-  the abscissa of the center 
			                         [y]: -float-  the ordinate of the center 
			                         [r]: -float-  the radius length of the circle
			"""),
	/**
	 *
	 */
	SQUARE("""
			COMMAND:     square n x y l 
			            Description: It is used to create a square which has its side 
			                         length in a certain location 
			            ARGS         [n]: -String-  name of the square 
			                         [x]: -float- the abscissa of vertex of the upper left
			                              corner of the square 
			                         [y]: -float- the ordinate of vertex of the upper left
			                              corner of the square 
			                         [l]: -float- the side length of the square 
			"""),
	/**
	 *
	 */
	GROUP("""
			COMMAND:     group n n1 n2 … 
			            Description: It is used to group existing shapes and they cannot 
			                         be controlled by following command until the group 
			                         ungrouped. 
			            ARGS         [n]: -String-  the group name 
			                         [n1]: -String- the first shape’s name 
			                         [n2]: -String- the second shape’s name
			"""),
	/**
	 *
	 */
	UNGROUP("""
			OMMAND:     ungroup n 
			            Description: It is used to ungroup an existing group 
			            ARGS:        [n]: -String- the name of an existing group to be 
			                              ungrouped 
			"""),
	/**
	 *
	 */
	DELETE("""
			COMMAND:     delete n 
			            Description: It is used to delete a shape, which can be a single
			                         shape or a group’s whole members 
			            ARGS:        [n]: -String- the name of an existing group to be 
			                              deleted
			"""),
	/**
	 *
	 */
	BOUNDINGBOX("""
			COMMAND:     boundingbox n 
			            Description: It is used to create a circumscribed rectangle of 
			                         an existing shape which could be a group. 
			            ARGS:        [n] -String- the shape name needs to be bounded 
			"""),
	/**
	 *
	 */
	MOVE("""
			COMMAND:     move n dx dy 
			            Description: It is used to move a shape horizontally and 
			                         vertically which can be a group. 
			            ARGS:        [n]: -String- the shape name needs to be bounded 
			                         [dx]: -float- the horizontal length of movement 
			                         [dy]: -float- the vertical length of movement
			"""),
	/**
	 *
	 */
	PICK_AND_MOVE("""
			COMMAND:     pick-and-move x y dx dy 
			            Description: It is used to choose a shape that contains the 
			                         input point and move the shape horizontally and 
			                         vertically. 
			            ARGS:        [x]: -float- the abscissa of the point  
			                         [y]: -float- the ordinate of the point  
			                         [dx]: -float- the horizontal length of movement 
			                         [dy]: -float- the vertical length of movement
			"""),
	/**
	 *
	 */
	INTERSECT("""
			COMMAND:     intersect n1 n2
			            Description: It is used to report whether two shapes outlines 
			                         share any common points or not.
			            ARGS:        [n1]: -String- the name of one shape
			                         [n2]: -String- the name of the other shape
			"""),
	/**
	 *
	 */
	LIST("""
			COMMAND:     list n
			            Description: It is used to list the types of information used 
			                         to construct the shape or all the shapes directly
			                         contained in the group
			            ARGS:        [n]: -String- the shape name to be list
			"""),
	/**
	 *
	 */
	LISTALL("""
			            COMMAND:     listAll
			            Description: It is used to list the basic information about all
			                         the shapes that have been drawn in decreasing 
			                         Z-order and using indentation to indicate the 
			                         containing relation between group shapes and their 
			                         component shapes.
			"""),
	/**
	 *
	 */
	QUIT("""
			            COMMAND:     quit
			            Description: It is used to terminate the execution of Clevis
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
