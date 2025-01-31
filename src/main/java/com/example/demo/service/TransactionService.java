package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Récupérer toutes les transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Récupérer une transaction par ID
    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    // Créer une nouvelle transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Mettre à jour une transaction existante
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Supprimer une transaction par ID
    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }

    // Récupérer les transactions par utilisateur
    public List<Transaction> getTransactionByUtilisateur(int utilisateurId) {
        return transactionRepository.findByUtilisateurId(utilisateurId);
    }
}
