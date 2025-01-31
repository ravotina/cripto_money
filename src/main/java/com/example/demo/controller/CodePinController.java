package com.example.demo.controller;

import com.example.demo.model.CodePin;
import com.example.demo.service.CodePinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/code_pins")
public class CodePinController {

    @Autowired
    private CodePinService codePinService;

    // Récupérer tous les codes PIN
    @GetMapping
    public List<CodePin> getAllCodePins() {
        return codePinService.getAllCodePins();
    }

    // Récupérer un code PIN par ID
    @GetMapping("/{id}")
    public ResponseEntity<CodePin> getCodePinById(@PathVariable int id) {
        Optional<CodePin> codePin = codePinService.getCodePinById(id);
        return codePin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau code PIN
    @PostMapping
    public ResponseEntity<CodePin> createCodePin(@RequestBody CodePin codePin) {
        CodePin createdCodePin = codePinService.createCodePin(codePin);
        return new ResponseEntity<>(createdCodePin, HttpStatus.CREATED);
    }

    // Mettre à jour un code PIN existant
    @PutMapping("/{id}")
    public ResponseEntity<CodePin> updateCodePin(@PathVariable int id, @RequestBody CodePin codePin) {
        Optional<CodePin> existingCodePin = codePinService.getCodePinById(id);
        if (existingCodePin.isPresent()) {
            codePin.setId(id);
            CodePin updatedCodePin = codePinService.updateCodePin(codePin);
            return ResponseEntity.ok(updatedCodePin);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un code PIN par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodePin(@PathVariable int id) {
        Optional<CodePin> codePin = codePinService.getCodePinById(id);
        if (codePin.isPresent()) {
            codePinService.deleteCodePin(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
