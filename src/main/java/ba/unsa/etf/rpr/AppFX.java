package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.AdminPanelController;
import ba.unsa.etf.rpr.controllers.HomeController;
import ba.unsa.etf.rpr.controllers.MyProfileController;
import ba.unsa.etf.rpr.controllers.UserPanelController;
import ba.unsa.etf.rpr.domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The type App fx.
 */
public class AppFX extends Application
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/Home.fxml"));
        HomeController controller = new HomeController();
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);

        // window icon
        primaryStage.getIcons().add(new Image("images/logo-opticalWorld.png"));

        // mouse press and drag listeners for window dragging
        root.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.show();
    }}