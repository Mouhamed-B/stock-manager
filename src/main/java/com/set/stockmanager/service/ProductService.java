package com.set.stockmanager.service;

import com.set.stockmanager.model.Product;
import com.set.stockmanager.projections.ProductView;
import com.set.stockmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    /**
     * Create - Insert a product
     * @param product - the product instance
     * @return - the saved product
     */
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * Read - retrieve all products
     * @return - a list of products
     */
    public List<ProductView> getProducts(){
        return productRepository.findAllBy();
    }

    /**
     * Read - retrieve a single product
     * @param id - product id
     * @return - a product instance
     */
    public ProductView getProduct(final int id){
        return productRepository.findProductById(id);
    }

    /**
     * Delete - delete a product
     * @param id - the product id
     */
    public void deleteProduct(final int id){
        productRepository.deleteById(id);
    }
}
