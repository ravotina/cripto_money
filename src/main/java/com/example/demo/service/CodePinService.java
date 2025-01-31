package com.example.demo.service;

import com.example.demo.model.CodePin;
import com.example.demo.repository.CodePinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodePinService {

    @Autowired
    private CodePinRepository codePinRepository;

    // Récupérer tous les codes PIN
    public List<CodePin> getAllCodePins() {
        return codePinRepository.findAll();
    }

    // Récupérer un code PIN par ID
    public Optional<CodePin> getCodePinById(int id) {
        return codePinRepository.findById(id);
    }

    // Créer un nouveau code PIN
    public CodePin createCodePin(CodePin codePin) {
        return codePinRepository.save(codePin);
    }

    // Mettre à jour un code PIN existant
    public CodePin updateCodePin(CodePin codePin) {
        return codePinRepository.save(codePin);
    }

    // Supprimer un code PIN par ID
    public void deleteCodePin(int id) {
        codePinRepository.deleteById(id);
    }
}
