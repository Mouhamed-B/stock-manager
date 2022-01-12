package com.set.stockmanager.controller;

import com.set.stockmanager.model.Category;
import com.set.stockmanager.model.Product;
import com.set.stockmanager.projections.CategoryView;
import com.set.stockmanager.service.CategoryService;
import com.set.stockmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    /**
     * Create - Insert a category
     * @param category - the category instance
     * @return - the saved category
     */
    @PostMapping("/")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    /**
     * Create - Insert a product
     * @param product - the product instance
     * @return - the saved product
     */
    @PostMapping("/{id}/products/")
    public Product addProduct(@PathVariable("id") int id,@RequestBody Product product){
        Optional<Category> c = categoryService.getCategory(id);
        if(c.isPresent()){
            Category category = c.get();
            product.setCategory(category);
            return productService.saveProduct(product);
        }else {
            return null;
        }

    }

    /**
     * Read - Get all products of the category
     * @param id - the category id
     * @return - the product list
     */
    @GetMapping("/{id}/products/")
    public Set<Product> getProducts(@PathVariable("id") int id){
        Optional<Category> c = categoryService.getCategory(id);
        if(c.isPresent()){
            Category category = c.get();
            return category.getProductList();
        }else {
            return null;
        }

    }

    /**
     * Read - retrieve all categories
     * @return - a list of categories
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    /**
     * Read - retrieve a single category
     * @param id - category id
     * @return - a category instance
     */
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") int id){
        Optional<Category> category = categoryService.getCategory(id);
        return category.orElse(null);

    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") int id, @RequestBody Category category){
        Optional<Category> u = categoryService.getCategory(id);
        if (u.isPresent()){
            Category currentCategory = (Category) u.get();

            String label = category.getLabel();
            if (label!=null && !label.equals(currentCategory.getLabel())){
                currentCategory.setLabel(label);
            }

            return categoryService.saveCategory(currentCategory);
        }
        else {
            return null;
        }
    }



    /**
     * Delete - delete a category
     * @param id - the category id
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
    }
}
