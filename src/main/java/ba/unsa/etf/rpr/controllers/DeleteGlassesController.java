package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Controller that will delete glasses from database.
 */
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
            showAlert("Please select glasses to delete.");
            utils.closeCurrentStage(confirmationLabel);
            return;
        }

        String confirmationMessage = "Are you sure you want to delete glasses with id #" + glassesToDelete.getId() + "?";
        confirmationLabel.setText(confirmationMessage);

        confirmButton.setOnAction(event -> deleteGlasses());
        cancelButton.setOnAction(event -> utils.closeCurrentStage(confirmationLabel));
    }

    @FXML
    private void deleteGlasses() {
        try {
            // Delete glasses from database
            gm.delete(glassesToDelete.getId());

            // Update table ind AdminPanelController
            adminAccountController.refreshTables();

            utils.closeCurrentStage(confirmationLabel);
        } catch (GlassesException e) {
            showAlert("An error occurred while deleting glasses.");
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
