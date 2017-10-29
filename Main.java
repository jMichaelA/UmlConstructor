import applicationLayor.command.Command;
import applicationLayor.command.CommandFactory;
import applicationLayor.command.Invoker;
import applicationLayor.component.Canvas;
import applicationLayor.component.ComponentFactory;
import gui.App;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Application.launch(App.class);
//        ComponentFactory factory = ComponentFactory.getInstance();
//        Canvas canvas = new Canvas();
//        CommandFactory cmdFact = CommandFactory.getInstance();
//        Command cmd = cmdFact.create("addTable", null);
//        Invoker invoker = new Invoker(cmd);
//        invoker.execute();
    }
}
