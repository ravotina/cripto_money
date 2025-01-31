package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token", length = 200, unique = true)
    @Size(max = 200, message = "Le token ne doit pas dépasser 200 caractères")
    private String token;

    @Column(name = "date_debu", nullable = false)
    @NotNull(message = "La date de début ne peut pas être null")
    private LocalDateTime dateDebut;

    @Column(name = "dure_de_vie", nullable = false)
    @NotNull(message = "La durée de vie ne peut pas être null")
    private int dureeDeVie; // En secondes

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_1", nullable = false)
    private Utilisateur utilisateur;


    // Constructeurs
    public Token() {}

    public Token(String token, LocalDateTime dateDebut, int dureeDeVie, Utilisateur utilisateur) {
        this.token = token;
        this.dateDebut = dateDebut;
        this.dureeDeVie = dureeDeVie;
        this.utilisateur = utilisateur;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public void setDureeDeVie(int dureeDeVie) {
        this.dureeDeVie = dureeDeVie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Méthode pour vérifier si le token est expiré
    public boolean isExpired() {
        return dateDebut.plusSeconds(dureeDeVie).isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", dateDebut=" + dateDebut +
                ", dureeDeVie=" + dureeDeVie +
                ", utilisateur=" + (utilisateur != null ? utilisateur.getId() : "null") +
                '}';
    }
}
