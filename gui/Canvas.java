package gui;

import javafx.scene.layout.Pane;

public class Canvas extends Pane {
    private String theme;
    private String name;

    public Canvas(){
        super();
        theme = "basic";
        name = "uml";
    }

    public Canvas(String theme, String name) {
        super();
        this.theme = theme;
        this.name = name;
    }


}
