package applicationLayer.command;

public class Undo extends Command {

    @Override
    public Boolean execute() {
        return false;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}