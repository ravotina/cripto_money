package com.example.demo.repository;

import com.example.demo.model.CryptoPrice;
import com.example.demo.model.Cripto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Integer> {
    Optional<CryptoPrice> findTopByCriptoOrderByDatyDesc(Cripto cripto);
}
