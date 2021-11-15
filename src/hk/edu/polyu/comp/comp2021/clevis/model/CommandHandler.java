package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.Manual;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;

import java.io.Serializable;
import java.util.ArrayList;
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
	private String cmdPart;
	private List<Object> arguments;
	/**
	 *
	 */
	CommandHandler() {
		setVar(true, false, null, null);
		System.out.println("CommandHandler initialized");
	}

	/**
	 *
	 */
	public void reset() {
		setVar(isActive(), isUndoable(), null, null);
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
		List<Object> argsPart = new ArrayList<>();
		cmdPart = scanner.next();
		switch (cmdPart) {
			case "quit" -> setVar(false, false, null, null);

			case "rectangle" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'rectangle'!", Manual.RECTANGLE);

				argsPart.add(scanner.next());
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 5)
					throw new InvalidCommandException("Too few arguments for 'rectangle'!", Manual.RECTANGLE);
				if (argsPart.size() > 5 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'rectangle'!", Manual.RECTANGLE);

				setVar(true, true, "createRectangle", argsPart);
			}

//			case "line" -> {
//				setVar();
//			}
//			case "circle" -> {
//				setVar();
//			}
//			case "square" -> {
//				setVar();
//			}
//			case "group" -> {
//				setVar();
//			}
//			case "ungroup" -> {
//				setVar();
//			}
//			case "delete" -> {
//				setVar();
//			}
//			case "boundingbox" -> {
//				setVar();
//			}
//			case "move" -> {
//				setVar();
//			}
//			case "pick-and-move" -> {
//				setVar();
//			}
//			case "intersect" -> {
//				setVar();
//			}
//			case "list" -> {
//				setVar();
//			}
//			case "listall" -> {
//				setVar();
//			}
//			case "undo" -> {
//				setVar();
//			}
//
//			case "redo" -> {
//				setVar();
//			}

			default -> throw new InvalidCommandException("Unknown command " + cmd, Manual.CLEVIS);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(cmdPart);
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

	private void setVar(boolean active, boolean undo, String cmd, List<Object> args) {
		setActive(active);
		setUndoable(undo);
		setCmd(cmd);
		setArguments(args);
	}

}
