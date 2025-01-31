package com.example.demo.repository;

import com.example.demo.model.Cripto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriptoRepository extends JpaRepository<Cripto, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
