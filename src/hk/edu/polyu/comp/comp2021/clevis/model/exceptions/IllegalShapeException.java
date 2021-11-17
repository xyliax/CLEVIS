package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for some impossible shapes.
 *
 * @see InModelException
 */
public class IllegalShapeException extends InModelException {

	/**
	 * @param message exception message notes
	 */
	public IllegalShapeException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("IllegalShapeException{%s}", getMessage());
	}
}
