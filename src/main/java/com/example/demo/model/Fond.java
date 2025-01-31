package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fond")
public class Fond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "montant_transaction", precision = 12, scale = 2, nullable = false)
    private BigDecimal montantTransaction;

    @Column(name = "type_transaction", length = 50, nullable = false)
    private String typeTransaction;

    @Column(name = "etat", nullable = false)
    private int etat;

    @Column(name = "date_transaction", nullable = false)
    private LocalDateTime dateTransaction;

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

    
    public BigDecimal getMontantTransaction() {
        return montantTransaction;
    }

    public void setMontantTransaction(BigDecimal montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
