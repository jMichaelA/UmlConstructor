package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.util.event.MouseControlUtil;

public class Table extends StackPane implements Component {
    private Rectangle rectangle;
    private Label name;


    public Table(Integer x, Integer y, String name) {
        this.rectangle = new Rectangle(x, y);
        this.rectangle.getStyleClass().addAll("table-def-rect");
        this.rectangle.setArcHeight(5);
        this.rectangle.setArcWidth(5);

        this.name = new Label(name);
        this.name.getStyleClass().addAll("table-def-label");

        this.getStyleClass().addAll("table-cpt");
        this.getChildren().addAll(rectangle, this.name);
        MouseControlUtil.makeDraggable(this);
    }

    @Override
    public String getType() {
        return "table";
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }
}
