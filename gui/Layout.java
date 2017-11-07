package gui;

import applicationLayer.command.*;
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
import javafx.stage.Stage;

import java.util.ArrayList;

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
    private Button classBtn = new Button("Class");
    private Button inherit = new Button("Inheritance");
    private ArrayList<Table> classTable = new ArrayList<Table>();
    private KeyCombination ctrZ = KeyCodeCombination.keyCombination("Ctrl+z");

    /* lines */
    private Boolean addingLine = false;
    private Boolean deleting = false;
    private ClassLine deleteingLine = null;
    private Table tableA = null;
    private Table tableB = null;

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

        classBtn.getStyleClass().addAll("button", "left-menu-button");
        classBtn.setPrefWidth(btnSize);

        inherit.getStyleClass().addAll("button", "left-menu-button");
        inherit.setPrefWidth(btnSize);

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
        leftMenu.getChildren().addAll(classBtn, inherit, btn3, btn4);

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
            if(deleting){
                deleteingLine.setOnMouseClicked(event -> {
                    if(e.getCode() == KeyCode.DELETE) {
                        Object[] params = {deleteingLine};
                        invoker.enqueueCommand(cmdFactory.create("delete", params));
                        resetDeletingLine();
                    }
                });
            }
            if(e.getCode() == KeyCode.Z && e.isControlDown()){
                invoker.undo();
            }else if(e.getCode() == KeyCode.R && e.isControlDown()){
                invoker.redo();
            }
        });

        setClassBtn();

        menuUndo.setOnAction(e -> {
            invoker.undo();
        });

        menuRedo.setOnAction(e -> {
            invoker.redo();
        });

        inherit.setOnAction(e -> {
            addingLine = !addingLine;
        });
    }

    private void setClassBtn(){
        classBtn.setOnAction(e -> {
            AddTable cmd = (AddTable)cmdFactory.create("addtable", null);
            invoker.enqueueCommand(cmd);
            classTable.add(cmd.getTable());
            resetDeletingLine();

            cmd.getTable().setOnMouseClicked(evt -> {
                deleting = true;
                if(evt.getClickCount() == 2) {
                    resetAddingLine();
                    Stage stage = new Stage();
                    NameTableForm form = new NameTableForm();
                    try {
                        form.start(stage);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    stage.setOnCloseRequest(event-> {
                        if(form.getName() != null) {
                            Object[] obj = {cmd.getTable(), form.getName()};
                            RenameTable tmpCmd = (RenameTable) cmdFactory.create("renametable", obj);
                            invoker.enqueueCommand(tmpCmd);
                            tmpCmd.getTable().getRectangle().setWidth(form.getName().length()*10.0+10);
                        }
                    });
                }else if(addingLine) {
                    if(tableA == null){
                        tableA = cmd.getTable();
                    }else{
                        tableB = cmd.getTable();
                        Object[] obj = {tableA, tableB};
                        AddLine lineCmd = (AddLine) cmdFactory.create("addLine", obj);
                        invoker.enqueueCommand(lineCmd);
                        resetAddingLine();

                        deleting = true;
                        deleteingLine = lineCmd.getLine();
                    }
                }else {
                    resetDeletingLine();
                }
            });
        });

    }

    private void resetAddingLine() {
        tableA = null;
        tableB = null;
        addingLine = false;
    }

    private void resetDeletingLine() {
        deleting = false;
        deleteingLine = null;
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
