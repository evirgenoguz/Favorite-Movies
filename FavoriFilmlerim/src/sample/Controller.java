package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private BorderPane anaPencere;

    @FXML
    private ListView<Film> filmListesi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filmListesi.setItems(FilmData.getInstance().getFilmListesi());
    }

    @FXML
    void filmEkleDialog(ActionEvent event) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(anaPencere.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("yeniFilmDialog2.fxml"));


        dialog.setTitle("Yeni Film Ekle");
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> sonuc = dialog.showAndWait();

        if(sonuc.get() == ButtonType.APPLY){
            //System.out.println("Apply Tıklandı");
            YeniFilmDialog2Controller dialogController = fxmlLoader.getController();
            Film eklenenFilm = dialogController.yeniNotuEkle();

            filmListesi.getSelectionModel().select(eklenenFilm);
        }

    }

    @FXML
    void uygulamayiKapat(ActionEvent event) {
        Platform.exit();
    }


}
