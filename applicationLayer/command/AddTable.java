package applicationLayer.command;

import gui.Canvas;
import gui.Table;
import javafx.application.Platform;

public class AddTable extends Command {
    private Table table;
    private String name;

    public AddTable(Object[] params){
        if(params == null || params.length < 1) {
            name = "class";
        }else {
            name = params[0].toString();
        }
        int width = name.length()*10;
        table = new Table(width, 50, name);
        table.setLayoutX(100);
        table.setLayoutY(100);
    }

    @Override
    public Boolean execute(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().addAll(table);
            }
        });
        return true;
    }

    @Override
    public void undo(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().remove(table);
            }
        });
    }

    @Override
    public void redo(Canvas canvas) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getChildren().addAll(table);
            }
        });
    }
}
