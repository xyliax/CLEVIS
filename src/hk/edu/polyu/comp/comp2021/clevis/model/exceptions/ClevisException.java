package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

// TODO: 15/11/2021 write helper 
/**
 *
 */
public abstract class ClevisException extends Exception {
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

