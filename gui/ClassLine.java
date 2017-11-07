package gui;

import javafx.scene.shape.Line;

import java.util.concurrent.atomic.AtomicInteger;

public class ClassLine extends Line implements Component {
    private AtomicInteger id;
    private Table start;
    private Table end;

    public ClassLine(){
        super();
        id = new AtomicInteger();
        start = null;
        end = null;
    }

    public ClassLine(Table start, Table end){
        super();
        id = new AtomicInteger();
        this.start = start;
        this.end = end;
        this.setStrokeWidth(2.0);

        this.setStartX(start.getX());
        this.setStartY(start.getY());
        this.startXProperty().bind(start.layoutXProperty().add(start.getRectangle().getWidth()/2));
        this.startYProperty().bind(start.layoutYProperty().add(start.getRectangle().getHeight()/2));

        this.setEndX(end.getX());
        this.setEndY(end.getY());
        this.endXProperty().bind(end.layoutXProperty().add(end.getRectangle().getWidth()/2));
        this.endYProperty().bind(end.layoutYProperty().add(end.getRectangle().getHeight()/2));
    }

    @Override
    public String getType() {
        return "line";
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

    public void setId(AtomicInteger id) {
        this.id = id;
    }

    public Table getStart() {
        return start;
    }

    public void setStart(Table start) {
        this.start = start;
    }

    public Table getEnd() {
        return end;
    }

    public void setEnd(Table end) {
        this.end = end;
    }
}
