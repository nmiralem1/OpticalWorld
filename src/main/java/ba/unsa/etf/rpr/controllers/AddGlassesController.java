package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * Controller that will add new glasses to the database.
 */
public class AddGlassesController {


    @FXML private TextField nameField, priceField, categoryField, imageField;
    @FXML private Button saveButton;
    private final Utilities utilities = new Utilities();
    private final GlassesManager gm = new GlassesManager();
    private AdminPanelController adminPanelController;
    private User user = new User();

    private AdminPanelController adminAccountController;

    public AddGlassesController(AdminPanelController adminAccountController, User user) {
        this.user = user;
        this.adminAccountController = adminAccountController;
    }

    @FXML
    private void initialize() {
        saveButton.setOnMouseClicked(event -> saveGlasses());
    }

    /**
     * @author nmiralem1
     * Initialize method with no parameters.
     */
    @FXML
    private void saveGlasses() {
        try {
            // Validation of the input
            String categoryText = categoryField.getText().trim();
            String nameText = nameField.getText().trim();
            String priceText = priceField.getText().trim();

            if (categoryText.isEmpty() || priceText.isEmpty()) {
                showAlert("Please fill in all fields.");
                return;
            }

            int price = Integer.parseInt(priceText);

            if (price < 0) {
                showAlert("Price must be a positive number.");
                return;
            }

            Glasses glasses = new Glasses();
            glasses.setCategory(categoryText);
            glasses.setPrice(price);
            glasses.setName(nameText);

            // Updating glasses in database
            GlassesManager.add(glasses);

            // Updating table in AdminPanelController
            adminPanelController.refreshTables();

            utilities.closeWindow(saveButton);
        } catch (NumberFormatException | GlassesException e) {
            showAlert("Invalid input. Please enter valid numeric values.");
        }
    }
    /**
     * @param message
     * @author nmiralem1
     * Method that will show alert if some unvalid action is occured
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}