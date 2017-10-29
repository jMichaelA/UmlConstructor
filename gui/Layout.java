package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Layout extends Pane {
    private final MenuBar menu = new MenuBar();
    private final Menu menuFile = new Menu("File");
    private final Menu menuUndo = new Menu("undo");
    private final Menu menuRedo = new Menu("redo");
    private HBox body;
    private Canvas canvas;
    private VBox leftMenu;

    public Layout(){
        super();
        menu.getMenus().addAll(menuFile, menuUndo, menuRedo);
        HBox menuBox = new HBox();
        menuBox.prefWidthProperty().bind(this.widthProperty());
        menuBox.getStyleClass().addAll("menu-bar");
        menuBox.getChildren().addAll(menu);

        Button btn1 = new Button("gasp");
        btn1.getStyleClass().addAll("button", "left-menu-button");
        Button btn2 = new Button("gasp");
        btn2.getStyleClass().addAll("button", "left-menu-button");
        Button btn3 = new Button("gasp");
        btn3.getStyleClass().addAll("button", "left-menu-button");
        Button btn4 = new Button("gasp");
        btn4.getStyleClass().addAll("button", "left-menu-button");

        leftMenu = new VBox();
        leftMenu.getStyleClass().addAll("left-menu");
        leftMenu.setPrefHeight(1000);
        leftMenu.setPrefWidth(100);
        leftMenu.getChildren().addAll(btn1, btn2, btn3, btn4);

        canvas = new Canvas();
        canvas.getStyleClass().addAll("black-grey", "padding-10");
        canvas.prefWidthProperty().bind(this.widthProperty().subtract(leftMenu.getPrefWidth()+30));

        body = new HBox();
        body.getStyleClass().addAll("hbox");
        body.getChildren().addAll(menuBox, leftMenu, canvas);

        VBox total = new VBox();
        total.getChildren().addAll(menuBox, body);

        this.getChildren().addAll(total);
    }

    public double getLeftBoundary(){
        return this.getLayoutX();
    }

    public MenuBar getMenu() {
        return menu;
    }

    public Menu getMenuFile() {
        return menuFile;
    }

    public Menu getMenuUndo() {
        return menuUndo;
    }

    public Menu getMenuRedo() {
        return menuRedo;
    }

    public HBox getBody() {
        return body;
    }

    public void setBody(HBox body) {
        this.body = body;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public VBox getLeftMenu() {
        return leftMenu;
    }

    public void setLeftMenu(VBox leftMenu) {
        this.leftMenu = leftMenu;
    }
}
