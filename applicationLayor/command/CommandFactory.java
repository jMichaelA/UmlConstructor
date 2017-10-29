package applicationLayor.command;

import applicationLayor.component.Canvas;

public class CommandFactory {
    private static Canvas canvas;
    private static CommandFactory ourInstance = new CommandFactory(canvas);

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private CommandFactory(Canvas canvas) {
        this.canvas = canvas;
        System.out.println("created");
    }

    public Command create(String name, Object[] params){
        Command command = null;
        switch (name.toLowerCase()) {
            case "addtable":
                command = new AddTable();
                break;
            default:
                break;
        }
        return command;
    }
}
