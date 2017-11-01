package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Layout extends Pane {
    private MenuBar menu = new MenuBar();
    private Menu menuFile = new Menu("File");
    private Menu menuView= new Menu("View");
    private Menu menuUndo = new Menu("Undo");
    private Menu menuRedo = new Menu("Redo");
    private MenuItem menuSave = new MenuItem("Save");
    private MenuItem menuOpen = new MenuItem("Open");
    private MenuItem menuBackground = new MenuItem("Background");
    private HBox body;
    private Canvas canvas;
    private VBox leftMenu;

    public Layout(){
        super();
        menuSave.getStyleClass().addAll("sub-menu");
        menuOpen.getStyleClass().addAll("sub-menu");

        menuFile.getItems().addAll(menuSave, menuOpen);
        menuView.getItems().addAll(menuBackground);

        menu.getMenus().addAll(menuFile, menuView, menuUndo, menuRedo);
        HBox menuBox = new HBox();
        menuBox.prefWidthProperty().bind(this.widthProperty());
        menuBox.getStyleClass().addAll("menu-bar", "border-bottom");
        menuBox.getChildren().addAll(menu);

        Integer btnSize = 120;

        Button btn1 = new Button("Class");
        btn1.getStyleClass().addAll("button", "left-menu-button");
        btn1.setPrefWidth(btnSize);

        Button btn2 = new Button("Inheritance");
        btn2.getStyleClass().addAll("button", "left-menu-button");
        btn2.setPrefWidth(btnSize);

        Button btn3 = new Button("Aggregation");
        btn3.getStyleClass().addAll("button", "left-menu-button");
        btn3.setPrefWidth(btnSize);

        Button btn4 = new Button("Association");
        btn4.getStyleClass().addAll("button", "left-menu-button");
        btn4.setPrefWidth(btnSize);

        leftMenu = new VBox();
        leftMenu.getStyleClass().addAll("left-menu");
        leftMenu.setPrefHeight(1000);
        leftMenu.setPrefWidth(150);
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
