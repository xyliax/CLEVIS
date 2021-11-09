package hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox;

// TODO: 15/11/2021 write helper 
/**
 *
 */
abstract class ClevisException extends Exception {
	/**
	 * When a ClevisException is catched, Clevis will show the user how to fix it.
	 */
	protected String helper;

	/**
	 * @param message exception message notes
	 */
	public ClevisException(String message) {
		super(message);
	}
}

/**
 *
 */
class ShapeOutOfMapException extends ClevisException {
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

/**
 *
 */
class IllegalNameException extends ClevisException {
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

/**
 *
 */
class InGroupMovementException extends ClevisException {
	/**
	 * @param message exception message notes
	 */
	InGroupMovementException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return String.format("InGroupMovementException{%s}", getMessage());
	}
}
