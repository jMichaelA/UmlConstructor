package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.util.event.MouseControlUtil;
import java.util.concurrent.atomic.AtomicInteger;

public class Table extends StackPane implements Component {
    private Rectangle rectangle;
    private Label name;
    private AtomicInteger id;

    public Table(Integer x, Integer y, String name) {
        id = new AtomicInteger();
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

    @Override
    public AtomicInteger getComponentId() {
        return id;
    }

    @Override
    public void setComponentId(AtomicInteger id){
        this.id = id;
    }

    @Override
    public Double getX() {
        return this.getLayoutX();
    }

    @Override
    public Double getY() {
        return this.getLayoutY();
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
