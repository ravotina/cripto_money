package com.example.demo.service;

public class User {

    private String name;
    private String email;

    // Constructeur par défaut
    public User() {}

    // Constructeur avec paramètres
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
