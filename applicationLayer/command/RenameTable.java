package applicationLayer.command;

import gui.Canvas;
import gui.Table;
import javafx.application.Platform;
import javafx.scene.Node;

public class RenameTable extends Command {
    private String prevName;
    private Table table;
    private String name;

    public RenameTable(Object[] params){
        this.table = (Table) params[0];
        this.name = params[1].toString();
    }


    @Override
    public Boolean execute(Canvas canvas) {
        prevName = table.getName().getText();
        table.setName(name);
        return true;
    }

    @Override
    public void undo(Canvas canvas) {
        String temp = prevName;
        prevName = table.getName().getText();
        table.setName(temp);
    }

    @Override
    public void redo(Canvas canvas) {
        String temp = prevName;
        prevName = table.getName().getText();
        table.setName(temp);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
