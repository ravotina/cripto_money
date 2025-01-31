package com.example.demo.repository;

import com.example.demo.model.ValeurFondReel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ValeurFondReelRepository extends JpaRepository<ValeurFondReel, Integer> {
    List<ValeurFondReel> findByUtilisateurId(int idUtilisateur);
}


