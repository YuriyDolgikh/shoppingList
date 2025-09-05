package com.shoppinglist.repository;

import com.shoppinglist.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryInterface {
    Category add(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    Optional<Category> update(Category category);
    Optional<Category> deleteById(Long id);
}
