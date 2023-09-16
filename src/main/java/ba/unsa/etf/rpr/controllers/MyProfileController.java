package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * My profile page controller that will print your data.
 */
public class MyProfileController {
    @FXML private Label usernameLabel, firstNameLabel, lastNameLabel, emailLabel, nameLabel;
    @FXML private ImageView logOutButton;
    @FXML private Button goBack, myOrdersButton;
    private final User user;

    public MyProfileController(User u) { this.user = u; }

    private final Utils utils= new Utils();
    public void initialize() {

        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        emailLabel.setText(user.getEmail());
        usernameLabel.setText(user.getUsername());
        nameLabel.setText(user.getFirstName());

        String fxmlTitle = (user.getRole() == 1) ? "Admin Panel" : "Home Page";
        String fxmlPath = (user.getRole() == 1) ? "/fxmlFiles/Admin/AdminPanel.fxml" : "/fxmlFiles/Customer/UserPanel.fxml";
        goBack.setOnMouseClicked(event -> utils.changeWindow(goBack, fxmlTitle, fxmlPath, (user.getRole() == 1) ? new AdminPanelController(user) : new UserPanelController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxmlFiles/Home.fxml", new HomeController()));

        if (user.getRole() == 1) myOrdersButton.setVisible(false);
        myOrdersButton.setOnMouseClicked(event -> utils.changeWindow(myOrdersButton, "My Orders", "/fxmlFiles/Customer/MyOrders.fxml", new MyOrdersController(user)));
    }
}