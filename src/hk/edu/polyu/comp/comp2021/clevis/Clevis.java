package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.ClevisModel;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.InvalidCommandException;

import java.util.Scanner;

/**
 *
 */
public class Clevis {

	/**
	 * @param args args
	 */

	public static void main(String[] args) {

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

		ClevisModel clevis = new ClevisModel(htmlName, txtName);
		System.out.println("CLEVIS session starts!");
		System.out.println("----------------------------------------------------------------------------");
		Scanner scanner = new Scanner(System.in);
		while (clevis.isRunning()) {
			System.out.print(">>> Clevis % ");
			scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (input.isBlank())
				continue;
			if (input.matches("man *+")) {
				System.out.print(">>> ");
				displayUserManual(input.substring(3).strip().toLowerCase());
				continue;
			}
			try {
				clevis.request(input);
			} catch (InvalidCommandException invalidCommandException) {
				System.out.println("Invalid command! " + invalidCommandException.getMessage());
				System.out.println("'man [-c]' to display the user manual.");
			}
		}
	}

	private static void displayUserManual(String commandName) {
		switch (commandName) {
			case "title" -> System.out.println(Manual.TITLE.getText());
			case "rectangle" -> System.out.println(Manual.RECTANGLE.getText());
			case "line" -> System.out.println(Manual.LINE.getText());
			case "circle" -> System.out.println(Manual.CIRCLE.getText());
			case "square" -> System.out.println(Manual.SQUARE.getText());
			case "group" -> System.out.println(Manual.GROUP.getText());
			case "ungroup" -> System.out.println(Manual.UNGROUP.getText());
			case "delete" -> System.out.println(Manual.DELETE.getText());
			case "boundingbox" -> System.out.println(Manual.BOUNDINGBOX.getText());
			case "move" -> System.out.println(Manual.MOVE.getText());
			case "pick-and-move" -> System.out.println(Manual.PICK_AND_MOVE.getText());
			case "intersect" -> System.out.println(Manual.INTERSECT.getText());
			case "list" -> System.out.println(Manual.LIST.getText());
			case "listAll" -> System.out.println(Manual.LISTALL.getText());
			case "quit" -> System.out.println(Manual.QUIT.getText());
			default -> System.out.println(Manual.CLEVIS.getText());
		}
	}


}

