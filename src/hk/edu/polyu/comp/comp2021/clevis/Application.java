package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InModelException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.view.ClevisIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * The application class for Clevis.
 * <p>This is what the user can get access to.</p>
 * <p>Each application object has registered controller and IO objects</p>
 *
 * @see Clevis
 * @see ClevisIO
 */
public class Application {
	private Clevis controller;
	private ClevisIO clevisIO;

	/**
	 * Constructor of Application
	 *
	 * @param args arguments from main
	 * @see #main(String[])
	 */
	public Application(String[] args) {
		//ShapeDrawer drawer = new ShapeDrawer();
		//drawer.drawCircle();
		InputStream inputStream = System.in;
		try {
			inputStream = new FileInputStream("inputx.txt");
		} catch (FileNotFoundException ignored) {
		}
		setClevisIO(new ClevisIO(inputStream, System.out));
		setController(new Clevis(clevisIO));
		clevisIO.setLogFiles(args);
	}

	/**
	 * The main method.
	 * <p>Creates an application and start the running session.</p>
	 *
	 * @param args args
	 * @see #run()
	 */
	public static void main(String[] args) {
		Application application = new Application(args);
		application.run();
	}

	/**
	 * The start method for this application.
	 * <p>Scan commands from the IO object and send to the controller for processing.<\p>
	 *
	 * @see #controller
	 * @see #clevisIO
	 * @see Clevis#request(String)
	 */
	public void run() {
		Clevis clevis = this.getController();
		ClevisIO io = this.getClevisIO();
		io.printWelcomeMessage();
		while (clevis.isRunning()) {
			String command = io.scanCommand();
			if (command.matches("man .+")) {
				io.displayUserManual(command);
				continue;
			}
			try {
				clevis.request(command);
			} catch (InvalidCommandException invalidCommandException) {
				io.printInvalidCommandException(invalidCommandException);
			} catch (InModelException inModelException) {
				io.printInModelException(inModelException);
			}
		}
	}

	/**
	 * Getter of controller
	 *
	 * @return the controller object
	 * @see #controller
	 */
	public Clevis getController() {
		return controller;
	}

	/**
	 * Setter of controller
	 *
	 * @param controller the controller object
	 * @see #controller
	 */
	public void setController(Clevis controller) {
		this.controller = controller;
	}

	/**
	 * Getter of clevisIO
	 *
	 * @return the IO object
	 * @see #clevisIO
	 */
	public ClevisIO getClevisIO() {
		return clevisIO;
	}

	/**
	 * Setter of clevisIO
	 *
	 * @param clevisIO I-O object for Clevis
	 * @see #clevisIO
	 */
	public void setClevisIO(ClevisIO clevisIO) {
		this.clevisIO = clevisIO;
	}
}