package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class FilmData {

    private static FilmData instance = new FilmData();
    private DateTimeFormatter formatter;
    private ObservableList<Film> filmListesi;

    public ObservableList<Film> getFilmListesi() {
        return filmListesi;
    }

    private FilmData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static FilmData getInstance(){
        return instance;
    }

    public void filmEkle(Film eklenecekFilm){
        filmListesi.add(eklenecekFilm);
    }

    public void filmSil(Film silinecekFilm){
        filmListesi.remove(silinecekFilm);
    }


    //Uygulama Açıldıgında dosyadan tüm filmler getirilip observable listeye atanır
    public void dosyadanFilmleriGetir(){
        //Dosya İçeriği Şu Şekilde
        //Başlık /t  Detayı /t Çıkış Tarihi şeklinde olacak

        filmListesi = FXCollections.observableArrayList();

        try(BufferedReader okuyucu = new BufferedReader(new FileReader("filmler.txt"))){
            String tekFilmSatir;

            while((tekFilmSatir = okuyucu.readLine()) != null){
                String[] filmParcalari = tekFilmSatir.split("\t");
                String filmBaslik = filmParcalari[0];
                String filmDetay = filmParcalari[1];
                String filmBitisTarihi = filmParcalari[2];

                LocalDate bitis = LocalDate.parse(filmBitisTarihi,formatter);

                Film okunanFilm = new Film(filmBaslik, filmDetay, bitis);
                filmListesi.add(okunanFilm);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //uygulama kapatılırken observable listedeki filmler dosyaya yazılır
    public void dosyayaFilmleriYaz(){
        try(BufferedWriter yazici = new BufferedWriter(new FileWriter("filmler.txt"))){
            Iterator<Film> iterator = filmListesi.iterator();
            while(iterator.hasNext()){
                Film oankiFilm = iterator.next();
                yazici.write(String.format("%s\t%s\t%s",oankiFilm.getBaslik(),oankiFilm.getDetay(),oankiFilm.getCikisTarihi().format(formatter)));
                yazici.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
