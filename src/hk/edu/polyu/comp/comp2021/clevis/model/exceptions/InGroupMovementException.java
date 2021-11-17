package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for some grouped shapes movements.
 *
 * @see InModelException
 */
public class InGroupMovementException extends InModelException {

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
