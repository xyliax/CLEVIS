package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.Application;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.view.Manual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The class for processing a command from the application, i.e. Clevis.
 * <p>The connection between application class and model class.</p>
 * <p>It analyze whether a command string is valid semantically.</p>
 * <p>Set its status to let ClevisModel understand the command is undoable or not.</p>
 * <p>It is not able to handle invalid exceptions, so command handlers will throw it to ClevisModel.</p>
 *
 * @see Application
 * @see Clevis
 */
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
	}

	private void setVar(boolean active, boolean undo, String cmd, List<Object> args) {
		setActive(active);
		setUndoable(undo);
		setCmd(cmd);
		setArguments(args);
	}

	/**
	 *
	 */
	public void reset() {
		setVar(isActive(), isUndoable(), null, null);
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
	 * Process on a command string, and set status.
	 * <p>This is the core of the command handler, it determines if a command is valid.</p>
	 *
	 * @param candidate the full command to be processed
	 * @throws InvalidCommandException when the command is invalid
	 * @see #setVar(boolean, boolean, String, List)
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

			case "line" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'line'!", Manual.LINE);

				argsPart.add(scanner.next());
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 5)
					throw new InvalidCommandException("Too few arguments for 'line'!", Manual.RECTANGLE);
				if (argsPart.size() > 5 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'line'!", Manual.RECTANGLE);

				setVar(true, true, "createLineSegment", argsPart);
			}

			case "circle" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'circle'!", Manual.CIRCLE);

				argsPart.add(scanner.next());
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 4)
					throw new InvalidCommandException("Too few arguments for 'circle'!", Manual.CIRCLE);
				if (argsPart.size() > 4 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'circle'!", Manual.CIRCLE);

				setVar(true, true, "createCircle", argsPart);
			}

			case "square" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'square'!", Manual.SQUARE);

				argsPart.add(scanner.next());
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 4)
					throw new InvalidCommandException("Too few arguments for 'square'!", Manual.SQUARE);
				if (argsPart.size() > 4 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'square'!", Manual.SQUARE);

				setVar(true, true, "createSquare", argsPart);
			}

			case "group" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'group'!", Manual.GROUP);

				argsPart.add(scanner.next());
				if (!scanner.hasNext())
					throw new InvalidCommandException("Too few arguments for 'group'!", Manual.GROUP);

				while (scanner.hasNext())
					argsPart.add(scanner.next());
				setVar(true, true, "createGroup", argsPart);
			}

			case "ungroup" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'ungroup'!", Manual.UNGROUP);

				argsPart.add(scanner.next());
				if (scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'ungroup'!", Manual.UNGROUP);

				setVar(true, true, "disbandGroup", argsPart);
			}

			case "delete" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'delete'!", Manual.DELETE);

				argsPart.add(scanner.next());
				if (scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'delete'!", Manual.DELETE);

				setVar(true, true, "deleteShape", argsPart);
			}

			case "boundingbox" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'boundingbox'!", Manual.BOUNDINGBOX);

				argsPart.add(scanner.next());
				if (scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'boundingbox'!", Manual.BOUNDINGBOX);

				setVar(true, false, "boundingbox", argsPart);
			}

			case "move" -> {
				if (!scanner.hasNext())
					throw new InvalidCommandException("Missing [-n] for 'move'!", Manual.MOVE);

				argsPart.add(scanner.next());
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 3)
					throw new InvalidCommandException("Too few arguments for 'move'!", Manual.MOVE);
				if (argsPart.size() > 3 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'move'!", Manual.MOVE);

				setVar(true, true, "moveShape", argsPart);
			}

			case "pick-and-move" -> {
				while (scanner.hasNextFloat())
					argsPart.add(scanner.nextFloat());

				if (argsPart.size() < 4)
					throw new InvalidCommandException("Too few arguments for 'pick-and-move'!", Manual.PICK_AND_MOVE);
				if (argsPart.size() > 4 || scanner.hasNext())
					throw new InvalidCommandException("Too many arguments for 'pick-and-move'!", Manual.PICK_AND_MOVE);

				setVar(true, true, "pickMoveShape", argsPart);
			}

			case "intersect" -> {
				while (scanner.hasNext())
					argsPart.add(scanner.next());

				if (argsPart.size() < 2)
					throw new InvalidCommandException("Too few arguments for 'move'!", Manual.INTERSECT);
				if (argsPart.size() > 2)
					throw new InvalidCommandException("Too many arguments for 'move'!", Manual.INTERSECT);

				setVar(true, false, "hasIntersection", argsPart);
			}

			case "list" -> {
				while (scanner.hasNext())
					argsPart.add(scanner.next());

				if (argsPart.size() < 1)
					throw new InvalidCommandException("Too few arguments for 'list'!", Manual.LIST);
				if (argsPart.size() > 1)
					throw new InvalidCommandException("Too many arguments for 'list'!", Manual.LIST);

				setVar(true, false, "listOneShape", argsPart);
			}

			case "listAll" -> setVar(true, false, "listAllShapes", null);

			case "undo" -> setVar(true, false, "undo", null);

			case "redo" -> setVar(true, false, "redo", null);

			case "show" -> setVar(true, false, "show", null);

			default -> throw new InvalidCommandException("Unknown command " + cmdPart, Manual.CLEVIS);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(cmdPart);
		if (arguments != null) {
			for (Object arg : arguments)
				stringBuilder.append(" ").
						append(arg instanceof Float ? String.format("%.2f", arg) : arg);
		}
		return stringBuilder.toString();
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
}