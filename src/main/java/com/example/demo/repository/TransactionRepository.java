package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Méthodes personnalisées si nécessaire
    // Méthodes personnalisées si nécessaire
    List<Transaction> findByUtilisateurId(int utilisateurId);
}
