package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
/**
 * Controller that will generate all your previous orders.
 */
public class MyOrdersController {

    @FXML private Button homeButton, myProfileButton;
    private final User user;

    public MyOrdersController(User u){this.user=u;}
    @FXML
    public TableView<Order> ordersTable;
    @FXML public TableColumn<Order, String> orderIdColumn, glassesIdColumn;
    @FXML public TableColumn<Order, Double> totalColumn;

    private final OrderManager o = new OrderManager();
    private final GlassesManager gm = new GlassesManager();
    private final Utils utils = new Utils();
    @FXML private Label nameLabel;

    void refreshTable(){
        try {
            ordersTable.setItems(FXCollections.observableList(o.getAllForUser(user)));
            ordersTable.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws SQLException {
        nameLabel.setText(user.getFirstName());

        homeButton.setOnMouseClicked(event -> utils.changeWindow(homeButton, "Home Page", "/fxmlFiles/Customer/UserPanel.fxml", new UserPanelController(user)));
        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxmlFiles/MyProfile.fxml", new MyProfileController(user)));

        glassesIdColumn.setCellValueFactory(new PropertyValueFactory<>("glassesID"));
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        refreshTable();
    }
}