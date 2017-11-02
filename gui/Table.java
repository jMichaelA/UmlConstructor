package gui;

import applicationLayer.command.CommandFactory;
import applicationLayer.command.Invoker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;
import java.util.concurrent.atomic.AtomicInteger;

public class Table extends StackPane implements Component {
    private Rectangle rectangle;
    private Label name;
    private AtomicInteger id;
    private String cmd;

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

        this.setOnMouseClicked(e -> {
            Stage stage = new Stage();
            if(e.getClickCount() == 2){
                NameTableForm form = new NameTableForm();
                try {
                    form.start(stage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                stage.setOnCloseRequest(event->{
                    if(form.getName() != null){
                        this.setName(form.getName());
                        this.rectangle.setWidth(form.getName().length()*10.0+10);
                    }
                });
            }
        });
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

    public void setName(String name) {
        this.name.setText(name);
    }
}
