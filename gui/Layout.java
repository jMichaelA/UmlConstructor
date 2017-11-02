package gui;

import applicationLayer.command.AddTable;
import applicationLayer.command.CommandFactory;
import applicationLayer.command.Invoker;
import applicationLayer.command.Undo;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Layout extends Pane {
    private MenuBar menu = new MenuBar();
    private Menu menuFile = new Menu("File");
    private Menu menuView= new Menu("View");
    private ClickableMenu menuUndo = new ClickableMenu("Undo");
    private ClickableMenu menuRedo = new ClickableMenu("Redo");
    private MenuItem menuSave = new MenuItem("Save");
    private MenuItem menuOpen = new MenuItem("Open");
    private MenuItem menuBackground = new MenuItem("Background");
    private HBox body;
    private Canvas canvas;
    private VBox leftMenu;
    private Invoker invoker;
    private CommandFactory cmdFactory = CommandFactory.getInstance();

    private KeyCombination ctrZ = KeyCodeCombination.keyCombination("Ctrl+z");

    public Layout(){
        super();
        canvas = new Canvas();
        invoker = new Invoker(canvas);
        invoker.start();
        cmdFactory.setCanvas(canvas);

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

        Button classBtn = new Button("Class");
        classBtn.getStyleClass().addAll("button", "left-menu-button");
        classBtn.setPrefWidth(btnSize);
        classBtn.setOnAction(e -> {
            invoker.enqueueCommand(cmdFactory.create("addTable", null));
        });

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
        leftMenu.getChildren().addAll(classBtn, btn2, btn3, btn4);

        canvas.getStyleClass().addAll("black-grey", "padding-10");
        canvas.prefWidthProperty().bind(this.widthProperty().subtract(leftMenu.getPrefWidth()+30));

        body = new HBox();
        body.getStyleClass().addAll("hbox");
        body.getChildren().addAll(menuBox, leftMenu, canvas);

        VBox total = new VBox();
        total.getChildren().addAll(menuBox, body);

        this.getChildren().addAll(total);
        setActions();
    }

    private void setActions(){

        this.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.Z && e.isControlDown()){
                invoker.undo();
            }else if(e.getCode() == KeyCode.R && e.isControlDown()){
                invoker.redo();
            }
        });

        menuUndo.setOnAction(e -> {
            invoker.undo();
        });

        menuRedo.setOnAction(e -> {
            invoker.redo();
        });
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
