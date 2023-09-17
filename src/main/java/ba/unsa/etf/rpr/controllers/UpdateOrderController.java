package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.List;
/**
 * Controller that will update specific order from database.
 */
public class UpdateOrderController {
    @FXML private Button saveButton;

    private final Utilities utilities = new Utilities();
    private final User user;
    private final OrderManager o = new OrderManager();

    private Order selectedOrder = new Order();
    private AdminPanelController adminAccountController;
    public UpdateOrderController(AdminPanelController adminAccountController, User user, Order selectedOrder) {
        this.adminAccountController = adminAccountController;
        this.user = user;
        this.selectedOrder = selectedOrder;
    }
    @FXML private ComboBox<String> glassesComboBox, userComboBox;
    private GlassesManager g = new GlassesManager();

    @FXML
    private void initialize() throws GlassesException {
        if (selectedOrder == null) {
            showAlert("Please select glasses to update.");
            utilities.closeWindow(saveButton);
            return;
        }

        saveButton.setOnMouseClicked(event -> saveOrder());

        List<Glasses> glasses = GlassesManager.getAll();
        List<User> users = UserManager.getAll();

        for (Glasses glass : glasses) {
            glassesComboBox.getItems().add(String.valueOf(glass.getId()));
        }

        for (User user : users) {
            userComboBox.getItems().add(String.valueOf(user.getId()));
        }

//        glassesComboBox.se(selectedOrder.getGlassesID());
  //      userIdField.setText(selectedOrder.getUserID());
    }


    private void saveOrder() {
        try {
            String selectedUser = userComboBox.getValue();
            String selectedGlasses = glassesComboBox.getValue();

            if (selectedGlasses == null || selectedUser == null) {
                showAlert("Please select glasses");
                return;
            }

            double totalPrice = g.getById(Integer.parseInt(selectedGlasses)).getPrice();

            Order order = new Order();
            order.setGlassesID(g.getById(Integer.parseInt(selectedGlasses)));
            order.setTotal((int) totalPrice);
            order.setUserID(user);

            o.update(order);
            adminAccountController.refreshTables();

            // Close the dialog or perform other actions as needed
            utilities.closeWindow(saveButton);
        }  catch (NumberFormatException | GlassesException e) {
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