package com.example.demo.controller;

import com.example.demo.model.ValeurFondReel;
import com.example.demo.service.ValeurFondReelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/valeurs-fond-reel")
public class ValeurFondReelController {

    @Autowired
    private ValeurFondReelService service;

    // Récupérer toutes les valeurs
    @GetMapping
    public List<ValeurFondReel> getAllValeurs() {
        return service.getAllValeurs();
    }

    // Récupérer une valeur par ID
    @GetMapping("/{id}")
    public ResponseEntity<ValeurFondReel> getValeurById(@PathVariable int id) {
        Optional<ValeurFondReel> valeur = service.getValeurById(id);
        return valeur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer une nouvelle valeur
    @PostMapping
    public ResponseEntity<ValeurFondReel> createValeur(@RequestBody ValeurFondReel valeur) {
        ValeurFondReel createdValeur = service.createValeur(valeur);
        return new ResponseEntity<>(createdValeur, HttpStatus.CREATED);
    }

    // Mettre à jour une valeur existante
    @PutMapping("/{id}")
    public ResponseEntity<ValeurFondReel> updateValeur(@PathVariable int id, @RequestBody ValeurFondReel valeur) {
        Optional<ValeurFondReel> existingValeur = service.getValeurById(id);
        if (existingValeur.isPresent()) {
            valeur.setId(id);
            ValeurFondReel updatedValeur = service.updateValeur(valeur);
            return ResponseEntity.ok(updatedValeur);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une valeur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValeur(@PathVariable int id) {
        Optional<ValeurFondReel> valeur = service.getValeurById(id);
        if (valeur.isPresent()) {
            service.deleteValeur(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
