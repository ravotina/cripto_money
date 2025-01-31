package com.example.demo.controller;

import com.example.demo.model.CryptoPrice;
import com.example.demo.model.Fond;
import com.example.demo.model.Portefeuille;
import com.example.demo.model.Transaction;
import com.example.demo.model.Utilisateur;
import com.example.demo.model.ValeurFondReel;
import com.example.demo.repository.CriptoRepository;
import com.example.demo.service.CryptoPriceService;
import com.example.demo.service.FondService;
import com.example.demo.service.PortefeuilleService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.ValeurFondReelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CryptoPriceService criptoPriceService;

    @Autowired
    private PortefeuilleService portefeuilleService;

    @Autowired
    private FondService fondService;

    @Autowired
    private ValeurFondReelService fondReelService;

    
    // Récupérer toutes les transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Récupérer une transaction par ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/echange1")
    public ResponseEntity<String> echangeCrypto1(@RequestBody Transaction transaction) {
        try {
            // Simuler une erreur volontairement pour voir l'exception
            if (transaction == null) {
                throw new IllegalArgumentException("L'objet Transaction est null !");
            }

            System.out.println("Transaction Type: " + transaction.getTransactionType());
            System.out.println("Prix Unitaire: " + transaction.getPrixUnitaireCryptomonnaie());
            System.out.println("Quantité: " + transaction.getQuantite());

            // Calcul du total
            BigDecimal total = transaction.getPrixUnitaireCryptomonnaie()
                    .multiply(BigDecimal.valueOf(transaction.getQuantite()));

            System.out.println("Total: " + total);

            return ResponseEntity.ok("Transaction traitée avec succès");

        } catch (Exception e) {
            e.printStackTrace(); // Log de l'erreur côté serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne : " + e.getMessage());
        }
    }

    // {
    //     "transactionType": "achat",
    //     "montantCryptomonnaie": 2.5,
    //     "prixUnitaireCryptomonnaie": 32000.75,
    //     "total": 80001.88,
    //     "dateHeur": "2024-01-30T15:30:00",
    //     "quantite": 1,
    //     "cripto": {
    //       "id": 1
    //     },
    //     "utilisateur": {
    //       "id": 5
    //     },
    //     "cryptoPrice": {
    //       "id": 10
    //     }
    //   }
    @GetMapping("/historique_transaction_vola/{idUtilisateur}")
    public ResponseEntity<?> getHistoriqueTransactionVola(@PathVariable int idUtilisateur){
        List<Fond> fonds = fondService.getFondByUtilisateur(idUtilisateur);
        if (fonds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucune fond trouvée pour l'utilisateur avec l'ID : " + idUtilisateur);
        }
        return ResponseEntity.ok(fonds);
    }

    @GetMapping("/historique_transaction_portefeuille/{idUtilisateur}")
    public ResponseEntity<?> getHistoriquePortefeuilleByUser(@PathVariable int idUtilisateur) {
        List<Transaction> transactions = transactionService.getTransactionByUtilisateur(idUtilisateur);
    
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucune transaction trouvée pour l'utilisateur avec l'ID : " + idUtilisateur);
        }
    
        return ResponseEntity.ok(transactions);
    }
    

    @GetMapping("/validate_transaction/{idFond}")
    public ResponseEntity<?> validate_transaction(@PathVariable int idFond) {
    try {
        // Vérifier si le fond existe
        Fond fond_transation = fondService.getFondById(idFond)
                .orElseThrow(() -> new RuntimeException("Fond non trouvé avec l'ID: " + idFond));

        // Mettre à jour l'état du fond
        fond_transation.setEtat(1); // état validé
        fondService.updateFond(fond_transation);

        // Récupérer la valeur du fond réel de l'utilisateur
        Utilisateur utilisateur = fond_transation.getUtilisateur();
        ValeurFondReel valeur_reel = fondReelService.getValeurFondReelByUtilisateur(utilisateur.getId()).get(0);

        if (valeur_reel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Valeur du fond réel introuvable pour l'utilisateur ID: " + utilisateur.getId());
        }

        BigDecimal valeur_fond_actuel = valeur_reel.getValeur();

        // Vérifier et ajuster la valeur du fond réel
        if ("retrait".equalsIgnoreCase(fond_transation.getTypeTransaction())) {
            if (fond_transation.getMontantTransaction().compareTo(valeur_fond_actuel) > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Fonds insuffisants pour effectuer ce retrait.");
            }
            valeur_fond_actuel = valeur_fond_actuel.subtract(fond_transation.getMontantTransaction());
        } else if ("depot".equalsIgnoreCase(fond_transation.getTypeTransaction())) {
            valeur_fond_actuel = valeur_fond_actuel.add(fond_transation.getMontantTransaction());
        }

        // Mettre à jour la valeur réelle du fond
        valeur_reel.setValeur(valeur_fond_actuel);
        fondReelService.updateValeur(valeur_reel);

        return ResponseEntity.ok(fond_transation);

    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Une erreur est survenue: " + e.getMessage());
    }
}


@PostMapping("/transaction_vola")
public ResponseEntity<?> transaction(@RequestBody Fond fond) {
    try {
        // Vérification des valeurs obligatoires
        if (fond.getUtilisateur() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Utilisateur non spécifié pour cette transaction.");
        }
        if (fond.getMontantTransaction() == null || fond.getMontantTransaction().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le montant de la transaction doit être positif.");
        }
        if (fond.getTypeTransaction() == null || fond.getTypeTransaction().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Type de transaction (depot/retrait) requis.");
        }

        // Récupération de l'utilisateur et de son fond réel
        Utilisateur utilisateur = fond.getUtilisateur();
        List<ValeurFondReel> valeur_fond_list = fondReelService.getValeurFondReelByUtilisateur(utilisateur.getId());

        if (valeur_fond_list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Valeur du fond réel introuvable pour l'utilisateur ID: " + utilisateur.getId());
        }

        ValeurFondReel valeur_fond_actuel = valeur_fond_list.get(0);

        // Gestion des retraits et dépôts
        if ("retrait".equalsIgnoreCase(fond.getTypeTransaction())) {
            if (fond.getMontantTransaction().compareTo(valeur_fond_actuel.getValeur()) > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Fonds insuffisants pour effectuer ce retrait.");
            }
            valeur_fond_actuel.setValeur(valeur_fond_actuel.getValeur().subtract(fond.getMontantTransaction()));
        } else if ("depot".equalsIgnoreCase(fond.getTypeTransaction())) {
            valeur_fond_actuel.setValeur(valeur_fond_actuel.getValeur().add(fond.getMontantTransaction()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Type de transaction invalide. Veuillez utiliser 'depot' ou 'retrait'.");
        }

        // Sauvegarde des changements
        fond.setEtat(0);
        fondService.createFond(fond);
        fondReelService.updateValeur(valeur_fond_actuel);

        return ResponseEntity.ok(fond);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Une erreur est survenue: " + e.getMessage());
    }
}

      
    @PostMapping("/echange")
    public ResponseEntity<Transaction> echangeCrypto(@RequestBody Transaction transaction){
        Transaction transaction_updated = new Transaction();
        int idCripto = transaction.getCripto().getId();
        System.out.println("Transaction cripto" + idCripto);
        // int idCrypto_prices = transaction.getCryptoPrice().getId();
        // System.out.print("price : " + idCrypto_prices);
        LocalDateTime date = transaction.getDateHeur();
        System.out.print("date : " + date);
        // CryptoPrice cryptoPrice = criptoPriceService.getLastCryptoPriceByCripto(transaction.getCripto());
        // System.out.print("prix : " + cryptoPrice);
        double quantite = transaction.getQuantite();
        System.out.print("quantite : " + quantite);
        BigDecimal quantiteBigDecimal = BigDecimal.valueOf(quantite);
        System.out.print("qttBig : " + quantiteBigDecimal);

        Utilisateur utilisateur = transaction.getUtilisateur();
        System.out.print("user : " + utilisateur);
        String type_transaction = transaction.getTransactionType();
        System.out.print("type_transaction : " + type_transaction);
        // System.out.print("id_2")
        transactionService.createTransaction(transaction);
        // Portefeuille portefeuille = portefeuilleService.getPortefeuilleByUtilisateur(utilisateur.getId());
        Portefeuille portefeuille = portefeuilleService.getPortefeuilleByUtilisateurAndCripto(utilisateur.getId(), idCripto);
        System.out.print("portefeuille : " + portefeuille);
        ValeurFondReel fondReel = fondReelService.getValeurFondReelByUtilisateur(utilisateur.getId()).get(0);
        System.out.print("fondReel : " + fondReel);
        // Fond fond = fondService.getFondByUtilisateur(utilisateur.getId());
        Fond fond = new Fond();
        if(type_transaction.equalsIgnoreCase("achat")){
            if (portefeuille.getQuatiter().compareTo(quantiteBigDecimal) >= 0) {
                portefeuille.setQuatiter(quantiteBigDecimal.add(portefeuille.getQuatiter()));
                portefeuilleService.updatePortefeuille(portefeuille);
                fond.setTypeTransaction("achat");
                // BigDecimal vola_retire = fond.getFond().subtract((cryptoPrice.getValeur().multiply(quantiteBigDecimal)));
                BigDecimal vola_reste = fondReel.getValeur().subtract((transaction.getMontantCryptomonnaie().multiply(quantiteBigDecimal)));
                fondReel.setValeur(vola_reste);
                // fond.setFond(vola_retire);
                fond.setMontantTransaction(transaction.getMontantCryptomonnaie().multiply(quantiteBigDecimal));
                fond.setDateTransaction(date);
                fond.setUtilisateur(utilisateur);
                fond.setEtat(1); // etat validé
                fondService.createFond(fond);
                fondReelService.updateValeur(fondReel);
                // fondService.updateFond(fond);
            } 
        } else if(type_transaction.equalsIgnoreCase("vente")){
            if (portefeuille.getQuatiter().compareTo(quantiteBigDecimal) >= 0) {
                portefeuille.setQuatiter(portefeuille.getQuatiter().subtract(quantiteBigDecimal));
                portefeuilleService.updatePortefeuille(portefeuille);
                // BigDecimal vola_depose = fond.getFond().add(cryptoPrice.getValeur());
                BigDecimal vola_azo = fondReel.getValeur().add((transaction.getMontantCryptomonnaie().multiply(quantiteBigDecimal)));
                fondReel.setValeur(vola_azo);
                fond.setTypeTransaction("vente");
                fond.setMontantTransaction(transaction.getMontantCryptomonnaie().multiply(quantiteBigDecimal)); 
                fond.setDateTransaction(date);
                fond.setEtat(1); // etat validé
                fond.setUtilisateur(utilisateur);
                fondService.createFond(fond);
                fondReelService.updateValeur(fondReel);
                // fondService.updateFond(fond);
            }
        }
        return ResponseEntity.ok(transaction);
    } 

    // Créer une nouvelle transaction
    @PostMapping("/insert")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    // Mettre à jour une transaction existante
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionService.getTransactionById(id);
        if (existingTransaction.isPresent()) {
            transaction.setId(id);
            Transaction updatedTransaction = transactionService.updateTransaction(transaction);
            return ResponseEntity.ok(updatedTransaction);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une transaction par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        if (transaction.isPresent()) {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
