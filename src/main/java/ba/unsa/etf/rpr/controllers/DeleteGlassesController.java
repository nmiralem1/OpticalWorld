package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeleteGlassesController {

    @FXML private Label confirmationLabel;
    @FXML private Button confirmButton, cancelButton;

    private final Utils utils = new Utils();
    private final GlassesManager gm = new GlassesManager();
    private final Glasses glassesToDelete;
    private User user = new User();
    private AdminPanelController adminAccountController;

    public DeleteGlassesController(AdminPanelController adminAccountController, User user, Glasses glassesToDelete) {
        this.adminAccountController = adminAccountController;
        this.user = user;
        this.glassesToDelete = glassesToDelete;
    }

    @FXML
    private void initialize() {
        if (glassesToDelete == null) {
            // Prikazati upozorenje korisniku da nijedna soba nije izabrana
            showAlert("Please select glasses to delete.");
            utils.closeCurrentStage(confirmationLabel);
            return;
        }

        String confirmationMessage = "Are you sure you want to delete Room #" + glassesToDelete.getId() + "?";
        confirmationLabel.setText(confirmationMessage);

        confirmButton.setOnAction(event -> deleteRoom());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(confirmationLabel));
    }

    @FXML
    private void deleteRoom() {
        try {
            // Obriši sobu iz baze podataka
            gm.delete(glassesToDelete.getId());

            // Ažuriraj tabelu u AdminAccountController-u
            adminAccountController.refreshTables();

            utils.closeCurrentStage(confirmationLabel);
        } catch (GlassesException e) {
            showAlert("An error occurred while deleting the room.");
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
