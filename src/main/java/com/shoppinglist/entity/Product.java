package com.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Double quantity;
    private boolean isPurchased;
    private Category category;

    public Product(String name, Double quantity, boolean isPurchased, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.isPurchased = isPurchased;
        this.category = category;
    }
}
