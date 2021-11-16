package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

import hk.edu.polyu.comp.comp2021.clevis.Manual;


/**
 * The exception class for invalid commands.
 *
 * @see ClevisException
 * @see hk.edu.polyu.comp.comp2021.clevis.Clevis
 */
public class InvalidCommandException extends ClevisException {
	private final Manual cmd;

	/**
	 * @param message exception message notes
	 * @param cmd     the problem command
	 */
	public InvalidCommandException(String message, Manual cmd) {
		super(message);
		this.cmd = cmd;
	}

	/**
	 * Getter of cmd.
	 *
	 * @return cmd as enum Munual
	 */
	public Manual getCmd() {
		return cmd;
	}

	@Override
	public String toString() {
		return String.format("InvalidCommandException{%s}", getMessage());
	}
}