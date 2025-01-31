package com.example.demo.service;

import com.example.demo.model.Cripto;
import com.example.demo.repository.CriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriptoService {

    @Autowired
    private CriptoRepository criptoRepository;

    // Récupérer tous les enregistrements
    public List<Cripto> getAllCriptos() {
        return criptoRepository.findAll();
    }

    // Récupérer un enregistrement par ID
    public Optional<Cripto> getCriptoById(int id) {
        return criptoRepository.findById(id);
    }

    // Créer un nouvel enregistrement
    public Cripto createCripto(Cripto cripto) {
        return criptoRepository.save(cripto);
    }

    // Mettre à jour un enregistrement existant
    public Cripto updateCripto(Cripto cripto) {
        return criptoRepository.save(cripto);
    }

    // Supprimer un enregistrement par ID
    public void deleteCripto(int id) {
        criptoRepository.deleteById(id);
    }
}
