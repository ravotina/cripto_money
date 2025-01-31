package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_pin")
public class CodePin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code_Pin", nullable = false)
    private int codePin;

    @Column(name = "date_debu", nullable = false)
    private LocalDateTime dateDebu;

    @Column(name = "dure_de_vie", nullable = false)
    private int dureDeVie;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    private Token token;

    @ManyToOne
    @JoinColumn(name = "id_2", nullable = false)
    private Utilisateur utilisateur;

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodePin() {
        return codePin;
    }

    public void setCodePin(int codePin) {
        this.codePin = codePin;
    }

    public LocalDateTime getDateDebu() {
        return dateDebu;
    }

    public void setDateDebu(LocalDateTime dateDebu) {
        this.dateDebu = dateDebu;
    }

    public int getDureDeVie() {
        return dureDeVie;
    }

    public void setDureDeVie(int dureDeVie) {
        this.dureDeVie = dureDeVie;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
