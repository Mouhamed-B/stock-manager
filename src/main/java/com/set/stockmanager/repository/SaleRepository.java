package com.set.stockmanager.repository;

import com.set.stockmanager.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleRepository extends JpaRepository<Sale, Integer> {
}