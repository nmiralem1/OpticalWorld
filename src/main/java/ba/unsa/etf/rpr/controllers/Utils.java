package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Utils {
    public void changeWindow(Node n, String title, String file, Object controller) {
        closeCurrentStage(n); // Close the current stage (if any)
        try {
            openDialog(title, file, controller);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void closeCurrentStage(Node node) {
        // Close the current window
        Stage stage = (Stage) node.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }

    public void openDialog(String title, String file, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.getIcons().add(new Image("images/logo-opticalWorld.png"));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}