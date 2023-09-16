package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller za kreiranje i izmjenu prozora koji se otvori kada se aplikacija pokrene
 *
 * @author Nadina
 */
public class HomeController {

    @FXML
    private Button aboutUsButton, signInButton, signUpButton, helpButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label badUsername, badPassword, errorLabel;
    private User user = new User();
    private final Utils utils = new Utils();
    private final UserManager userManager = new UserManager();

    public HomeController() {
    }

    public HomeController(User user) {
        this.user = user;
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(signUpButton, "Sign Up", "/fxmlFiles/SignUp.fxml", new SignUpController());
    }

    public void aboutUsOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(aboutUsButton, "About Us", "/fxmlFiles/AboutUs.fxml", new AboutUsController());
    }

    public void helpOnAction(ActionEvent actionEvent) throws IOException {
        utils.changeWindow(helpButton, "Help", "/fxmlFiles/Help.fxml", new HelpController());
    }

    @FXML
    public void initialize() {

        usernameField.setOnAction(event -> passwordField.requestFocus());
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> badUsername.setText(""));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> badPassword.setText(""));

    }

    @FXML
    private void handleLogin() throws GlassesException, NoSuchAlgorithmException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean showBadUsername = username.trim().isEmpty();
        boolean showBadPassword = password.trim().isEmpty();

        if (showBadUsername || showBadPassword) {
            if (showBadUsername) {
                badUsername.setText("Username cannot be empty.");
            } else badUsername.setText("");

            if (showBadPassword) {
                badPassword.setText("Password cannot be empty");
            } else badPassword.setText("");

            return;
        }

        User user = userManager.findUsername(username);

        System.out.println(user.toString());

        if (user == null || !(Objects.equals(user.getPassword(), UserManager.hashPassword(password)))) {
            errorLabel.setText("Invalid username or password!");
            return;
        }

        String fxmlTitle = (user.getRole() == 1) ? "Admin Panel" : "Home";
        String fxmlPath = (user.getRole() == 1) ? "/fxmlFiles/Admin/AdminPanel.fxml" : "/fxmlFiles/Customer/UserPanel.fxml";
        utils.changeWindow(signInButton, fxmlTitle, fxmlPath, (user.getRole() == 1) ? new AdminPanelController(user) : new UserPanelController(user));
        
    }

}