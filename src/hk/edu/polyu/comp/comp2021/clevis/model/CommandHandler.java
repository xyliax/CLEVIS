package hk.edu.polyu.comp.comp2021.clevis.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * We consider every valid command into 2 parts : cmd(String) + arguments(List)
 * for example, "rectangle r1 1 2 3 4" : "rectangle" + "[r1, 1, 2, 3, 4]"
 * after process(), set 'active', 'valid' status, and command, arguments
 * outside world can get these and call relevant methods
 * Help me!
 * Noted that even if the command is invalid, you still need to set command,
 * for example, if the command is "recta r1 1 2 3 4", set command = "recta"
 */
class CommandHandler implements Serializable {
	private boolean active;
	private boolean cmdValid;
	private boolean allValid;
	private String cmd;
	private List<Object> arguments;
	private String message;

	/**
	 *
	 */
	CommandHandler() {
		setVar(true, false, false, null);
		System.out.println("CommandHandler initialized");
	}

	/**
	 *
	 */
	public void reset() {
		setVar(true, false, false, null);
		setMessage("Default Status at " + new Date());
	}

	boolean isActive() {
		return active;
	}

	void setActive(boolean active) {
		this.active = active;
	}

	String getCmd() {
		return cmd;
	}

	private void setCommand(String cmd) {
		this.cmd = cmd;
	}

	List<Object> getArguments() {
		return arguments;
	}

	private void setArguments(List<Object> arguments) {
		this.arguments = arguments;
	}

	boolean isAllValid() {
		return allValid;
	}

	private void setAllValid(boolean allValid) {
		this.allValid = allValid;
	}

	public boolean isCmdValid() {
		return cmdValid;
	}

	private void setCmdValid(boolean cmdValid) {
		this.cmdValid = cmdValid;
	}

	public String getMessage() {
		return message;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	public void process(String candidate) {
		setAllValid(true);
		int index = 0;
		StringBuilder cmdBuilder = new StringBuilder();
		StringBuilder argBuilder = new StringBuilder();
		while (index < candidate.length() && candidate.charAt(index) == ' ')
			index++;
		while (index < candidate.length() && candidate.charAt(index) != ' ')
			cmdBuilder.append(candidate.charAt(index++));
		String cmd = cmdBuilder.toString().toLowerCase();
		if (cmd.equals("quit")) {
			//add missing code
		} else if (cmd.equals("rectangle")) {
			//add missing code
		} else if (cmd.equals("line")) {
			//add missing code
		} else if (cmd.equals("circle")) {
			//add missing code
		} else if (cmd.equals("square")) {
			//add missing code
		} else if (cmd.equals("group")) {
			//add missing code
		} else if (cmd.equals("ungroup")) {
			//add missing code
		} else if (cmd.equals("delete")) {
			//add missing code
		} else if (cmd.equals("boundingbox")) {
			//add missing code
		} else if (cmd.equals("move")) {
			//add missing code
		} else if (cmd.equals("pick-and-move")) {
			//add missing code
		} else if (cmd.equals("intersect")) {
			//add missing code
		} else if (cmd.equals("list")) {
			//add missing code
		} else if (cmd.equals("listall")) {
			//add missing code
		} else if (cmd.equals("save")) {
			//don't bother this
			//skip it
		} else {
			//...
		}
	}

	private void setVar(boolean active, boolean cmdValid, boolean allValid,
						String command, Object... vars) {
		setActive(active);
		setCmdValid(cmdValid);
		setAllValid(allValid);
		setCommand(command);
		setArguments(Collections.singletonList(vars));
	}

}
