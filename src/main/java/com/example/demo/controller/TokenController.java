package com.example.demo.controller;

import com.example.demo.model.Token;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    // Récupérer tous les tokens
    @GetMapping
    public List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }

    // Récupérer un token par ID
    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable int id) {
        Optional<Token> token = tokenService.getTokenById(id);
        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau token
    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        Token createdToken = tokenService.createToken(token);
        return new ResponseEntity<>(createdToken, HttpStatus.CREATED);
    }

    // Mettre à jour un token existant
    @PutMapping("/{id}")
    public ResponseEntity<Token> updateToken(@PathVariable int id, @RequestBody Token token) {
        Optional<Token> existingToken = tokenService.getTokenById(id);
        if (existingToken.isPresent()) {
            token.setId(id);
            Token updatedToken = tokenService.updateToken(token);
            return ResponseEntity.ok(updatedToken);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un token
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable int id) {
        Optional<Token> token = tokenService.getTokenById(id);
        if (token.isPresent()) {
            tokenService.deleteToken(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Trouver un token par valeur
    @GetMapping("/value/{token}")
    public ResponseEntity<Token> getTokenByValue(@PathVariable String token) {
        Token foundToken = tokenService.getTokenByValue(token);
        if (foundToken != null) {
            return ResponseEntity.ok(foundToken);
        }
        return ResponseEntity.notFound().build();
    }
}
