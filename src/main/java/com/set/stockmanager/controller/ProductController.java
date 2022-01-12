package com.set.stockmanager.controller;

import com.set.stockmanager.model.Category;
import com.set.stockmanager.model.Product;
import com.set.stockmanager.projections.ProductView;
import com.set.stockmanager.repository.CategoryRepository;
import com.set.stockmanager.repository.ProductRepository;
import com.set.stockmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
        /**
     * Read - retrieve all products
     * @return - a list of products
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<ProductView> getProducts(){
        return productService.getProducts();
    }

    /**
     * Read - retrieve a single product
     * @param id - product id
     * @return - a product instance
     */
    @GetMapping("/{id}")
    public ProductView getProduct(@PathVariable("id") int id){
        Optional<ProductView> product = Optional.ofNullable(productService.getProduct(id));
        return product.orElse(null);

    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product){
        //Optional<ProductView> p = Optional.ofNullable(productService.getProduct(id));
        Product currentProduct = productRepository.getById(id);
        System.out.println("here");
        String label = product.getLabel();
        if (label!=null && !label.equals(currentProduct.getLabel())){
            currentProduct.setLabel(label);
        }
        String description = product.getDescription();
        if (description!=null && !description.equals(currentProduct.getDescription())){
            currentProduct.setDescription(description);
        }
        int price = product.getPrice();
        if (price>0 && price!=currentProduct.getPrice()){
            currentProduct.setPrice(price);
        }
        int stock = product.getStock();
        if (stock>0 && stock!=currentProduct.getStock()){
            System.out.println("again");
            currentProduct.setStock(stock);
        }
        return productService.saveProduct(currentProduct);
    }



    /**
     * Delete - delete a product
     * @param id - the product id
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }
}
