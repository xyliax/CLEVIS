package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;


/**
 * The abstract class for all exceptions in Clevis.
 *
 * @see InModelException
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

