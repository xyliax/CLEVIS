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
    private static final float CO_MIN_VALUE = Float.MIN_VALUE;
    private static final float CO_MAX_VALUE = Float.MAX_VALUE;
    private boolean active;
    //is it still able to handle another command? only quit will set it false
    private boolean cmdValid; // is cmd valid?
    private boolean allValid;
    private String cmd; // cmd part of a command
    private List<Object> arguments; // arguments part of a command
    private String message;

    CommandHandler() {
        setVar(true, false, false, null);
        System.out.println("CommandHandler initialized");
    }

    public void reset() {
        setVar(true, false, false, null);
        setMessage("Default Status at " + new Date());
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCommand(String cmd) {
        this.cmd = cmd;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public boolean isAllValid() {
        return allValid;
    }

    public void setAllValid(boolean allValid) {
        this.allValid = allValid;
    }

    public boolean isCmdValid() {
        return cmdValid;
    }

    public void setCmdValid(boolean cmdValid) {
        this.cmdValid = cmdValid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
        }
        else if (cmd.equals("rectangle")) {
            //add missing code
        }
        else if (cmd.equals("line")) {
            //add missing code
        }
        else if (cmd.equals("circle")) {
            //add missing code
        }
        else if (cmd.equals("square")) {
            //add missing code
        }
        else if (cmd.equals("group")) {
            //add missing code
        }
        else if (cmd.equals("ungroup")) {
            //add missing code
        }
        else if (cmd.equals("delete")) {
            //add missing code
        }
        else if (cmd.equals("boundingbox")) {
            //add missing code
        }
        else if (cmd.equals("move")) {
            //add missing code
        }
        else if (cmd.equals("pick-and-move")) {
            //add missing code
        }
        else if (cmd.equals("intersect")) {
            //add missing code
        }
        else if (cmd.equals("list")) {
            //add missing code
        }
        else if(cmd.equals("listall")) {
            //add missing code
        }
        else if(cmd.equals("save")) {
            //don't bother this
            //skip it
        }
        else {
            //...
        }
    }

    /**
     * use this to set status please!
     * @param active
     * @param cmdValid
     * @param allValid
     * @param command
     * @param vars
     */
    private void setVar(boolean active, boolean cmdValid, boolean allValid,
                        String command, Object... vars) {
        setActive(active);
        setCmdValid(cmdValid);
        setAllValid(allValid);
        setCommand(command);
        setArguments(Collections.singletonList(vars));
    }

}
