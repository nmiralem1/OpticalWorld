package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
/**
 * About us page controller with information about Optical World.
 */
public class AboutUsController {

    @FXML
    private Button signUpButton, helpButton;
    @FXML private ImageView goBack;
    private final Utils utils = new Utils();
    private User user = new User();

    public AboutUsController(User user) {
        this.user = user;
    }

    public AboutUsController() {
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(signUpButton, "Sign Up", "/fxmlFiles/SignUp.fxml", new SignUpController());
    }

    public void helpOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(helpButton, "Help", "/fxmlFiles/Help.fxml", new HelpController());
    }
    /**
     * Initialize method with no parameters.
     */
    @FXML
    public void initialize() {
        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, "Home Page", "/fxmlFiles/Home.fxml", new HomeController()));

    }

}
