package applicationLayer.command;

import gui.Canvas;

public abstract class Command {
    protected Canvas canvas;

    public abstract Boolean execute(Canvas canvas);
    public abstract void undo(Canvas canvas);
    public abstract void redo(Canvas canvas);

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
