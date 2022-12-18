package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {

    public TextField fieldUsername;

    public void buttonClick(ActionEvent actionEvent) {
        if(fieldUsername.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText("Niste unijeli korisničko ime");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pozdrav");
    }
}
