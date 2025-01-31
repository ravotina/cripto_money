package com.example.demo.service;
 import java.util.Random;

import com.example.demo.model.Cripto;
import com.example.demo.model.CryptoPrice;
import com.example.demo.repository.CriptoRepository;
import com.example.demo.repository.CryptoPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.Optional;
import java.util.Random;
import java.time.LocalDateTime;

// import org.springframework.scheduling.annotation;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import java.math.BigDecimal;
import org.springframework.scheduling.annotation.EnableScheduling;

@Service
@Component
public class CryptoPriceService {

    @Autowired
    private CryptoPriceRepository cryptoPriceRepository;

    @Autowired
    private CriptoRepository criptoRepository;
    
    private boolean isRunning = false; 

    // Récupérer tous les enregistrements
    public List<CryptoPrice> getAllCryptoPrices() {
        return cryptoPriceRepository.findAll();
    }

    // Récupérer un prix par ID
    public Optional<CryptoPrice> getCryptoPriceById(int id) {
        return cryptoPriceRepository.findById(id);
    }

    // Créer un nouveau prix
    public CryptoPrice createCryptoPrice(CryptoPrice crypto) {
        // Vérifie et nettoie la table si nécessaire
        verifierEtNettoyerTable();
        return cryptoPriceRepository.save(crypto);
    }

    private void verifierEtNettoyerTable() {
        long count = cryptoPriceRepository.count();
        if (count >= 10) {
            // Effacer toutes les données
            cryptoPriceRepository.deleteAll();
            System.out.println("La table CryptoPrice a été nettoyée car elle contenait 1000 entrées.");
        }
    }

    // Mettre à jour un prix existant
    public CryptoPrice updateCryptoPrice(CryptoPrice cryptoPrice) {
        return cryptoPriceRepository.save(cryptoPrice);
    }

    // Supprimer un prix par ID
    public void deleteCryptoPrice(int id) {
        cryptoPriceRepository.deleteById(id);
    }

    public CryptoPrice genererCrypto(double pourcentage, double valeur) {
        Random random = new Random();

        // Générer un pourcentage aléatoire entre -pourcentage et +pourcentage
        double facteurAleatoire = (random.nextDouble() * 2 - 1) * pourcentage / 100;

        // Calculer la nouvelle valeur avec BigDecimal
        BigDecimal valeurInitiale = BigDecimal.valueOf(valeur);
        BigDecimal facteur = BigDecimal.valueOf(facteurAleatoire);

        // valeurInitiale * facteur = variation
        BigDecimal variation = valeurInitiale.multiply(facteur);

        // valeurInitiale + variation = nouveau prix
        BigDecimal nouveauPrix = valeurInitiale.add(variation);

        // Créer l'objet CryptoPrice avec la nouvelle valeur
        CryptoPrice crypto = new CryptoPrice();
        crypto.setValeur(nouveauPrix);
        crypto.setUnite("dollars");
        // crypto.setDaty(date);
        return crypto;
    }  

    @Scheduled(fixedRate = 10000, initialDelay = 10000)  
    public void generateAndInsertCryptoPrice() {
        // Vérifier si la table Cripto contient des données
        long count = criptoRepository.count();  // Remplace par un repository de Cripto si nécessaire
        if (count == 0) {
            System.out.println("Aucune donnée dans la table Cripto. Génération annulée.");
            return;
        }

        double valeur = 2000000;  // Prix initial
        double pourcentage = 0.06;
        LocalDateTime daty = LocalDateTime.now();

        CryptoPrice crypto1 = this.genererCrypto(pourcentage, valeur);
        CryptoPrice crypto2 = this.genererCrypto(pourcentage, valeur);
        CryptoPrice crypto3 = this.genererCrypto(pourcentage, valeur);

        crypto1.setDaty(daty);
        crypto2.setDaty(daty);
        crypto3.setDaty(daty);

        Cripto cripto1 = new Cripto();
        Cripto cripto2 = new Cripto();
        Cripto cripto3 = new Cripto();
        cripto1.setId(1);
        cripto2.setId(2);
        cripto3.setId(3);

        crypto1.setCripto(cripto1);
        crypto2.setCripto(cripto2);
        crypto3.setCripto(cripto3);

        this.createCryptoPrice(crypto1);
        this.createCryptoPrice(crypto2);
        this.createCryptoPrice(crypto3);

        System.out.println("CryptoPrice inséré : " + crypto1);
    }


    public CryptoPrice getLastCryptoPriceByCripto(Cripto cripto) {
        return cryptoPriceRepository.findTopByCriptoOrderByDatyDesc(cripto)
                .orElseThrow(() -> new RuntimeException("Aucun CryptoPrice trouvé pour ce Cripto"));
    }
    
    
}