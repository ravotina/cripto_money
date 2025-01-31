package com.example.demo.repository;

import com.example.demo.model.CodePin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodePinRepository extends JpaRepository<CodePin, Integer> {
    // Méthodes personnalisées si nécessaire
}
