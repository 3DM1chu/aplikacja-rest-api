package com.pbs.aplikacja.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="zadanie")
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

/*TODO Uzupełnij kod o zmienne reprezentujące pozostałe pola tabeli zadanie (patrz rys. 3.1),
. następnie wygeneruj dla nich akcesory i mutatory (Source -> Generate Getters and Setters),
. ponadto dodaj pusty konstruktor oraz konstruktor ze zmiennymi nazwa, opis i kolejnosc.
*/
}
