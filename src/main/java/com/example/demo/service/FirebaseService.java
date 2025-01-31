package com.example.demo.service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import java.util.Map;
import java.util.HashMap;

@Service
public class FirebaseService {

    private final DatabaseReference databaseReference;

    public FirebaseService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("users");
    }

    public void saveUser(String userId, String name, String email) {
        Map<String, Object> user = Map.of(
                "name", name,
                "email", email
        );
        databaseReference.child(userId).setValueAsync(user);
    }

    // Récupérer tous les utilisateurs
    public CompletableFuture<List<User>> getAllUsers() {
        CompletableFuture<List<User>> future = new CompletableFuture<>();
        List<User> users = new ArrayList<>();

        // Récupération des données depuis Firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        users.add(user);
                    }
                }
                // Une fois les données récupérées, compléter le futur avec la liste des utilisateurs
                future.complete(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérer les erreurs de récupération
                System.err.println("Erreur lors de la récupération des utilisateurs : " + databaseError.getMessage());
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }


    public CompletableFuture<User> getUserById(String userId) {
        CompletableFuture<User> future = new CompletableFuture<>();
    
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    future.complete(user);
                } else {
                    future.completeExceptionally(new Exception("Utilisateur non trouvé"));
                }
            }
    
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Erreur lors de la récupération de l'utilisateur : " + databaseError.getMessage());
                future.completeExceptionally(databaseError.toException());
            }
        });
    
        return future;
    }
    
    
    
}
