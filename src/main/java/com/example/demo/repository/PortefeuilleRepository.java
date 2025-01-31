package com.example.demo.repository;

import com.example.demo.model.Portefeuille;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Integer> {
    // Recherche des portefeuilles d'un utilisateur par son ID
    List<Portefeuille> findByUtilisateurId(int id);
    // Recherche d'un portefeuille par l'ID de l'utilisateur et l'ID du Cripto
    Portefeuille findByUtilisateurIdAndCriptoId(int utilisateurId, int criptoId);
} 

