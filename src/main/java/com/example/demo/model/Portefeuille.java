package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "portefeuille")
public class Portefeuille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quatiter", precision = 12, scale = 2)
    private BigDecimal quatiter;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_2", nullable = false)
    private Cripto cripto;

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getQuatiter() {
        return quatiter;
    }

    public void setQuatiter(BigDecimal quatiter) {
        this.quatiter = quatiter;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Cripto getCripto() {
        return cripto;
    }

    public void setCripto(Cripto cripto) {
        this.cripto = cripto;
    }
}
