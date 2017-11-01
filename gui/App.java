package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Layout root = new Layout();
        root.getStylesheets().add("gui/assets/app.css");
        root.setId("root");
        root.setPrefSize(200, 300);

        Canvas drawing = root.getCanvas();
        drawing.getStylesheets().add("gui/assets/app.css");
        drawing.setId("drawing");

        Table rect = new Table(75, 75, "parent");
        rect.setLayoutY(40);
        rect.setLayoutX(40);

        Table table2 = new Table(50, 50, "child");
        table2.setLayoutY(200);
        table2.setLayoutX(50);

        ClassLine line = new ClassLine(rect, table2);

        drawing.getChildren().addAll(line, rect, table2);
        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
    }
}
