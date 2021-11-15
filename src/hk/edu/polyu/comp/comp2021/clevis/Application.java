package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import org.w3c.dom.ls.LSOutput;

import java.io.Console;
import java.util.Scanner;

/**
 *
 */
public class Application {

	/**
	 * @param args args
	 */
	public static void main(String[] args) {
		//Clevis clevis = Clevis.retrieveClevisFromFile(null);
		Clevis clevis = new Clevis();
		Console console = System.console();
		// FIXME: 10/11/2021
		Scanner scanner = new Scanner(System.in);
		while (clevis.isRunning()) {
			String input = scanner.nextLine();
			if (input.matches("man .+")) {
				displayUserManual(input.substring(3).strip().toLowerCase());
				continue;
			}
			clevis.request(input);
		}
	}

	private static void displayUserManual(String commandName) {
		switch (commandName) {
			case "line" -> System.out.println(Manual.LINE.getText());
			//...finish all cases
			default -> System.out.println(Manual.CLEVIS.getText());
		}
	}

	/**
	 *
	 */
	enum Manual {
		/**
		 * display all
		 */
		CLEVIS("""
				........................CLEVIS-USER-MANUAL........................
								
				"""),

		/**
		 *
		 */
		LINE("""
							........................CLEVIS-USER-MANUAL........................
				"""),

		//complete other commands
		private String text;

		Manual(String text) {
			this.setText(text);
		}


		/**
		 * Getter of text
		 *
		 * @return the user manual
		 */
		public String getText() {
			return text;
		}

		private void setText(String text) {
			this.text = text;
		}
		}
}


