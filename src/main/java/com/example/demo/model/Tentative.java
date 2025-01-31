package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tentatives")
public class Tentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_de_debu", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "Dure_suspension", nullable = false)
    private int dureeSuspension;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    private Utilisateur utilisateur;

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDureeSuspension() {
        return dureeSuspension;
    }

    public void setDureeSuspension(int dureeSuspension) {
        this.dureeSuspension = dureeSuspension;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
