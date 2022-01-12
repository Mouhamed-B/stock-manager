package com.set.stockmanager.repository;

import com.set.stockmanager.model.Product;
import com.set.stockmanager.projections.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<ProductView> findAllBy();
    ProductView findProductById(@Param("id") int id);
}