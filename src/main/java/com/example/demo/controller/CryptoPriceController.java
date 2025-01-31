package com.example.demo.controller;

import com.example.demo.model.CryptoPrice;
import com.example.demo.service.CryptoPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crypto-prices")
public class CryptoPriceController {

    @Autowired
    private CryptoPriceService cryptoPriceService;

    // Récupérer tous les prix
    @GetMapping
    public List<CryptoPrice> getAllCryptoPrices() {
        return cryptoPriceService.getAllCryptoPrices();
    }

    // Récupérer un prix par ID
    @GetMapping("/{id}")
    public ResponseEntity<CryptoPrice> getCryptoPriceById(@PathVariable int id) {
        Optional<CryptoPrice> cryptoPrice = cryptoPriceService.getCryptoPriceById(id);
        return cryptoPrice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau prix
    @PostMapping
    public ResponseEntity<CryptoPrice> createCryptoPrice(@RequestBody CryptoPrice cryptoPrice) {
        CryptoPrice createdCryptoPrice = cryptoPriceService.createCryptoPrice(cryptoPrice);
        return new ResponseEntity<>(createdCryptoPrice, HttpStatus.CREATED);
    }

    // Mettre à jour un prix existant
    @PutMapping("/{id}")
    public ResponseEntity<CryptoPrice> updateCryptoPrice(@PathVariable int id, @RequestBody CryptoPrice cryptoPrice) {
        Optional<CryptoPrice> existingCryptoPrice = cryptoPriceService.getCryptoPriceById(id);
        if (existingCryptoPrice.isPresent()) {
            cryptoPrice.setId(id);
            CryptoPrice updatedCryptoPrice = cryptoPriceService.updateCryptoPrice(cryptoPrice);
            return ResponseEntity.ok(updatedCryptoPrice);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un prix par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCryptoPrice(@PathVariable int id) {
        Optional<CryptoPrice> cryptoPrice = cryptoPriceService.getCryptoPriceById(id);
        if (cryptoPrice.isPresent()) {
            cryptoPriceService.deleteCryptoPrice(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
