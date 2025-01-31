package com.example.demo.controller;

import com.example.demo.model.Utilisateur;
import com.example.demo.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    // Mettre à jour un utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> existingUtilisateur = utilisateurService.getUtilisateurById(id);
        if (existingUtilisateur.isPresent()) {
            utilisateur.setId(id);
            Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur.isPresent()) {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Trouver un utilisateur par Gmail
    @GetMapping("/gmail/{gmail}")
    public ResponseEntity<Utilisateur> getUtilisateurByGmail(@PathVariable String gmail) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByGmail(gmail);
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        }
        return ResponseEntity.notFound().build();
    }
}
