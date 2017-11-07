package applicationLayer.command.test;

import applicationLayer.command.Delete;
import gui.Canvas;
import gui.Table;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteTest {
    private Table table;
    private Object[] params;
    private Delete delteCmd;
    private Canvas canvas;

    private void init(){
        table = new Table(0,0);
        params = new Object[]{table};
        delteCmd = new Delete(params);
        canvas = new Canvas();
        canvas.getChildren().addAll(table);
    }

    @Test
    public void execute() throws Exception {
        init();
        delteCmd.execute(canvas);
        assertEquals(false, canvas.getChildren().contains(table));
    }

    @Test
    public void undo() throws Exception {
        this.execute();
        delteCmd.undo(canvas);
        assertEquals(true, canvas.getChildren().contains(table));
    }

    @Test
    public void redo() throws Exception {
        this.undo();
        delteCmd.redo(canvas);
        assertEquals(false, canvas.getChildren().contains(table));

    }

}