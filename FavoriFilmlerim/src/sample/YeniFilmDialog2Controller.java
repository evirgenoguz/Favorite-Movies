package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class YeniFilmDialog2Controller {
    @FXML
    private DatePicker dateCikis;

    @FXML
    private TextArea txtFilmDetay;

    @FXML
    private TextField txtFilm;

    public Film yeniNotuEkle() {
        String filmAdi = txtFilm.getText().trim();
        String filmDetay = txtFilmDetay.getText().trim();
        LocalDate filmCikisTarihi = dateCikis.getValue();

        Film eklenecekFilm = new Film(filmAdi, filmDetay, filmCikisTarihi);
        FilmData.getInstance().filmEkle(eklenecekFilm);

        return eklenecekFilm;
    }
}
