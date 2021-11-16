package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for illegal names.
 *
 * @see ClevisException
 * @see hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager
 */
public class IllegalNameException extends ClevisException {
	/**
	 * @param message exception message notes
	 */
	public IllegalNameException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("DuplicateNameException{%s}", getMessage());
	}
}
