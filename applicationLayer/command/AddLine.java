package applicationLayer.command;

import gui.Canvas;
import gui.ClassLine;
import gui.Table;
import javafx.application.Platform;

public class AddLine extends Command {
    Table start;
    Table end;
    ClassLine line;

    public AddLine(Object[] params){
        this.start = (Table) params[0];
        this.end = (Table) params[1];
        line = new ClassLine(start, end);
    }

    @Override
    public Boolean execute(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().removeAll(start, end);
                canvas.getChildren().addAll(line, start, end);
            }
        });
        return true;
    }

    @Override
    public void undo(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().remove(line);
            }
        });
    }

    @Override
    public void redo(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                execute(canvas);
            }
        });
    }

    public ClassLine getLine() {
        return line;
    }

    public void setLine(ClassLine line) {
        this.line = line;
    }
}
