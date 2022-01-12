package com.set.stockmanager.projections;

import com.set.stockmanager.model.Category;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.ManyToOne;

public interface ProductView {

    public int getId();
    public String getLabel();
    public String getDescription();
    @Value("#{target.getCategory().getLabel()}")
    public String getCategory();
    public int getPrice();
    public int getStock();
}
