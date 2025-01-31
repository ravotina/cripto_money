package com.example.demo.controller;

import com.example.demo.service.FirebaseService;
import com.example.demo.service.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    private final FirebaseService firebaseService;

    public FirebaseController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String userId, @RequestParam String name, @RequestParam String email) {
        firebaseService.saveUser(userId, name, email);
        return "Utilisateur ajouté avec succès !";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        // Attendre la récupération des utilisateurs
        return firebaseService.getAllUsers().join();
    }


    @GetMapping("/getUser/{userId}")
    public User getUserById(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return firebaseService.getUserById(userId).join();
    }

}
