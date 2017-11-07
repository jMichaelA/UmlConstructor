package applicationLayer.command.test;

import applicationLayer.command.AddLine;
import gui.Canvas;
import gui.Table;

import static org.junit.Assert.*;

public class AddLineTest {
    private Table tableA;
    private Table tableB;
    private Object[] params;
    private AddLine lineCommand;
    private Canvas canvas;

    private void init(){
        tableA = new Table(0,0);
        tableB = new Table(0,0);
        params = new Object[]{tableA, tableB};
        lineCommand = new AddLine(params);
        canvas = new Canvas();
        canvas.getChildren().addAll(tableA, tableB);
    }

    @org.junit.Test
    public void execute() throws Exception {
        init();
        lineCommand.execute(canvas);
        assertEquals(true, canvas.getChildren().contains(lineCommand.getLine()));
        assertEquals(true, canvas.getChildren().contains(tableA));
        assertEquals(true, canvas.getChildren().contains(tableB));
    }

    @org.junit.Test
    public void undo() throws Exception {
        this.execute();
        lineCommand.undo(canvas);
        assertEquals(false, canvas.getChildren().contains(lineCommand.getLine()));
        assertEquals(true, canvas.getChildren().contains(tableA));
        assertEquals(true, canvas.getChildren().contains(tableB));
    }

    @org.junit.Test
    public void redo() throws Exception {
        this.undo();
        lineCommand.redo(canvas);
        assertEquals(true, canvas.getChildren().contains(lineCommand.getLine()));
        assertEquals(true, canvas.getChildren().contains(tableA));
        assertEquals(true, canvas.getChildren().contains(tableB));
    }
}