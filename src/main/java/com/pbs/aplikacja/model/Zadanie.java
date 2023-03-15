package com.pbs.aplikacja.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Zadanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="zadanie_id")
    private Integer zadanieId;

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Projekt projekt;

    @Column(nullable = false, length = 50)
    private String nazwa;
    @Column(nullable = false)
    private Integer kolejnosc;
    @Column(nullable = false, length = 1000)
    private String opis;
    @Column(name = "dataczas_dodania", nullable = false)
    private LocalDateTime dataczas_dodania;

    public Integer getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(Integer zadanieId) {
        this.zadanieId = zadanieId;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataczas_dodania() {
        return dataczas_dodania;
    }

    public void setDataczas_dodania(LocalDateTime dataczas_dodania) {
        this.dataczas_dodania = dataczas_dodania;
    }
}
