package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller za kreiranje i izmjenu prozora koji se otvori kada se aplikacija pokrene
 *
 * @author Nadina
 */
public class PocetnaController {
    public Button btnRegistracija, btnPrijava;
    public TextField idEmail;
    public PasswordField idPassword;
    public Label idLabel4;
    UserManager korisnikManager = new UserManager();

    public void actionPrijava(ActionEvent actionEvent) throws GlassesException {
        String uneseniEmail = idEmail.getText();
        String uneseniPassword = idPassword.getText();
        List<User> listaPrijavljenihKorisnika = korisnikManager.getAll();
        int brojac = 0, brojac1 = 0;

        if (Objects.equals(idPassword.getText(), "") || Objects.equals(idEmail.getText(), "")) {
            idLabel4.setText("Polje ne može biti prazno !");
            brojac++;
        } else {
            for (User k : listaPrijavljenihKorisnika) {
                if (k.getEmail().equals(uneseniEmail) && k.getPassword().equals(uneseniPassword)) {
                    brojac1++;
                }
            }
            if (brojac1 != 0)
                idLabel4.setText("");
            else
                idLabel4.setText("Neispravni uneseni podaci!");
        }

        if (brojac == 0 && brojac1 != 0) {
            try {

                Stage stage =(Stage)btnPrijava.getScene().getWindow();
                stage.close();
                Stage stage1 = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/proizvodi.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                stage1.setTitle("CeraVe ponuda");
                stage1.setScene(scene);
                stage1.setResizable(false);
                stage1.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Otvaranje prozora za registraciju ukoliko korisnik nema račun na aplikaciji
     * @param actionEvent
     * @throws IOException
     */
    public void actionRegistracija(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage =(Stage)btnRegistracija.getScene().getWindow();
            stage.close();
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/registracija.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage1.setTitle("Registracija CeraVe");
            stage1.setScene(scene);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Događaj za otvaranje prozora za pomoć
     *
     * @param actionEvent
     */
    public void actionOtvaranjeHelp(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/help.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage1.setTitle("Pomoć CeraVe");
            stage1.setScene(scene);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Događaj za otvaranje prozora za više informacija
     *
     * @param actionEvent
     */
    public void actionOtvaranjeONama(ActionEvent actionEvent) throws IOException {
        try {
            /*Stage stage =(Stage)btnONama.getScene().getWindow();
            stage.close();*/
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/registracija.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage1.setTitle("CeraVe");
            stage1.setScene(scene);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void promjenaBoje(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgb(196,196,196); -fx-background-radius:10px;");
    }

    @FXML
    public void vracanjeBoje(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgb(223,223,223); -fx-background-radius: 10px;");
    }

    @FXML
    public void promjenaBoje1(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgb(128,188,255);");
    }
    @FXML
    public void vracanjeBoje1(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: rgb(36,140,255);");
    }
}