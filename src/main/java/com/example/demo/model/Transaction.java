package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_type", length = 50, nullable = false)
    private String transactionType;

    @Column(name = "montant__cryptomonnaie", precision = 12, scale = 2, nullable = false)
    private BigDecimal montantCryptomonnaie;

    @Column(name = "prix_unitaire__cryptomonnaie", precision = 12, scale = 2, nullable = false)
    private BigDecimal prixUnitaireCryptomonnaie;

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "date_heur", nullable = false)
    private LocalDateTime dateHeur;

    @Column(name = "quantite", nullable = false)
    private double quantite;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    private Cripto cripto;

    @ManyToOne
    @JoinColumn(name = "id_2", nullable = false)
    private Utilisateur utilisateur;

    // Getters et Setters
    public double getQuantite(){
        return this.quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    } 

    public BigDecimal getMontantCryptomonnaie() {
        return montantCryptomonnaie;
    }

    public void setMontantCryptomonnaie(BigDecimal montantCryptomonnaie) {
        this.montantCryptomonnaie = montantCryptomonnaie;
    }

    public BigDecimal getPrixUnitaireCryptomonnaie() {
        return prixUnitaireCryptomonnaie;
    }

    public void setPrixUnitaireCryptomonnaie(BigDecimal prixUnitaireCryptomonnaie) {
        this.prixUnitaireCryptomonnaie = prixUnitaireCryptomonnaie;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDateHeur() {
        return dateHeur;
    }

    public void setDateHeur(LocalDateTime dateHeur) {
        this.dateHeur = dateHeur;
    }

    public Cripto getCripto() {
        return cripto;
    }

    public void setCripto(Cripto cripto) {
        this.cripto = cripto;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

