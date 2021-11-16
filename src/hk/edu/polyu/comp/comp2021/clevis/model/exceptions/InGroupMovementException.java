package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for some grouped shapes movements.
 *
 * @see ClevisException
 * @see hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager
 */
public class InGroupMovementException extends ClevisException {
	/**
	 * @param message exception message notes
	 */
	public InGroupMovementException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("InGroupMovementException{%s}", getMessage());
	}
}
