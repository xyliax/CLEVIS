package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;


/**
 * The abstract class for all exceptions in Clevis.
 *
 * @see IllegalNameException
 * @see InGroupMovementException
 * @see ShapeOutOfMapException
 * @see InvalidCommandException
 */
public abstract class ClevisException extends Exception {

	/**
	 * @param message exception message notes
	 */
	public ClevisException(String message) {
		super(message);
	}
}

