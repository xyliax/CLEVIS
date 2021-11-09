package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * model class
 */
public class Clevis implements Serializable {
	private static final String FILE_NAME_DEFAULT = "model_data_filename";
	private static final String FILE_SUFFIX = ".clevis";
	private final ShapeManager shapeManager;
	private final CommandHandler commandHandler;
	private String txtLogFile;
	private String htmlLogFile;
	private Date createTime;


	/**
	 *
	 */
	public Clevis() {
		shapeManager = new ShapeManager();
		commandHandler = new CommandHandler();
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

	private void logCommand(String aCommand) throws IOException {
		FileOutputStream txtStream = new FileOutputStream(txtLogFile, true);
		txtStream.write((aCommand + "\n").getBytes());
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

	private void setRunning(boolean status) {
		commandHandler.setActive(true);
	}


	/**
	 * Generally Clevis works depending on a command sequence.
	 * <p>When a command is received, it will trandfer it to a command handler.</p>
	 * <p>The command handler processes it and set status for Clevis.</p>
	 * <p>Finaly the command handler is decoded and translated into detailed operations.</p>
	 *
	 * @param aCommand the command String sent by the application
	 * @see CommandHandler
	 * @see #commandHandler
	 */
	// FIXME: reflection!
	public void request(String aCommand) {
		commandHandler.process(aCommand);
		String cmd = commandHandler.getCmd();
		List<Object> args = commandHandler.getArguments();
		if (commandHandler.isCmdValid()) {
			if (commandHandler.isAllValid()) {
				// TODO: 11/11/2021  
				try {
					logCommand(aCommand);
				} catch (IOException ignored) {
					System.out.println(">>> Error - " + aCommand + " fails to log");
				}
			} else {
				System.out.printf(">>> Invalid arguments in '%s'%n", aCommand);
				System.out.println(commandHandler.getMessage());
			}
		} else {
			System.out.printf(">>> Invalid command '%s'%n", aCommand);
		}
		commandHandler.reset();
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