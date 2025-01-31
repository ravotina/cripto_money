package com.example.demo.repository;

import com.example.demo.model.Tentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TentativeRepository extends JpaRepository<Tentative, Integer> {
    // Vous pouvez ajouter d'autres méthodes personnalisées si nécessaire.
}
