package com.example.demo.service;

import com.example.demo.model.ValeurFondReel;
import com.example.demo.repository.ValeurFondReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValeurFondReelService {

    @Autowired
    private ValeurFondReelRepository repository;

    // Récupérer toutes les valeurs
    public List<ValeurFondReel> getAllValeurs() {
        return repository.findAll();
    }

    // Récupérer une valeur par ID
    public Optional<ValeurFondReel> getValeurById(int id) {
        return repository.findById(id);
    }

    // Créer une nouvelle valeur
    public ValeurFondReel createValeur(ValeurFondReel valeur) {
        return repository.save(valeur);
    }

    // Mettre à jour une valeur existante
    public ValeurFondReel updateValeur(ValeurFondReel valeur) {
        return repository.save(valeur);
    }

    // Supprimer une valeur par ID
    public void deleteValeur(int id) {
        repository.deleteById(id);
    }

    public List<ValeurFondReel> getValeurFondReelByUtilisateur(int idUtilisateur) {
        return repository.findByUtilisateurId(idUtilisateur);
    }
    
}

