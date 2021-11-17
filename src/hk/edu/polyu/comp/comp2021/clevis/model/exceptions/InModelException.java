package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * The abstract class for exceptions in clevis model.
 *
 * @see ClevisException
 * @see IllegalNameException
 * @see InGroupMovementException
 * @see ShapeOutOfMapException
 * @see IllegalShapeException
 */
public abstract class InModelException extends ClevisException {

	/**
	 * @param message exception message notes
	 */
	public InModelException(String message) {
		super(message);
	}
}
