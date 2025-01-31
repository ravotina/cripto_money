package com.example.demo.service;

import com.example.demo.model.Token;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    // Récupérer tous les tokens
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    // Récupérer un token par ID
    public Optional<Token> getTokenById(int id) {
        return tokenRepository.findById(id);
    }

    // Créer un nouveau token
    public Token createToken(Token token) {
        return tokenRepository.save(token);
    }

    // Mettre à jour un token existant
    public Token updateToken(Token token) {
        return tokenRepository.save(token);
    }

    // Supprimer un token par ID
    public void deleteToken(int id) {
        tokenRepository.deleteById(id);
    }

    // Trouver un token par valeur
    public Token getTokenByValue(String tokenValue) {
        return tokenRepository.findByToken(tokenValue);
    }
}
