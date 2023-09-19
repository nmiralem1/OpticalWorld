package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller that will add new glasses to the database.
 */
public class AddGlassesController {


    @FXML private TextField nameField, priceField, categoryField;
    @FXML private Button saveButton, imageField;
    @FXML private ImageView selectedImageView;
    @FXML private ComboBox<String> categoryComboBox;

    private final Utilities utilities = new Utilities();
    private final GlassesManager gm = new GlassesManager();
    private AdminPanelController adminPanelController;
    private User user = new User();
    private String imagePath;

    public AddGlassesController(AdminPanelController adminAccountController, User user) {
        this.user = user;
        this.adminPanelController = adminAccountController;
    }

    String[] categories = { "Lenses", "Dioptric", "Sunglasses" };

    @FXML
    private void initialize() {
        categoryComboBox.setItems(FXCollections.observableArrayList(categories));

        saveButton.setOnMouseClicked(event -> saveGlasses());
        imageField.setOnMouseClicked(event -> selectImage());
    }

    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Postavljen filter za slike ako želimo ograničiti odabir samo na određene vrste slika (npr. JPEG, PNG).
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));

        File selectedFile = fileChooser.showOpenDialog(saveButton.getScene().getWindow());

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath().replace(File.separator, "/");
            Image image = new Image(selectedFile.toURI().toString());
            selectedImageView.setImage(image); // Postavljanje odabrane slike u ImageView
        }
    }

    /**
     * @author nmiralem1
     * Initialize method with no parameters.
     */
    @FXML
    private void saveGlasses() {
        try {
            // Validacija unosa
            String categoryText = categoryComboBox.getValue(); // Dobijanje vrednosti iz ComboBox-a
            String nameText = nameField.getText().trim();
            String priceText = priceField.getText().trim();

            if (categoryText == null || categoryText.isEmpty() || priceText.isEmpty() || imagePath == null) {
                showAlert("Please fill in all fields and select an image.");
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
            glasses.setImage(imagePath); // Postavljanje putanje slike

            // Dodavanje naočala u bazu
            GlassesManager.add(glasses);

            // Ažuriranje tabele u AdminPanelController
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