package com.example.demo.service;

import com.example.demo.model.Portefeuille;
import com.example.demo.repository.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.sound.sampled.Port;

@Service
public class PortefeuilleService {

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    // Récupérer tous les enregistrements
    public List<Portefeuille> getAllPortefeuilles() {
        return portefeuilleRepository.findAll();
    }

    // Récupérer un portefeuille par ID
    public Optional<Portefeuille> getPortefeuilleById(int id) {
        return portefeuilleRepository.findById(id);
    }

    // Créer un nouveau portefeuille
    public Portefeuille createPortefeuille(Portefeuille portefeuille) {
        return portefeuilleRepository.save(portefeuille);
    }

    // Mettre à jour un portefeuille existant
    public Portefeuille updatePortefeuille(Portefeuille portefeuille) {
        return portefeuilleRepository.save(portefeuille);
    }

    // Supprimer un portefeuille par ID
    public void deletePortefeuille(int id) {
        portefeuilleRepository.deleteById(id);
    }

    public List<Portefeuille> getPortefeuilleByUtilisateur(int id) {
        return portefeuilleRepository.findByUtilisateurId(id);
    }

    public Portefeuille getPortefeuilleByUtilisateurAndCripto(int utilisateurId, int criptoId) {
        return portefeuilleRepository.findByUtilisateurIdAndCriptoId(utilisateurId, criptoId);
    }

}
