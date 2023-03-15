package com.pbs.aplikacja.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity //Indeksujemy kolumny, które są najczęściej wykorzystywane do wyszukiwania studentów
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String indexNumber;
    private String email;
    private Boolean isLocal;

    @ManyToMany(mappedBy = "studenci")
    private Set<Projekt> projekty;

    public Student() {
    }

    public Student(String firstName, String lastName, String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }

    public Student(String firstName, String lastName, String indexNumber, String email, Boolean isLocal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
        this.email = email;
        this.isLocal = isLocal;
    }
}
