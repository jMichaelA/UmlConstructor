package applicationLayer.command;

import gui.Canvas;
import gui.ClassLine;
import javafx.application.Platform;
import javafx.scene.Node;

public class Delete extends Command {
    private Node node;
    private Node start;
    private Node end;

    public Delete(Object[] params){
        node = (Node) params[0];
    }

    @Override
    public Boolean execute(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().remove(node);

            }
        });
        return true;
    }

    @Override
    public void undo(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(node instanceof ClassLine){
                    start = ((ClassLine) node).getStart();
                    end = ((ClassLine) node).getEnd();
                    canvas.getChildren().removeAll(start, end);
                    canvas.getChildren().addAll(node, start, end);
                }else {
                    canvas.getChildren().addAll(node);
                }
            }
        });
    }

    @Override
    public void redo(Canvas canvas) {
        execute(canvas);
    }
}
