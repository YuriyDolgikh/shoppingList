package com.shoppinglist.repository;

import com.shoppinglist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByPurchased(boolean purchased);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryName(String categoryName);
}
