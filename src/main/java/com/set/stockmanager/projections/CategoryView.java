package com.set.stockmanager.projections;

import com.set.stockmanager.model.Product;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoryView {

    String getLabel();
    @Value("#{target.getProductListCount()}")
    int getProductListCount();
}
