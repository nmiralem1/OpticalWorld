package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.time.format.DateTimeParseException;
import java.util.List;
/**
 * Controller that will add order to the database.
 */

public class AddOrderController {
    @FXML private Button saveButton;

    private final Utils utils = new Utils();
    private final User user;
    private final OrderManager o = new OrderManager();
    private AdminPanelController adminAccountController;
    public AddOrderController(AdminPanelController adminAccountController, User user) {
        this.adminAccountController = adminAccountController;
        this.user = user;
    }

    @FXML private ComboBox<String> glassesComboBox, userComboBox;
    private GlassesManager g = new GlassesManager();

    @FXML
    private void initialize() throws GlassesException {
        saveButton.setOnMouseClicked(event -> saveOrder());

        List<Glasses> glasses = GlassesManager.getAll();

        // Extract room IDs and populate the ComboBox
        for (Glasses glass : glasses) {
            glassesComboBox.getItems().add(String.valueOf(glass.getId()));
        }
    }

    /**
     * function to save information about new order.
     */

    private void saveOrder() {
        try {
            //First we have to make sure that selected user and selected glasses are valid
            String selectedUser = userComboBox.getValue();
            String selectedGlasses = glassesComboBox.getValue();

            if (selectedGlasses == null || selectedUser == null) {
                showAlert("Please select a glasses with valid ID!");
                return;
            }

            double totalPrice = g.getById(Integer.parseInt(selectedGlasses)).getPrice();
            // Calculate the total price based on items in your order

            Order order = new Order();
            order.setGlassesID(g.getById(Integer.parseInt(selectedGlasses)));
            order.setTotal((int) totalPrice);
            order.setUserID(user);

            // Save the order to the database
            o.add(order);

            // update table in AdminAccountController
            adminAccountController.refreshTables();

            // Close the dialog or perform other actions if needed
            utils.closeCurrentStage(saveButton);

        } catch (NumberFormatException | GlassesException e) {
            showAlert("Invalid input. Please enter valid numeric values.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}