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

import java.time.format.DateTimeParseException;
import java.util.List;

public class UpdateOrderController {
    @FXML private Button saveButton;

    private final Utils utils = new Utils();
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
            // Prikazati upozorenje korisniku da nijedna soba nije izabrana
            showAlert("Please select glasses to update.");
            utils.closeCurrentStage(saveButton);
            return;
        }

        saveButton.setOnMouseClicked(event -> saveOrder());

        List<Glasses> glasses = GlassesManager.getAll();
        List<User> users = UserManager.getAll();

        // Extract room IDs and populate the ComboBox
        for (Glasses glass : glasses) {
            glassesComboBox.getItems().add(String.valueOf(glass.getId()));
        }

        for (User user : users) {
            userComboBox.getItems().add(String.valueOf(user.getId()));
        }

        // Postavite polja za unos informacija o sobi na trenutne vrednosti sobe
//        glassesComboBox.se(selectedOrder.getGlassesID());
  //      userIdField.setText(selectedOrder.getUserID());
    }


    private void saveOrder() {
        try {
            // Validacija unosa
            String selectedUser = userComboBox.getValue();
            String selectedGlasses = glassesComboBox.getValue();

            if (selectedGlasses == null || selectedUser == null) {
                showAlert("Please select a room and valid check-in/check-out dates.");
                return;
            }

            double totalPrice = g.getById(Integer.parseInt(selectedGlasses)).getPrice();
            // Calculate the total based on room price and number of nights

            Order order = new Order();
            order.setGlassesID(g.getById(Integer.parseInt(selectedGlasses)));
            order.setTotal((int) totalPrice);
            order.setUserID(user);

            // Save the reservation to the database (you should implement this logic)
            o.update(order);

            // Ažuriranje tabele u AdminAccountController-u
            adminAccountController.refreshTables();

            // Close the dialog or perform other actions as needed
            utils.closeCurrentStage(saveButton);
        } catch (DateTimeParseException e) {
            showAlert("Invalid date format. Please enter valid dates.");
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