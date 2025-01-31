package com.example.demo.service;

import com.example.demo.model.Fond;
import com.example.demo.repository.FondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FondService {

    @Autowired
    private FondRepository fondRepository;

    // Récupérer tous les enregistrements
    public List<Fond> getAllFonds() {
        return fondRepository.findAll();
    }

    // Récupérer un fond par ID
    public Optional<Fond> getFondById(int id) {
        return fondRepository.findById(id);
    }

    // Créer un nouveau fond
    public Fond createFond(Fond fond) {
        return fondRepository.save(fond);
    }

    // Mettre à jour un fond existant
    public Fond updateFond(Fond fond) {
        return fondRepository.save(fond);
    }

    // Supprimer un fond par ID
    public void deleteFond(int id) {
        fondRepository.deleteById(id);
    }

    public List<Fond> getFondByUtilisateur(int idUser){
        return fondRepository.findByUtilisateurId(idUser);
    }
}
