package com.example.demo.controller;

import com.example.demo.model.Fond;
import com.example.demo.service.FondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fonds")
public class FondController {

    @Autowired
    private FondService fondService;

    // Récupérer tous les fonds
    @GetMapping
    public List<Fond> getAllFonds() {
        return fondService.getAllFonds();
    }

    // Récupérer un fond par ID
    @GetMapping("/{id}")
    public ResponseEntity<Fond> getFondById(@PathVariable int id) {
        Optional<Fond> fond = fondService.getFondById(id);
        return fond.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau fond
    @PostMapping
    public ResponseEntity<Fond> createFond(@RequestBody Fond fond) {
        Fond createdFond = fondService.createFond(fond);
        return new ResponseEntity<>(createdFond, HttpStatus.CREATED);
    }

    // Mettre à jour un fond existant
    @PutMapping("/{id}")
    public ResponseEntity<Fond> updateFond(@PathVariable int id, @RequestBody Fond fond) {
        Optional<Fond> existingFond = fondService.getFondById(id);
        if (existingFond.isPresent()) {
            fond.setId(id);
            Fond updatedFond = fondService.updateFond(fond);
            return ResponseEntity.ok(updatedFond);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un fond par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFond(@PathVariable int id) {
        Optional<Fond> fond = fondService.getFondById(id);
        if (fond.isPresent()) {
            fondService.deleteFond(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
