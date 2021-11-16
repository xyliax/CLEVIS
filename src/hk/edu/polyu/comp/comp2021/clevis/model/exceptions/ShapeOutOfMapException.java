package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The exception class for some shapes being out of map.
 *
 * @see ClevisException
 * @see hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager
 */
public class ShapeOutOfMapException extends ClevisException {
	/**
	 * @param message exception message notes
	 */
	public ShapeOutOfMapException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("ShapeOutOfMapException{%s}", getMessage());
	}
}
