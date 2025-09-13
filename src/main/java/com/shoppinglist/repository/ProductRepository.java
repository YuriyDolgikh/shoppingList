package com.shoppinglist.repository;

import com.shoppinglist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> deleteProductById(Long id);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPurchased(boolean purchased);
    List<Product> findByCategoryName(String categoryName);
}
