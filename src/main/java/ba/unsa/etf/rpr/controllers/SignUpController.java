package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;
/**
 * Controller that will request your data and sign you up.
 */
public class SignUpController extends Component {

    @FXML
    private Button aboutUsButton, signInButton, signUpButton, helpButton;
    @FXML private ImageView goBack;

    public TextField name, surname, email, username;
    public Label badName, badSurname, badEmail, badUsername, badPassword;

    public PasswordField password;
    private User user = new User();
    private final Utilities utilities = new Utilities();
    private final UserManager userManager = new UserManager();
    @FXML
    public void initialize(){

        name.textProperty().addListener((observable, oldValue, newValue) -> badName.setText(""));
        surname.textProperty().addListener((observable, oldValue, newValue) -> badSurname.setText(""));
        username.textProperty().addListener((observable, oldValue, newValue) -> badUsername.setText(""));
        password.textProperty().addListener((observable, oldValue, newValue) -> badPassword.setText(""));
        email.textProperty().addListener((observable, oldValue, newValue) -> badEmail.setText(""));

        goBack.setOnMouseClicked(event -> utilities.changeWindow(goBack, "Home Page", "/fxmlFiles/Home.fxml", new HomeController()));
        aboutUsButton.setOnMouseClicked(event -> utilities.changeWindow(aboutUsButton, "About Us", "/fxmlFiles/AboutUs.fxml", new AboutUsController()));
        helpButton.setOnMouseClicked(event -> utilities.changeWindow(helpButton, "Help", "/fxmlFiles/Help.fxml", new HelpController()));

    }

    @FXML
    private void signUpButtonAction() throws GlassesException, NoSuchAlgorithmException {

        // Retrieve user input from form fields
        String nameInput = name.getText();
        String surnameInput = surname.getText();
        String emailInput = email.getText();
        String usernameInput = username.getText();
        String passwordInput = password.getText();
        boolean check = false;
        // Validate the input
        if (Objects.equals(name.getText(), "")) {
            badName.setText("Name can't be empty.");
            check = true;
        }
        if (Objects.equals(surname.getText(), "")) {
            // Display an error message
            badSurname.setText("Surname can't be empty.");
            check = true;
        }
        if (Objects.equals(username.getText(), "")) {
            // Display an error message
            badUsername.setText("Username can't be empty.");
            check = true;
        }
        if (Objects.equals(email.getText(), "")) {
            // Display an error message
            badEmail.setText("Email can't be empty.");
            check = true;
        }
        if (Objects.equals(password.getText(), "")) {
            // Display an error message
            badPassword.setText("Password can't be empty");
            check = true;
        } else {
            // Use a regular expression to check the email format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(emailInput).matches()) {
                // Display an error message
                badEmail.setText("Invalid email format.");
                check = true;
            }
            // Check if the username already exists
            if (userManager.findUsername(usernameInput) != null) {
                badUsername.setText("Username is already taken.");
                return;
            }
        }
        if (!check) {
            // Create a new user data object and set the instance variables
            user.setFirstName(nameInput);
            user.setLastName(surnameInput);
            user.setEmail(emailInput);
            user.setUsername(usernameInput);
            user.setPassword(UserManager.hashPassword(passwordInput));

            try {
                // Add the new user to the database
                user = UserManager.add(user);
                utilities.changeWindow(signUpButton, "Sign In", "/fxmlFiles/Home.fxml", new HomeController());
            } catch (Exception e) {
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
    }
}
