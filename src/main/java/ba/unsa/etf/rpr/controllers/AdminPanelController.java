package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;
public class AdminPanelController {

    private User user = new User();

    public AdminPanelController(User user) {
        this.user = user;
    }

    public AdminPanelController() {
    }

    @FXML private Button myProfileButton, aboutUsButton, addGlassesButton, updateGlassesButton, deleteGlassesButton, addOrderButton, updateOrderButton, deleteOrderButton;
    @FXML private ImageView logOutButton;
    @FXML private Label totalIncome, totalGlasses, totalUsers;
    public TableView<User> usersTable;
    public TableView<Glasses> glassesTable;
    public TableView<Order> orderTable;
    public TableColumn<User, String> firstNameColumn, lastNameColumn, emailColumn, usernameColumn;
    public TableColumn<Glasses, Double> priceColumn;
    public TableColumn<Glasses, String> categoryColumn, nameColumn;
    public TableColumn<Order, Integer> orderIdColumn, userIdColumn, glassesIdColumn;
    public TableColumn<Order, Double> totalColumn ;
    private final OrderManager o = new OrderManager();
    private final GlassesManager gm = new GlassesManager();
    private final UserManager um = new UserManager();
    private final Utils utils = new Utils();
    public void initialize() throws SQLException {

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxmlFiles/MyProfile.fxml", new MyProfileController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxmlFiles/Home.fxml", new HomeController()));
        aboutUsButton.setOnMouseClicked(event -> utils.changeWindow(aboutUsButton, "About Us", "/fxmlFiles/AboutUs.fxml", new AboutUsController(user)));


        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        glassesIdColumn.setCellValueFactory(new PropertyValueFactory<>("glassesID"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        refreshTables();

        totalIncome.setText(o.totalIncome() +" $");
        totalGlasses.setText(String.valueOf(gm.totalGlasses()));
        totalUsers.setText(String.valueOf(um.totalUsers()));

        updateGlassesButton.setOnMouseClicked(event -> {
            Glasses selectedGlasses = glassesTable.getSelectionModel().getSelectedItem();
            if (selectedGlasses == null) {
                // Prikazati upozorenje korisniku da nijedne naocale nisu izabrane
                showAlert("Please select a room to update.");
            } else {
                utils.openDialog("Update Glasses", "/fxmlFiles/Admin/Glasses/updateGlassesDialog.fxml", new UpdateGlassesController(this, user, selectedGlasses));
            }
        });

        deleteGlassesButton.setOnMouseClicked(event -> {
            Glasses selectedGlasses = glassesTable.getSelectionModel().getSelectedItem();
            if (selectedGlasses == null) {
                // Prikazati upozorenje korisniku da nijedna soba nije izabrana
                showAlert("Please select a room to update.");
            } else {
                utils.openDialog("Delete Room", "/fxmlFiles/Admin/Glasses/DeleteGlassesDialog.fxml", new DeleteGlassesController(this, user, selectedGlasses));
            }
        });

        updateOrderButton.setOnMouseClicked(event -> {
            Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();

            if (selectedOrder == null) {
                // Prikazati upozorenje korisniku da nijedna rezervacija nije izabrana
                showAlert("Please select a reservation to update.");
            } else {
                // Assuming you have a method to open a dialog for reservation update
                utils.openDialog("Update Order", "/fxmlFiles/Admin/Order/UpdateOrderDialog.fxml", new UpdateOrderController(this, user, selectedOrder));
            }
        });

        deleteOrderButton.setOnMouseClicked(event -> {
            Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();

            if (selectedOrder == null) {
                // Prikazati upozorenje korisniku da nijedna rezervacija nije izabrana
                showAlert("Please select a order to delete.");
            } else {
                // Assuming you have a method to open a dialog for reservation deletion
                utils.openDialog("Delete Order", "/fxmlFiles/Admin/Order/DeleteOrderDialog.fxml", new DeleteOrderController(this, user, selectedOrder));
            }
        });

        addGlassesButton.setOnMouseClicked(event -> utils.openDialog("Add Glasses", "/fxmlFiles/Admin/Glasses/AddGlassesDialog.fxml", new AddGlassesController(this, user)));
        addOrderButton.setOnMouseClicked(event -> utils.openDialog("Add Order", "/fxmlFiles/Admin/Order/AddOrderDialog.fxml", new AddOrderController(this, user)));

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * fetch data from DB
     */
    public void refreshTables() {
        try {
            // Fetch data from your data source (e.g., UserManager, RoomManager and ReservationManager)
            List<User> userList = UserManager.getAll();
            List<Glasses> glassesList = GlassesManager.getAll();
            List<Order> orderList = OrderManager.getAll();

            // Update the table views with the new data
            usersTable.setItems(FXCollections.observableList(userList));
            orderTable.setItems(FXCollections.observableList(orderList));
            glassesTable.setItems(FXCollections.observableList(glassesList));

            // Refresh the table views to update the UI
            usersTable.refresh();
            orderTable.refresh();
            glassesTable.refresh();
        } catch (GlassesException e) {
            throw new RuntimeException(e);
        }
    }
}
