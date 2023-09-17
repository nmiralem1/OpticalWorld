package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Controller that will delete specific order from database.
 */
public class DeleteOrderController {

    @FXML private Label confirmationLabel;
    @FXML private Button confirmButton, cancelButton;
    private final Utilities utilities = new Utilities();
    private final OrderManager r = new OrderManager();
    private final Order orderToDelete;
    private AdminPanelController adminAccountController;
    private User user = new User();

    public DeleteOrderController(AdminPanelController adminAccountController, User u, Order orderToDelete) {
        this.adminAccountController = adminAccountController;
        this.orderToDelete = orderToDelete;
        this.user = u;
    }

    @FXML
    private void initialize() {
        if (orderToDelete == null) {
            // Display a warning to the user that no order is selected
            showAlert("Please select an order to delete.");
            utilities.closeWindow(confirmationLabel);
            return;
        }

        String confirmationMessage = "Are you sure you want to delete Order #" + orderToDelete.getId() + "?";
        confirmationLabel.setText(confirmationMessage);

        confirmButton.setOnAction(event -> deleteOrder());
        cancelButton.setOnAction(event -> utilities.closeWindow(cancelButton));
    }

    @FXML
    private void deleteOrder() {
        try {
            // Delete the order from the database
            r.delete(orderToDelete.getId());

            // Update the table in AdminPanelController
            adminAccountController.refreshTables();

            utilities.closeWindow(confirmButton);
        } catch (Exception e) {
            showAlert("An error occurred while deleting the order.");
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
