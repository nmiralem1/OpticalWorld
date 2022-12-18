package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class noviprozor {
    public Label labela;

    public void buttonZatvoriClick(ActionEvent actionEvent) {
        Stage stage = (Stage) labela.getScene().getWindow();

        stage.close();
    }
}
