package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InModelException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;

import java.io.*;
import java.util.Scanner;


/**
 * The view class for Clevis.
 * <p>Manipulates all the input and output issues.</p>
 *
 * @see hk.edu.polyu.comp.comp2021.clevis.controller.Clevis
 * @see hk.edu.polyu.comp.comp2021.clevis.Application
 * @see hk.edu.polyu.comp.comp2021.clevis.model.shapetoolbox.ShapeManager
 */
public class ClevisIO implements Serializable {
	private final Scanner scanner;
	private String htmlLogFile;
	private String txtLogFile;

	/**
	 * The constructor of ClevisIO.
	 *
	 * @param inputStream the input stream for IO
	 * @param printStream the output stream for IO
	 */
	public ClevisIO(InputStream inputStream, PrintStream printStream) {
		System.setIn(inputStream);
		System.setOut(printStream);
		scanner = new Scanner(System.in);
	}

	/**
	 * The method for scanning a command.
	 *
	 * @return a command string
	 */
	public String scanCommand() {
		String command;
		do {
			System.out.print("\n>>> Clevis % ");
			command = scanner.nextLine();
		} while (command.isBlank());
		return command;
	}

	/**
	 * The method for printing results.
	 *
	 * @param result the result to be printed
	 */
	public void printResult(String result) {
		System.out.println("[RESULT]-" + result);
	}

	/**
	 * The method for printing welcome message.
	 */
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

	/**
	 * The method for printing invalid command exceptions.
	 *
	 * @param exception an invalidCommandException
	 */
	public void printInvalidCommandException(InvalidCommandException exception) {
		System.out.printf("""
				**********************************************************************
				*	Invalid Command!
				*-------->%s
				*-------->%s
				*	Try "man %s" to display the user manual
				**********************************************************************
				%n""", exception, exception.getMessage(), exception.getCmd().name().toLowerCase());
	}

	/**
	 * The method for printing in-model exceptions.
	 *
	 * @param inModelException an inModelException
	 */
	public void printInModelException(InModelException inModelException) {
		System.out.printf("""
				**********************************************************************
				*	Exception caught in session!
				*-------->%s
				*-------->%s
				**********************************************************************
				%n""", inModelException, inModelException.getMessage());
	}

	/**
	 * The method for setting up log files.
	 *
	 * @param args arguments from Application
	 * @see hk.edu.polyu.comp.comp2021.clevis.Application#main(String[])
	 */
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

	/**
	 * The method for printing system notice.
	 *
	 * @param notice the notice to be printed
	 */
	public void printSystemNotice(String notice) {
		System.out.println(">>> " + notice);
	}

	/**
	 * The method for logging a command into txt and html files.
	 *
	 * @param command the command to be logged
	 * @see #createhtml()
	 */
	public void logCommand(String command) {
		try {
			FileOutputStream txtStream = new FileOutputStream(txtLogFile, true);
			txtStream.write((command + '\n').getBytes());
		} catch (IOException ioException) {
			printRunningMessage("Unable to log command: " + command);
		}
		createhtml();
	}

	/**
	 * The method for printing running time message.
	 *
	 * @param message the message to be printed
	 */
	public void printRunningMessage(String message) {
		System.out.println("--------" + message);
	}

	private void createhtml() {
		StringBuilder s = new StringBuilder();
		try {
			PrintStream printStream = new PrintStream(htmlLogFile);
			String partname = htmlLogFile.replaceAll(".html", "");
			s.append("<html>").append("<head>").append("<title>").append(partname).append("</title>")
					.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />")
					.append("<style type=\"text/css\">")
					.append(".tablename table th {background:#8FBC8F}")
					.append(".tablename table tr{ background:#FAEBD7;text-align:center}")
					.append("</style></head>")
					.append("<div class=\"tablename\">")
					.append("<table align=\"center\" width=\"1000\"  height=\"100\" border=\"1\"")
					.append(" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;\">")
					.append("<th>index</th><th>operation command</th><tr>")
					.append("<tbody   align=\"center\">");
			try {
				Scanner scanner = new Scanner(new FileInputStream(txtLogFile));
				int i = 1;
				while (scanner.hasNextLine())
					s.append("<td>").append(i++).append("</td><td>")
							.append(scanner.nextLine()).append("</td></tr>");
			} catch (Exception ignored) {
			}
			s.append("</tr></table>")
					.append("<body background=\"beach.jpg\"style=\"background-repeat:no-repeat")
					.append("background-attachment:fixed;")
					.append(" background-size:100% 100%;\">")
					.append("</div></body></html>");
			printStream.println(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method for displaying a specified user manual.
	 *
	 * @param command the command needing explained
	 */
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

	/**
	 * The encapsulated method for printline.
	 *
	 * @param object the object to be printed
	 */
	public void println(Object object) {
		System.out.println(object.toString());
	}
}