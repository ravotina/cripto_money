package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "crypto_prices")
public class CryptoPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "valeur", precision = 12, scale = 2, nullable = false)
    private BigDecimal valeur;

    @Column(name = "daty", nullable = false)
    private LocalDateTime daty;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    private Cripto cripto;

    @Column(name="unite", nullable= false)
    private String unite;
    // Getters et Setters
    
    public String getUnite(){
        return unite;
    }

    public void setUnite(String unite){
        this.unite = unite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getDaty() {
        return daty;
    }

    public void setDaty(LocalDateTime daty) {
        this.daty = daty;
    }

    public Cripto getCripto() {
        return cripto;
    }

    public void setCripto(Cripto cripto) {
        this.cripto = cripto;
    }
}
