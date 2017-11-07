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

        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
    }
}
