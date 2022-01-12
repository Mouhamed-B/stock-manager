package com.set.stockmanager.repository;

import com.set.stockmanager.model.Category;
import com.set.stockmanager.projections.CategoryView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Category getByLabel(@Param("label") String label);
    public List<CategoryView> findAllBy();
    public CategoryView findCategoryById(@Param("id") int id);
}
