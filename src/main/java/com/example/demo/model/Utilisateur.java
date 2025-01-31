package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nom", length = 200, nullable = false)
    private String nom;

    @Column(name = "Prenom", length = 200)
    private String prenom;

    @Column(name = "Date_de_Naissance", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;

    @Column(name = "Gmail", length = 200, nullable = false, unique = true)
    private String gmail;

    @Column(name = "mot_de_pass", length = 100, nullable = false)
    private String motDePasse;

    @Column(name = "etat", length = 1, nullable = false)
    private String etat;

    @Column(name = "role", length = 50, nullable = false)
    private String role;


    @Column(name = "image", length = 200)
    private String image;

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
