package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for illegal names.
 *
 * @see InModelException
 */
public class IllegalNameException extends InModelException {

	/**
	 * @param message exception message notes
	 */
	public IllegalNameException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("IllegalNameException{%s}", getMessage());
	}
}
