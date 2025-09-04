package com.shoppinglist.repository;

import com.shoppinglist.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {
    Product add(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findByIsPurchased(boolean isPurchased);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryName(String categoryName);
    Optional<Product> updateNameById(Long id, String name);
    Optional<Product> updateQuantityById(Long id, Double quantity);
    Optional<Product> updateIsPurchasedById(Long id, boolean isPurchased);
    Optional<Product> deleteById(Long id);
}
