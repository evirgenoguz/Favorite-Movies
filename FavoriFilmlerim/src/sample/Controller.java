package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ContextMenu listeSilMenusu;

    @FXML
    private BorderPane anaPencere;

    @FXML
    private ListView<Film> filmListesi;

    @FXML
    private Label labelCikisTarihi;

    @FXML
    private TextArea txtAreaFilmDetay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listeSilMenusu = new ContextMenu();
        MenuItem filmSil = new MenuItem("Filmi Listeden Kaldır");
        filmSil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Film silinecekFilm = filmListesi.getSelectionModel().getSelectedItem();
                secilenFilmiSil(silinecekFilm);
            }
        });

        listeSilMenusu.getItems().add(filmSil);

        filmListesi.setItems(FilmData.getInstance().getFilmListesi());
        txtAreaFilmDetay.setEditable(false);

        filmListesi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Film>() {
            @Override
            public void changed(ObservableValue<? extends Film> observableValue, Film film, Film t1) {
                //System.out.println("asaddssad");
                Film secilenFilm = filmListesi.getSelectionModel().getSelectedItem();

                txtAreaFilmDetay.setText(secilenFilm.getDetay());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                labelCikisTarihi.setText(secilenFilm.getCikisTarihi().format(formatter));
            }
        });
        filmListesi.getSelectionModel().selectFirst();

        filmListesi.setCellFactory(new Callback<ListView<Film>, ListCell<Film>>() {
            @Override
            public ListCell<Film> call(ListView<Film> filmListView) {
                ListCell<Film> yeniFilmListesi = new ListCell<>(){
                    @Override
                    protected void updateItem(Film film, boolean empty) {
                        super.updateItem(film, empty);
                        if (empty|| film == null){
                            setText(null);
                        }
                        else{
                            setText(film.getBaslik());
                        }
                    }
                };
                yeniFilmListesi.setContextMenu(listeSilMenusu);
                return yeniFilmListesi;
            }
        });

    }

    public void secilenFilmiSil(Film silinecekFilm){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Emin misiniz?");
        alert.setHeaderText("Silinecek Film : " +silinecekFilm.getBaslik());

        Optional<ButtonType> sonuc = alert.showAndWait();
        if(sonuc.get() == ButtonType.OK){
            FilmData.getInstance().filmSil(silinecekFilm);
        }

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
