package com.example.demo.controller;

import com.example.demo.model.Tentative;
import com.example.demo.service.TentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tentatives")
public class TentativeController {

    @Autowired
    private TentativeService tentativeService;

    // Récupérer toutes les tentatives
    @GetMapping
    public List<Tentative> getAllTentatives() {
        return tentativeService.getAllTentatives();
    }

    // Récupérer une tentative par ID
    @GetMapping("/{id}")
    public ResponseEntity<Tentative> getTentativeById(@PathVariable int id) {
        Optional<Tentative> tentative = tentativeService.getTentativeById(id);
        return tentative.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer une nouvelle tentative
    @PostMapping
    public ResponseEntity<Tentative> createTentative(@RequestBody Tentative tentative) {
        Tentative createdTentative = tentativeService.createTentative(tentative);
        return new ResponseEntity<>(createdTentative, HttpStatus.CREATED);
    }

    // Mettre à jour une tentative existante
    @PutMapping("/{id}")
    public ResponseEntity<Tentative> updateTentative(@PathVariable int id, @RequestBody Tentative tentative) {
        Optional<Tentative> existingTentative = tentativeService.getTentativeById(id);
        if (existingTentative.isPresent()) {
            tentative.setId(id);
            Tentative updatedTentative = tentativeService.updateTentative(tentative);
            return ResponseEntity.ok(updatedTentative);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une tentative
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTentative(@PathVariable int id) {
        Optional<Tentative> tentative = tentativeService.getTentativeById(id);
        if (tentative.isPresent()) {
            tentativeService.deleteTentative(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
