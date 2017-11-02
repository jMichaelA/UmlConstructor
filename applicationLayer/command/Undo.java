package applicationLayer.command;

import gui.Canvas;

public class Undo extends Command {

    @Override
    public Boolean execute(Canvas canvas) {
        return false;
    }

    @Override
    public void undo(Canvas canvas) {

    }

    @Override
    public void redo(Canvas canvas) {

    }
}