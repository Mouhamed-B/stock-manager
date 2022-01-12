package com.set.stockmanager.service;

import com.set.stockmanager.model.Category;
import com.set.stockmanager.model.User;
import com.set.stockmanager.projections.CategoryView;
import com.set.stockmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Create - Insert a category
     * @param category - the category instance
     * @return - the saved category
     */
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    /**
     * Read - retrieve all categories
     * @return - a list of categories
     */
    public List<Category> getCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    /**
     * Read - retrieve a single category
     * @param id - category id
     * @return - a category instance
     */
    public Optional<Category> getCategory(final int id){
        return categoryRepository.findById(id);
    }
    /**
     * Delete - delete a category
     * @param id - the category id
     */
    public void deleteCategory(final int id){
        categoryRepository.deleteById(id);
    }
}
