package com.pbs.aplikacja.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.aop.target.LazyInitTargetSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Projekt {

    @Id
    @GeneratedValue
    @Column(name = "projekt_id")
    private Integer projektId;
    private String nazwa;
    private String opis;

    @CreationTimestamp
    @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
    private LocalDateTime dataCzasUtworzenia;

    @UpdateTimestamp
    @Column(name = "dataczas_oddania", nullable = false)
    private LocalDateTime data_oddania;

    @UpdateTimestamp
    @Column(name = "dataczas_modyfikacji", nullable = false)
    private LocalDateTime dataCzasModyfikacji;

    @OneToMany(mappedBy = "projekt")
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name="projekt_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> studenci;
}

