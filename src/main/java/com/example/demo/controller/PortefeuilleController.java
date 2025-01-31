package com.example.demo.controller;

import com.example.demo.model.Portefeuille;
import com.example.demo.service.PortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("/api/portefeuilles")
public class PortefeuilleController {

    @Autowired
    private PortefeuilleService portefeuilleService;

    // Récupérer tous les portefeuilles
    @GetMapping
    public List<Portefeuille> getAllPortefeuilles() {
        return portefeuilleService.getAllPortefeuilles();
    }

    // Récupérer les portefeuilles par utilisateur
    // @GetMapping("/user/{id}")
    // public ResponseEntity<List<Portefeuille>> getPortefeuilleByUser(@PathVariable int id) {
    //     List<Portefeuille> portefeuilles = portefeuilleService.getPortefeuilleByUtilisateur(id);
    //     return ResponseEntity.ok(portefeuilles);
    // }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Portefeuille>> getPortefeuilleByUser(@PathVariable int id) {
        List<Portefeuille> portefeuilles = portefeuilleService.getPortefeuilleByUtilisateur(id);
        return portefeuilles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(portefeuilles);
    } 
    // Récupérer un portefeuille par ID
    @GetMapping("/{id}")
    public ResponseEntity<Portefeuille> getPortefeuilleById(@PathVariable int id) {
        Optional<Portefeuille> portefeuille = portefeuilleService.getPortefeuilleById(id);
        return portefeuille.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } 
    // localhost:8080/api/portefeuilles/getPortefeuille/1

    // Créer un nouveau portefeuille
    @PostMapping
    public ResponseEntity<Portefeuille> createPortefeuille(@RequestBody Portefeuille portefeuille) {
        Portefeuille createdPortefeuille = portefeuilleService.createPortefeuille(portefeuille);
        return new ResponseEntity<>(createdPortefeuille, HttpStatus.CREATED);
    }

    // Mettre à jour un portefeuille existant
    @PutMapping("/{id}")
    public ResponseEntity<Portefeuille> updatePortefeuille(@PathVariable int id, @RequestBody Portefeuille portefeuille) {
        Optional<Portefeuille> existingPortefeuille = portefeuilleService.getPortefeuilleById(id);
        if (existingPortefeuille.isPresent()) {
            portefeuille.setId(id);
            Portefeuille updatedPortefeuille = portefeuilleService.updatePortefeuille(portefeuille);
            return ResponseEntity.ok(updatedPortefeuille);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un portefeuille par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortefeuille(@PathVariable int id) {
        Optional<Portefeuille> portefeuille = portefeuilleService.getPortefeuilleById(id);
        if (portefeuille.isPresent()) {
            portefeuilleService.deletePortefeuille(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
