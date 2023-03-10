package com.pbs.aplikacja.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name="projekt") //TODO Indeksować kolumny, które są najczęściej wykorzystywane do wyszukiwania projektów
public class Projekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projekt_id") //tylko jeżeli nazwa kolumny w bazie danych ma być inna od nazwy zmiennej
    private Integer projektId;
    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column(nullable = false, length = 50)
    private String opis;

    @CreationTimestamp
    @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
    private LocalDateTime dataCzasUtworzenia;

    @UpdateTimestamp
    @Column(name = "dataczas_modyfikacji", nullable = false)
    private LocalDateTime data_oddania;

    @UpdateTimestamp
    @Column(name = "dataczas_modyfikacji", nullable = false)
    private LocalDateTime dataCzasModyfikacji;

    public Integer getProjektId() {
        return projektId;
    }

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataCzasUtworzenia() {
        return dataCzasUtworzenia;
    }

    public void setDataCzasUtworzenia(LocalDateTime dataCzasUtworzenia) {
        this.dataCzasUtworzenia = dataCzasUtworzenia;
    }

    public LocalDateTime getData_oddania() {
        return data_oddania;
    }

    public void setData_oddania(LocalDateTime data_oddania) {
        this.data_oddania = data_oddania;
    }

    public LocalDateTime getDataCzasModyfikacji() {
        return dataCzasModyfikacji;
    }

    public void setDataCzasModyfikacji(LocalDateTime dataCzasModyfikacji) {
        this.dataCzasModyfikacji = dataCzasModyfikacji;
    }

    /*TODO Uzupełnij kod o zmienne reprezentujące pozostałe pola tabeli projekt (patrz rys. 3.1),
. następnie wygeneruj dla nich tzw. akcesory i mutatory (Source -> Generate Getters and Setters),
. ponadto dodaj pusty konstruktor oraz konstruktor ze zmiennymi nazwa i opis.
*/
}

