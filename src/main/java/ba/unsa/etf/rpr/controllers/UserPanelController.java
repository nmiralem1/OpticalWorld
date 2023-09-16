package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
/**
 * Controller that will show home page for User.
 */
public class UserPanelController {
    @FXML
    private Tab tab1, tab2, tab3;
    @FXML
    private ScrollPane scroll1, scroll2, scroll3;
    @FXML
    private Label totalLabel, nameLabel;
    @FXML
    private Button buyButton, myProfileButton, myOrdersButton;
    @FXML
    private ImageView logOutButton;

    private Glasses selectedGlasses = null;
    private GlassesManager gm = new GlassesManager();
    private OrderManager o = new OrderManager();
    private double totalAmount = 0.0;

    private User user = new User();
    private Utils utils = new Utils();

    public UserPanelController(User user) {
        this.user = user;
    }

    public UserPanelController() {
    }

    private ObservableList<Glasses> selectedGlassesList = FXCollections.observableArrayList();
    @FXML private TableView<Glasses> selectedGlassesTableView;

    // Ova metoda će se pozvati kada se kontroler inicijalizuje
    public void initialize() {

        myProfileButton.setOnMouseClicked(event -> utils.changeWindow(myProfileButton, "My Profile", "/fxmlFiles/MyProfile.fxml", new MyProfileController(user)));
        logOutButton.setOnMouseClicked(event -> utils.changeWindow(logOutButton, "Main Page", "/fxmlFiles/Home.fxml", new HomeController()));
        myOrdersButton.setOnMouseClicked(event -> utils.changeWindow(myOrdersButton, "My Orders", "/fxmlFiles/Customer/MyOrders.fxml", new MyOrdersController(user)));

        nameLabel.setText(user.getFirstName());
        loadGlasses("Dioptric", tab3, scroll3);
        loadGlasses("Sunglasses", tab1, scroll1);
        loadGlasses("Lenses", tab2, scroll2);

        /** Set listener for chosen items
         *
         */
        selectedGlassesList.addListener(new ListChangeListener<Glasses>() {
            @Override
            public void onChanged(Change<? extends Glasses> change) {
                selectedGlassesTableView.getItems().setAll(selectedGlassesList);
            }
        });

        TableColumn<Glasses, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(100);

        TableColumn<Glasses, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(70);

        selectedGlassesTableView.getColumns().addAll(nameColumn, priceColumn);
    }

    private void loadGlasses(String type, Tab tab, ScrollPane scroll) {
        List<Glasses> glassesList = gm.getAllByCategory(type);

        FlowPane productContainer = new FlowPane();
        productContainer.setHgap(20);
        productContainer.setVgap(20);
        productContainer.setPadding(new Insets(20));

        for (Glasses glasses : glassesList) {
            Image image = new Image(glasses.getImage());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(120);
            imageView.setFitWidth(190);

            Label productName = new Label(glasses.getName());
            productName.setTextFill(Color.BLACK);
            productName.setFont(Font.font(null, FontWeight.BOLD, 16));

            Label productPrice = new Label("$" + glasses.getPrice());
            productPrice.setTextFill(Color.BLACK);
            productPrice.setFont(Font.font(null, FontWeight.NORMAL, 14));

            // Shadow effect
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5);
            imageView.setEffect(dropShadow);

            // Creating VBox for each item
            VBox productBox = new VBox();
            productBox.setAlignment(Pos.CENTER);
            productBox.setSpacing(10);
            productBox.getChildren().addAll(imageView, productName, productPrice);

            productBox.setOnMouseClicked(event -> selectGlasses(glasses));

            productContainer.getChildren().add(productBox);
        }

        scroll.setContent(productContainer);
    }

    private void selectGlasses(Glasses glasses) {
        if (selectedGlassesList.contains(glasses)) {
            selectedGlassesList.remove(glasses);
            totalAmount -= glasses.getPrice();
            System.out.println("Removed: " + glasses.getId());
        } else {
            selectedGlassesList.add(glasses);
            totalAmount += glasses.getPrice();
            System.out.println("Added: " + glasses.getId());
        }

        totalLabel.setText("$" + totalAmount);
        totalLabel.setTextFill(Color.BLACK);
        selectedGlassesTableView.getItems().setAll(selectedGlassesList);
    }


    @FXML
    private void buyButtonClicked() {
        try{
            System.out.println(selectedGlassesList);
        if (!selectedGlassesList.isEmpty()) {
            saveOrder();
            // For now, just reset the selection
            selectedGlassesList.clear();
            totalAmount = 0.0;
            totalLabel.setText("$" + totalAmount);
        } else {
            totalLabel.setText("Select a product before buying.");
            totalLabel.setTextFill(Color.RED);
        }
        }catch(Exception e){e.printStackTrace();}
    }

    private void saveOrder() {
        try{
        for(Glasses glass: selectedGlassesList){
            Order order = new Order();
            order.setTotal((int) glass.getPrice());
            order.setUserID(user);
            order.setGlassesID(gm.getById(glass.getId()));

            o.add(order);
        }}catch (Exception e){e.printStackTrace();}
    }
}