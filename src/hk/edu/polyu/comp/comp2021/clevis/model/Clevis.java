package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.model.shapes.ShapeManager;

import java.io.*;
import java.util.Arrays;
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


    public Clevis() {
        shapeManager = new ShapeManager();
        commandHandler = new CommandHandler();
        setCreateTime(new Date());
        System.out.println("Clevis model initialized");
        System.out.println(this);
        setLogFiles("log.html", "log.txt");
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

    public static void main(String[] args) {
        Clevis clevis = new Clevis();
        clevis.test();
    }

    public ShapeManager getShapeManager() {
        return shapeManager;
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

    public boolean isRunning() {
        return commandHandler.isActive();
    }

    private void setRunning(boolean status) {
        commandHandler.setActive(true);
    }

    // FIXME: 9/11/2021 more test!
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

    // FIXME: 9/11/2021 name checking
    public void request(String aCommand) {
        commandHandler.process(aCommand);
        String cmd = commandHandler.getCmd();
        List<Object> args = commandHandler.getArguments();
        if (commandHandler.isCmdValid()) {
            if (commandHandler.isAllValid()) {
                switch (cmd) {
                    case "rectangle": {
                        String n = (String) args.get(0);
                        float x = (float) args.get(1);
                        float y = (float) args.get(2);
                        float w = (float) args.get(3);
                        float h = (float) args.get(4);
                        System.out.printf("%s %s %.2f %.2f %.2f %.2f%n",
                                cmd, n, x, y, w, h);
                        shapeManager.createRectangle(n, x, y, w, h);
                        break;
                    }
                    case "line": {
                        String n = (String) args.get(0);
                        float x1 = (float) args.get(1);
                        float y1 = (float) args.get(2);
                        float x2 = (float) args.get(3);
                        float y2 = (float) args.get(4);
                        System.out.printf("%s %s %.2f %.2f %.2f %.2f%n",
                                cmd, n, x1, y1, x2, y2);
                        shapeManager.createLineSegment(n, x1, y1, x2, y2);
                        break;
                    }
                    case "circle": {
                        String n = (String) args.get(0);
                        float x = (float) args.get(1);
                        float y = (float) args.get(2);
                        float r = (float) args.get(3);
                        System.out.printf("%s %s %.2f %.2f %.2f%n",
                                cmd, n, x, y, r);
                        shapeManager.createCircle(n, x, y, r);
                        break;
                    }
                    case "square": {
                        String n = (String) args.get(0);
                        float x = (float) args.get(1);
                        float y = (float) args.get(2);
                        float l = (float) args.get(3);
                        System.out.printf("%s %s %.2f %.2f %.2f%n",
                                cmd, n, x, y, l);
                        shapeManager.createSquare(n, x, y, l);
                        break;
                    }
                    case "delete": {
                        String n = (String) args.get(0);
                        System.out.printf("%s %s%n", cmd, n);
                        shapeManager.deleteShape(n);
                        break;
                    }
                    case "move": {
                        String n = (String) args.get(0);
                        float dx = (float) args.get(1);
                        float dy = (float) args.get(2);
                        System.out.printf("%s %s %.2f %.2f%n",
                                cmd, n, dx, dy);
                        shapeManager.moveShape(n, dx, dy);
                        break;
                    }
                    case "group": {
                        String[] n = new String[args.size()];
                        for(int i = 0; i < n.length; i++) {
                            n[i] = (String) args.get(i);
                        }

                    }
                    case "ungroup": {

                    }
                    case "boundingbox": {

                    }
                    default:
                        System.out.printf(">>> Unsupported command '%s'%n", cmd);
                }

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
        return super.toString() + "Created at " + createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}