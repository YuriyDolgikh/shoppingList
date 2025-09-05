package com.shoppinglist.repository;

import com.shoppinglist.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {
    Product add(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findByPurchased(boolean purchased);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryName(String categoryName);
    Optional<Product> update(Product product);
    Optional<Product> deleteById(Long id);
}
