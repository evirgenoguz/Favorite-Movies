package sample;

import java.time.LocalDate;

public class Film {
    private String baslik;
    private String detay;
    private LocalDate cikisTarihi;

    public Film(String baslik, String detay, LocalDate cikisTarihi) {
        this.baslik = baslik;
        this.detay = detay;
        this.cikisTarihi = cikisTarihi;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public LocalDate getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(LocalDate cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    @Override
    public String toString() {
        return  baslik;
    }
}
