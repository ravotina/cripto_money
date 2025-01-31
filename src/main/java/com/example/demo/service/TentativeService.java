package com.example.demo.service;

import com.example.demo.model.Tentative;
import com.example.demo.repository.TentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TentativeService {

    @Autowired
    private TentativeRepository tentativeRepository;

    // Récupérer toutes les tentatives
    public List<Tentative> getAllTentatives() {
        return tentativeRepository.findAll();
    }

    // Récupérer une tentative par ID
    public Optional<Tentative> getTentativeById(int id) {
        return tentativeRepository.findById(id);
    }

    // Créer une nouvelle tentative
    public Tentative createTentative(Tentative tentative) {
        return tentativeRepository.save(tentative);
    }

    // Mettre à jour une tentative existante
    public Tentative updateTentative(Tentative tentative) {
        return tentativeRepository.save(tentative);
    }

    // Supprimer une tentative par ID
    public void deleteTentative(int id) {
        tentativeRepository.deleteById(id);
    }
}
