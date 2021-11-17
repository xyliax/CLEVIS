package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.Application;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InModelException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager;
import hk.edu.polyu.comp.comp2021.clevis.view.ClevisIO;
import hk.edu.polyu.comp.comp2021.clevis.view.Manual;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Stack;

// TODO: 17/11/2021 format

/**
 * The controller class for clevis.
 * <p>The connection between the user and Clevis model.</p>
 * <p>Translates commands into methods calls.</p>
 * <p>Manipulate the models, view patterns, and the human-computer interactions.</p>
 * <p>Associates to the models by shapeManger.</p>
 * <p>Determines the prompt outputs by Manual.</p>
 * <p>Accepts user commands from Clevis.</p>
 * <p>Using the reflection technology.</p>
 *
 * @see ShapeManager
 * @see CommandHandler
 * @see Application
 * @see Manual
 */
public class Clevis {
	private final CommandHandler commandHandler;
	private final Stack<ShapeManager> historyStack;
	private final ClevisIO clevisIO;
	private ShapeManager shapeManager;
	private Stack<ShapeManager> tempStack;
	private Date createTime;


	/**
	 * Constructor of Clevis.
	 *
	 * @param htmlName html log file name
	 * @param txtName  txt log file name
	 * @param io       ClevisIO to be registered in this controller
	 */
	public Clevis(ClevisIO io) {
		clevisIO = io;
		shapeManager = new ShapeManager(clevisIO);
		clevisIO.printSystemNotice("ShapeManager initialized");
		commandHandler = new CommandHandler();
		clevisIO.printSystemNotice("CommandHandler initialized");
		historyStack = new Stack<>();
		tempStack = new Stack<>();
		setCreateTime(new Date());
		clevisIO.printSystemNotice("Clevis model initialized");
		clevisIO.println(this);
	}

	/**
	 * Generally Clevis works depending on a command sequence.
	 * <p>When a command is received, it will trandfer it to a command handler.</p>
	 * <p>The command handler processes it and set status for Clevis.</p>
	 * <p>Finaly the command handler is decoded and translated into detailed operations.</p>
	 * <p>The connection between the users and Clevis model.</p>
	 *
	 * @param aCommand the command String sent by the application
	 * @throws InvalidCommandException when the command is invalid
	 * @throws InModelException        when exceptions are thrown in clevis model
	 * @see CommandHandler
	 * @see ShapeManager
	 * @see Application
	 */
	public void request(String aCommand) throws InModelException, InvalidCommandException {
		try {
			commandHandler.process(aCommand);
			String name = commandHandler.getCmd();
			List<Object> arguments = commandHandler.getArguments();

			clevisIO.printRunningMessage(String.format("Executing %s %s",
					commandHandler, commandHandler.isUndoable() ? "(undoable)" : ""));

			if (commandHandler.isUndoable()) {
				historyStack.push(shapeManager.getClone());
				tempStack = new Stack<>();
			}

			if (!commandHandler.isActive())
				return;

			if (name.equals("redo")) {
				if (!tempStack.empty()) {
					historyStack.push(shapeManager.getClone());
					shapeManager = tempStack.pop();
					clevisIO.printRunningMessage("Successfully undo operation!");
				}

			} else if (name.equals("undo")) {
				if (!historyStack.empty()) {
					tempStack.push(shapeManager.getClone());
					shapeManager = historyStack.pop();
					clevisIO.printRunningMessage("Successfully undo operation!");
				}
			} else {
				Method method = ShapeManager.class.getDeclaredMethod(name, List.class);
				clevisIO.printRunningMessage(String.format("Invoking shapeManager.%s(%s)",
						method.getName(), arguments));
				method.invoke(shapeManager, arguments);
			}
			clevisIO.logCommand(commandHandler.toString());
		} catch (ReflectiveOperationException reflectiveOperationException) {
			if (reflectiveOperationException.getCause() instanceof InModelException)
				throw (InModelException) reflectiveOperationException.getCause();
			else
				reflectiveOperationException.printStackTrace();
		} finally {
			commandHandler.reset();
		}
	}

	@Override
	public String toString() {
		return super.toString() + "Created at " + getCreateTime();
	}

	/**
	 * Getter of createTime.
	 *
	 * @return the creating time of this Clevis session
	 * @see #createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Setter of createTime.
	 *
	 * @param createTime the time to be set as creating time
	 * @see #createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public ShapeManager getShapeManager() {
		return shapeManager;
	}

	/**
	 * The running status is determined by the command handler.
	 *
	 * @return true if Clevis is running, otherwise false
	 * @see CommandHandler#isActive()
	 */
	public boolean isRunning() {
		return commandHandler.isActive();
	}
}