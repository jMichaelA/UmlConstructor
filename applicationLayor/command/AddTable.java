package applicationLayor.command;

import applicationLayor.component.Canvas;

public class AddTable extends Command {

    @Override
    public Boolean execute() {
        System.out.println("Added table");
        return true;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
