package com.example.demo.repository;

import com.example.demo.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByToken(String token);
    // Vous pouvez ajouter d'autres méthodes de recherche personnalisées si nécessaire.
}
