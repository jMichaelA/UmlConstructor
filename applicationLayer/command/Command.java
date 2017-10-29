package applicationLayer.command;

import applicationLayer.component.Canvas;

public abstract class Command {
    private Canvas canvas;

    public abstract Boolean execute();
    public abstract void undo();
    public abstract void redo();

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
