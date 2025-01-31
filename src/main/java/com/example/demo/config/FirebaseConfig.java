package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        String firebaseConfigPath = System.getenv("firebase.config.path");

        System.out.println("---------------------------------------------------------------firebaseConfigPath : " + firebaseConfigPath);

        if (firebaseConfigPath == null || firebaseConfigPath.isEmpty()) {
            throw new IllegalStateException("FIREBASE_CONFIG_PATH n'est pas défini.");
        }

        // Utilisez le chemin du fichier Firebase dans le container
        FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);  // Cela devrait pointer vers /config/firebase-service-account.json
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://testravo-bb839-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        return firebaseApp;
    }

    @Bean
    public FirebaseDatabase firebaseDatabase(FirebaseApp firebaseApp) {
        return FirebaseDatabase.getInstance(firebaseApp);
    }
}


