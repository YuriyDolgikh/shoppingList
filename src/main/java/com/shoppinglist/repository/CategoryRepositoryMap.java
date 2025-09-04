package com.shoppinglist.repository;

import com.shoppinglist.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CategoryRepositoryMap implements CategoryRepositoryInterface {

    private Map<Long, Category> categories;
    private Long idCounter;

    public CategoryRepositoryMap() {
        this.categories = new HashMap<>();
        this.idCounter = 0L;
    }

    @Override
    public Category add(Category category) {
        category.setId(++idCounter);
        categories.put(idCounter, category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> categoryOptional = categories.values().stream()
                .filter(category -> category.getName().equalsIgnoreCase(name))
                .findFirst();
        return categoryOptional;
    }

    @Override
    public Optional<Category> updateNameById(Long id, String name) {
        Category categoryToUpdate = categories.get(id) ;
        if (categoryToUpdate != null) {
            categoryToUpdate.setName(name);
            return Optional.of(categoryToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> deleteById(Long id) {
        return Optional.ofNullable(categories.remove(id));
    }

}
