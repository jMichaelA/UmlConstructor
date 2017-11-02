package gui;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/* based on https://gist.github.com/Warlander/815f5c435b2b11527ce65ff165dde023 */

public class ClickableMenu extends Menu {
    private Label label;

    public ClickableMenu(String label) {
        this.label = new Label(label);
        this.setGraphic(this.label);
        MenuItem hiddenMenu = new MenuItem();
        hiddenMenu.setVisible(false);
        this.getItems().addAll(hiddenMenu);

        this.label.setOnMouseClicked(e->{
            hiddenMenu.fire();
        });
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }
}
