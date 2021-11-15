package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
// TODO: 15/11/2021 NOT FINISHED!
class CommandHandler implements Serializable {
	private boolean active;
	private boolean undoable;
	private String cmd;
	private List<Object> arguments;
	private Class<?>[] paraTypes;

	/**
	 *
	 */
	CommandHandler() {
		setVar(true, false, null, null, null);
		System.out.println("CommandHandler initialized");
	}

	/**
	 *
	 */
	public void reset() {
		setVar(isActive(), isUndoable(), null, null, null);
	}


	/**
	 * Process on a command string, and set status.
	 * <p>This is the core of the command handler, it determines if a command is valid.</p>
	 *
	 * @param candidate the full command to be processed
	 * @throws InvalidCommandException when the command is invalid
	 * @see #setVar(boolean, String, List, Class[])
	 */
	public void process(String candidate) throws InvalidCommandException {
		Scanner scanner = new Scanner(candidate);
		String cmdPart = scanner.next();
		/*switch (cmdPart) {
			case "quit" -> setVar(false, false, null, null, null);

			case "rectangle" -> {
				setVar();
			}
			//add missing code
			case "line" -> {
				setVar();
			}
			//add missing code
			case "circle" -> {
				setVar();
			}
			//add missing code
			case "square" -> {
				setVar();
			}
			//add missing code
			case "group" -> {
				setVar();
			}
			//add missing code
			case "ungroup" -> {
				setVar();
			}
			//add missing code
			case "delete" -> {
				setVar();
			}
			//add missing code
			case "boundingbox" -> {
				setVar();
			}
			//add missing code
			case "move" -> {
				setVar();
			}
			//add missing code
			case "pick-and-move" -> {
				setVar();
			}
			//add missing code
			case "intersect" -> {
				setVar();
			}
			//add missing code
			case "list" -> {
				setVar();
			}
			//add missing code
			case "listall" -> {
				setVar();
			}
			//add missing code
			case "undo" -> {
				setVar();
			}

			case "redo" -> {
				setVar();
			}

			default -> throw new InvalidCommandException("Unknown command " + cmd);
		}*/
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(cmd);
		for (Object arg : arguments)
			stringBuilder.append(" ").append(arg);
		return stringBuilder.toString();
	}

	/**
	 * Getter of active. Determine if Clevis is running.
	 *
	 * @return whether it is activated
	 * @see #active
	 */
	boolean isActive() {
		return active;
	}

	private void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Getter of undoable.
	 *
	 * @return whether the command is undoable
	 */
	boolean isUndoable() {
		return undoable;
	}

	private void setUndoable(boolean undoable) {
		this.undoable = undoable;
	}

	/**
	 * Getter of cmd.
	 *
	 * @return the cmd part of this command as a List
	 * @see #cmd
	 */
	String getCmd() {
		return cmd;
	}

	private void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * Getter of arguments.
	 *
	 * @return the arguments part of this command
	 * @see #arguments
	 */
	List<Object> getArguments() {
		return arguments;
	}

	private void setArguments(List<Object> arguments) {
		this.arguments = arguments;
	}

	/**
	 * Getter of paraTypes.
	 *
	 * @return the parameter types of this command
	 * @see #paraTypes
	 */
	Class<?>[] getParaTypes() {
		return paraTypes;
	}

	private void setParaTypes(Class<?>[] paraTypes) {
		this.paraTypes = paraTypes;
	}

	private void setVar(boolean active, boolean undo, String cmd, List<Object> args, Class<?>[] types) {
		setActive(active);
		setUndoable(undo);
		setCmd(cmd);
		setArguments(args);
		setParaTypes(types);
	}

}
