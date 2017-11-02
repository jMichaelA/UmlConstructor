package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NameTableForm extends Application{
    private String name;

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Class Name: ");
        label.getStyleClass().addAll("text-label", "padding-left-10");

        TextField input = new TextField();
        input.getStyleClass().addAll("text-field");

        HBox form = new HBox();
        form.getStyleClass().addAll("padding-top-25", "space-5");
        form.getChildren().addAll(label, input);

        Button ok = new Button("OK");
        ok.setOnAction(e-> {
            name = input.getText();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        input.getStyleClass().addAll("button");

        VBox body = new VBox();
        body.getStyleClass().addAll("body", "padding-10");
        body.getChildren().addAll(form, ok);

        Scene scene = new Scene(body, 350, 125);
        scene.getStylesheets().add("gui/assets/app.css");
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
