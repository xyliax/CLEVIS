package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Stack;

/**
 * model class
 */
public class Clevis implements Serializable {
	private static final String FILE_NAME_DEFAULT = "model_data_filename";
	private static final String FILE_SUFFIX = ".clevis";
	private final ShapeManager shapeManager;
	private final CommandHandler commandHandler;
	private Stack<ShapeManager> historyStack;
	private Stack<ShapeManager> tempStack;
	private String txtLogFile;
	private String htmlLogFile;
	private Date createTime;

	/**
	 *
	 */
	public Clevis() {
		shapeManager = new ShapeManager();
		commandHandler = new CommandHandler();
		historyStack = new Stack<>();
		tempStack = new Stack<>();
		setCreateTime(new Date());
		System.out.println("Clevis model initialized");
		System.out.println(this);
		setLogFiles("log.html", "log.txt");
	}

	/**
	 * @param args args
	 */
	public static void main(String[] args) {
		Clevis clevis = new Clevis();
	}

	/**
	 * Generally Clevis works depending on a command sequence.
	 * <p>When a command is received, it will trandfer it to a command handler.</p>
	 * <p>The command handler processes it and set status for Clevis.</p>
	 * <p>Finaly the command handler is decoded and translated into detailed operations.</p>
	 * <p>The connection between the users and Clevis model.</p>
	 *
	 * @param aCommand the command String sent by the application
	 * @see CommandHandler
	 * @see ShapeManager
	 * @see hk.edu.polyu.comp.comp2021.clevis.Application
	 */
	public void request(String aCommand) {
		try {
			commandHandler.process(aCommand);
			String name = commandHandler.getCmd();
			Object[] arguments = commandHandler.getArguments() == null ?
					null : commandHandler.getArguments().toArray();
			Class<?>[] paraTypes = commandHandler.getParaTypes();
			if (!commandHandler.isActive())
				return;
			if (name.equals("redo")) {
				// TODO: 15/11/2021
			} else if (name.equals("undo")) {
				// TODO: 15/11/2021
			} else {
				Method method = ShapeManager.class.getDeclaredMethod(name, paraTypes);
				System.out.println(commandHandler);
				method.invoke(shapeManager, arguments);
				System.out.println("----invokes " + method);
			}
			logCommand(commandHandler);
			if (commandHandler.isUndoable()) {
				// TODO: 15/11/2021
				historyStack.push(shapeManager.getClone());
				tempStack = new Stack<>();
			}
		} catch (InvalidCommandException invalidCommandException) {
			// TODO: 15/11/2021 details
			System.out.println("invalid command!");
		} catch (ReflectiveOperationException reflectiveOperationException) {
			System.out.println("Bugs detected >>> This line is not suppose to be here!");
		} finally {
			commandHandler.reset();
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

	private void setLogFiles(String htmlName, String txtName) {
		htmlLogFile = htmlName;
		txtLogFile = txtName;
		try {
			new FileOutputStream(htmlLogFile);
			new FileOutputStream(txtLogFile);
			System.out.println(">>> Success - Finish setting up log files");
		} catch (FileNotFoundException ignored) {
			System.out.println(">>> Error - Unable to set up log files");
		}
	}

	private void logCommand(CommandHandler commandHandler) {
		String aCommand = commandHandler.toString();
		try {
			FileOutputStream txtStream = new FileOutputStream(txtLogFile, true);
			txtStream.write((aCommand + '\n').getBytes());
		} catch (IOException ioException) {
			System.out.println("Unable to log command: " + aCommand);
		}
	}

	/*
	private void test() {
		ShapeManager controller = getShapeManager();
		controller.createCircle("c1", 1, 1, 1);
		controller.createCircle("c2", 2, 2, 2);
		controller.createSquare("s1", 1, 1, 1);
		controller.createLineSegment("l1", 1, 1, 1, 1);
		controller.createGroup("g1", "c1", "c2");
		controller.createGroup("g2", "s1", "g1");
		//controller.deleteShape("g2");
		controller.listAllShapes();
		controller.list("g1", "");
	}
	public static Clevis retrieveClevisFromFile(String fileName) {
		Clevis model;
		if (fileName == null) fileName = FILE_NAME_DEFAULT;
		fileName += FILE_SUFFIX;
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream stream = new ObjectInputStream(file);
			model = (Clevis) stream.readObject();
			System.out.println(">>> Success - Retrieve Clevis model from " + fileName);
			model.setRunning(true);
		} catch (Exception ignored) {
			model = new Clevis();
			System.out.println(">>> New Clevis model created at " + model.getCreateTime());
		}
		return model;
	}
	public void saveClevisToFile(String fileName) {
		if (fileName == null) fileName = FILE_NAME_DEFAULT;
		fileName += FILE_SUFFIX;
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(this);
			System.out.println(">>> Success - Save Clevis model to " + fileName);
		} catch (Exception ignored) {
			System.out.println(">>> Error - Unable to Save Clevis model to " + fileName);
		}
	}
	*/


}