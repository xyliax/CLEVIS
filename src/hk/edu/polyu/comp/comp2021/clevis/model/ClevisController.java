package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ClevisException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Stack;

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
 * @see Clevis
 * @see hk.edu.polyu.comp.comp2021.clevis.Manual
 */
public class ClevisController implements Serializable {
	private final CommandHandler commandHandler;
	private ShapeManager shapeManager;
	private final Stack<ShapeManager> historyStack;
	private Stack<ShapeManager> tempStack;
	private String htmlLogFile;
	private String txtLogFile;
	private Date createTime;

	/**
	 * Contructor of Clevis.
	 * <p>This constructor set log files using default file names.</p>
	 */
	public ClevisController() {
		this("log.html", "log.txt");
	}

	/**
	 * Constructor of Clevis.
	 *
	 * @param htmlName html log file name
	 * @param txtName  txt log file name
	 * @see #setLogFiles(String, String)
	 */
	public ClevisController(String htmlName, String txtName) {
		shapeManager = new ShapeManager();
		commandHandler = new CommandHandler();
		historyStack = new Stack<>();
		tempStack = new Stack<>();
		setCreateTime(new Date());
		System.out.println(">>> Clevis model initialized.");
		System.out.println(this);
		setLogFiles(htmlName, txtName);
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
	 * @see CommandHandler
	 * @see ShapeManager
	 * @see Clevis
	 */
	public void request(String aCommand) throws ClevisException {
		try {
			commandHandler.process(aCommand);
			String name = commandHandler.getCmd();
			List<Object> arguments = commandHandler.getArguments();
			System.out.printf("----Executing %s %s%n",
					commandHandler, commandHandler.isUndoable() ? "(undoable)" : "");
			if (commandHandler.isUndoable()) {
				historyStack.push(shapeManager.getClone());
				tempStack = new Stack<>();
			}
			if (!commandHandler.isActive())
				return;
			if (name.equals("redo")) {
				if (!tempStack.empty())
					shapeManager = tempStack.pop();
			} else if (name.equals("undo")) {
				if (!historyStack.empty()) {
					tempStack.push(shapeManager.getClone());
					shapeManager = historyStack.pop();
					System.out.println("--------Successfully undo operation");
				}
			} else {
				Method method = ShapeManager.class.getDeclaredMethod(name, List.class);
				System.out.printf("--------Invoking shapeManager.%s(%s)%n", method.getName(), arguments);
				method.invoke(shapeManager, arguments);
			}
			logCommand(commandHandler);
		} catch (ReflectiveOperationException reflectiveOperationException) {
			reflectiveOperationException.printStackTrace();
			throw (ClevisException) reflectiveOperationException.getCause();
		} finally {
			commandHandler.reset();
		}
	}


	private void setLogFiles(String htmlName, String txtName) {
		htmlLogFile = htmlName;
		txtLogFile = txtName;
		try {
			new FileOutputStream(htmlLogFile);
			new FileOutputStream(txtLogFile);
			System.out.println(">>> Success - Finish setting up log files");
			System.out.printf("""
					>>> All valid operations will be logged in to the followings
							'%s'
							'%s'
					%n""", htmlName, txtName);
		} catch (FileNotFoundException ignored) {
			System.out.println(">>> Error - Unable to set up log files");
		}
	}


	private void logCommand(CommandHandler commandHandler) {
		// TODO: 15/11/2021 Migrate html code!!!
		String aCommand = commandHandler.toString();
		try {
			FileOutputStream txtStream = new FileOutputStream(txtLogFile, true);
			txtStream.write((aCommand + '\n').getBytes());
		} catch (IOException ioException) {
			System.out.println("Unable to log command: " + aCommand);
		}
	}


	@Override
	public String toString() {
		return super.toString() + "Created at " + getCreateTime();
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
}