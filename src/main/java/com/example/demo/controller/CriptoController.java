package com.example.demo.controller;

import com.example.demo.model.Cripto;
import com.example.demo.service.CriptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/criptos")
public class CriptoController {

    @Autowired
    private CriptoService criptoService;

    // Récupérer tous les enregistrements
    @GetMapping
    public List<Cripto> getAllCriptos() {
        return criptoService.getAllCriptos();
    }

    // Récupérer un enregistrement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Cripto> getCriptoById(@PathVariable int id) {
        Optional<Cripto> cripto = criptoService.getCriptoById(id);
        return cripto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouvel enregistrement
    @PostMapping
    public ResponseEntity<Cripto> createCripto(@RequestBody Cripto cripto) {
        Cripto createdCripto = criptoService.createCripto(cripto);
        return new ResponseEntity<>(createdCripto, HttpStatus.CREATED);
    }

    // Mettre à jour un enregistrement existant
    @PutMapping("/{id}")
    public ResponseEntity<Cripto> updateCripto(@PathVariable int id, @RequestBody Cripto cripto) {
        Optional<Cripto> existingCripto = criptoService.getCriptoById(id);
        if (existingCripto.isPresent()) {
            cripto.setId(id);
            Cripto updatedCripto = criptoService.updateCripto(cripto);
            return ResponseEntity.ok(updatedCripto);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un enregistrement par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCripto(@PathVariable int id) {
        Optional<Cripto> cripto = criptoService.getCriptoById(id);
        if (cripto.isPresent()) {
            criptoService.deleteCripto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
