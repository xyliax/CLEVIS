package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InModelException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;

import java.io.*;
import java.util.Scanner;


// TODO: 17/11/2021 format

/**
 *
 */
public class ClevisIO implements Serializable {

	private Scanner scanner;
	private String htmlLogFile;
	private String txtLogFile;

	public ClevisIO(InputStream inputStream, PrintStream printStream) {
		System.setIn(inputStream);
		System.setOut(printStream);
		scanner = new Scanner(System.in);
	}

	public void reset() {
		scanner = new Scanner(System.in);
	}

	public String scanCommand() {
		String command;
		do {
			System.out.print("\n>>> Clevis % ");
			command = scanner.nextLine();
		} while (command.isBlank());
		return command;
	}

	public void setIn(InputStream inputStream) {
		System.setIn(inputStream);
	}

	public void setOut(PrintStream printStream) {
		System.setOut(printStream);
	}

	public void printResult(String result) {
		System.out.println("[RESULT]-" + result);
	}

	public void printWelcomeMessage() {
		System.out.println("""
							|---------------------------|------------------------------------------------------|
							| C L E V I S session starts| Signature: Group 9 COMP2021 11/2021 Group project    |
				|----------------------------------------------------------------------------------------------|
				| **     *     **  ********   **          *********   **********      *       *      ********  |
				|  **   * *   **   **         **          **          **      **     ***     ***     **        |
				|   ** ** ** **    ********   **          **          **      **    ** **   ** **    ********  |
				|    ***   ***     **         **          **          **      **   **   ** **   **   **        |
				|     *     *      ********   **********  *********   **********  **     **      **  ********  |
				|______________________________________________________________________________________________|
							""");
	}

	public void printInvalidCommandException(InvalidCommandException exception) {
		System.out.printf("""
				**********************************************************************
				*	Invalid Command!
				*-------->%s
				*	Try "man %s" to display the user manual
				**********************************************************************
				%n""", exception.getMessage(), exception.getCmd().name().toLowerCase());
	}

	public void printInModelException(InModelException inModelException) {
		System.out.printf("""
				**********************************************************************
				*	Exception caught in session!
				*-------->%s
				**********************************************************************
				%n""", inModelException.getMessage());
	}

	public void setLogFiles(String[] args) {
		String htmlName = "log.html";
		String txtName = "log.txt";
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].equals("-html"))
				htmlName = args[++i];
			else if (args[i].equals("-txt"))
				txtName = args[++i];
		}
		if (!htmlName.matches(".+\\.html"))
			htmlName += ".html";
		if (!txtName.matches(".+\\.txt"))
			txtName += ".txt";

		htmlLogFile = htmlName;
		txtLogFile = txtName;
		try {
			new FileOutputStream(htmlLogFile);
			new FileOutputStream(txtLogFile);
			printSystemNotice("Success - Finish setting up log files");
			printSystemNotice(String.format("""
					All valid operations will be logged in to the followings
							'%s'
							'%s'
					""", htmlName, txtName));
		} catch (FileNotFoundException fileNotFoundException) {
			printSystemNotice("Error - Unable to set up log files");
		}
	}

	public void printSystemNotice(String notice) {
		System.out.println(">>> " + notice);
	}

	public void logCommand(String command) {
		// TODO: 17/11/2021 html
		try {
			FileOutputStream txtStream = new FileOutputStream(txtLogFile, true);
			txtStream.write((command + '\n').getBytes());
		} catch (IOException ioException) {
			printRunningMessage("Unable to log command: " + command);
		}
	}

	public void printRunningMessage(String message) {
		System.out.println("--------" + message);
	}

	public void displayUserManual(String command) {
		String commandName = command.substring(3).strip().toLowerCase();
		switch (commandName) {

			case "rectangle" -> println(Manual.RECTANGLE.getText());

			case "line" -> println(Manual.LINE.getText());

			case "circle" -> println(Manual.CIRCLE.getText());

			case "square" -> println(Manual.SQUARE.getText());

			case "group" -> println(Manual.GROUP.getText());

			case "ungroup" -> println(Manual.UNGROUP.getText());

			case "delete" -> println(Manual.DELETE.getText());

			case "boundingbox" -> println(Manual.BOUNDINGBOX.getText());

			case "move" -> println(Manual.MOVE.getText());

			case "pick-and-move" -> println(Manual.PICK_AND_MOVE.getText());

			case "intersect" -> println(Manual.INTERSECT.getText());

			case "list" -> println(Manual.LIST.getText());

			case "listall" -> println(Manual.LISTALL.getText());

			case "quit" -> println(Manual.QUIT.getText());

			default -> println(Manual.CLEVIS.getText());
		}
	}

	public void println(Object object) {
		System.out.println(object.toString());
	}
}
