package applicationLayer.command;


import gui.Canvas;

public class CommandFactory {
    private static Canvas canvas;
    private static CommandFactory ourInstance = new CommandFactory(canvas);

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private CommandFactory(Canvas canvas) {
        this.canvas = canvas;
    }

    public Command create(String name, Object[] params){
        Command command = null;
        switch (name.toLowerCase()) {
            case "addtable":
                command = new AddTable(params);
                break;
            case "renametable":
                command = new RenameTable(params);
                break;
            case "addline":
                command = new AddLine(params);
                break;
            case "delete":
                command = new Delete(params);
                break;
            default:
                break;
        }
        return command;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        CommandFactory.canvas = canvas;
    }

    public static CommandFactory getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(CommandFactory ourInstance) {
        CommandFactory.ourInstance = ourInstance;
    }
}
