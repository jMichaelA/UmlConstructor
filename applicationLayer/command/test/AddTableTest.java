package applicationLayer.command.test;

import applicationLayer.command.AddTable;
import gui.Canvas;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTableTest {
    private Canvas canvas;
    private AddTable addTable;

    private void init(){
        canvas = new Canvas();
        addTable = new AddTable();
    }

    @Test
    public void execute() throws Exception {
        init();
        addTable.execute(canvas);
        assertEquals(true, canvas.getChildren().contains(addTable.getTable()));
    }

    @Test
    public void undo() throws Exception {
        this.execute();
        addTable.undo(canvas);
        assertEquals(false, canvas.getChildren().contains(addTable.getTable()));
    }

    @Test
    public void redo() throws Exception {
        this.undo();
        addTable.redo(canvas);
        assertEquals(true, canvas.getChildren().contains(addTable.getTable()));
    }

}