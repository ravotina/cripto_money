package com.example.demo.repository;

import com.example.demo.model.Fond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FondRepository extends JpaRepository<Fond, Integer> {
    // Méthodes personnalisées si nécessaire
    List<Fond> findByUtilisateurId(int id);
}

