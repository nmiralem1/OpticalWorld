package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
/**
 * Controller that will help you with technical problems on our app.
 */
public class HelpController {

    @FXML private Button aboutUsButton, signInButton, signUpButton, helpButton;
    @FXML private ImageView goBack;
    private final Utilities utilities = new Utilities();

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        utilities.changeWindow(signUpButton, "Sign Up", "/fxmlFiles/SignUp.fxml", new SignUpController());
    }

    public void aboutUsOnAction(ActionEvent actionEvent) throws IOException {
        utilities.changeWindow(aboutUsButton, "About Us", "/fxmlFiles/AboutUs.fxml", new AboutUsController());
    }

    @FXML
    public void initialize() {
        goBack.setOnMouseClicked(event -> utilities.changeWindow(goBack, "Home Page", "/fxmlFiles/Home.fxml", new HomeController()));

    }
}
