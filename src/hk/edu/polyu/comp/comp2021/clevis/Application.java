package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InModelException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;
import hk.edu.polyu.comp.comp2021.clevis.view.ClevisIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 */
public class Application {

	private Clevis controller;
	private ClevisIO clevisIO;

	/**
	 *
	 */
	public Application(String[] args) throws FileNotFoundException {
		InputStream in = new FileInputStream("input.txt");
		setClevisIO(new ClevisIO(in, System.out));
		setController(new Clevis(clevisIO));

		clevisIO.setLogFiles(args);
	}

	/**
	 * @param args args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Application application = new Application(args);
		application.run();
	}

	public void run() {
		Clevis clevis = this.getController();
		ClevisIO io = this.getClevisIO();

		//FileInputStream file = new FileInputStream("input.txt");
		//io.setIn(file);
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

	public Clevis getController() {
		return controller;
	}

	public void setController(Clevis controller) {
		this.controller = controller;
	}

	public ClevisIO getClevisIO() {
		return clevisIO;
	}

	public void setClevisIO(ClevisIO clevisIO) {
		this.clevisIO = clevisIO;
	}
}

