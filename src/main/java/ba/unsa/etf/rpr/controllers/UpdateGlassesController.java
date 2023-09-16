package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateGlassesController {

    @FXML private TextField nameField, priceField, categoryField, imageField;
    @FXML private Button saveButton, cancelButton;
    private final Utils utils = new Utils();
    private final GlassesManager gm = new GlassesManager();
    private final Glasses glassesToUpdate;
    private AdminPanelController adminPanelController;
    private User user = new User();

    public UpdateGlassesController(AdminPanelController adminAccountController, User user, Glasses room) {
        this.adminPanelController = adminAccountController;
        this.user = user;
        this.glassesToUpdate = room;
    }

    @FXML
    private void initialize() {
        if (glassesToUpdate == null) {
            // Prikazati upozorenje korisniku da nijedna soba nije izabrana
            showAlert("Please select glasses to update.");
            utils.closeCurrentStage(saveButton);
            return;
        }

        // Postavite polja za unos informacija o sobi na trenutne vrednosti sobe
        categoryField.setText(glassesToUpdate.getCategory());
        priceField.setText(String.valueOf(glassesToUpdate.getPrice()));
        nameField.setText(glassesToUpdate.getName());
        imageField.setText(glassesToUpdate.getImage());

        saveButton.setOnAction(event -> updateGlasses());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(cancelButton));
    }


    @FXML
    private void updateGlasses() {
        try {
            // Validacija unosa
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

            // Ažuriranje informacija o sobi
            glassesToUpdate.setCategory(categoryText);
            glassesToUpdate.setPrice(price);
            glassesToUpdate.setName(nameText);

            // Ažuriranje sobe u bazi podataka
            gm.update(glassesToUpdate);

            // Ažuriranje tabele u AdminAccountController-u
            adminPanelController.refreshTables();

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