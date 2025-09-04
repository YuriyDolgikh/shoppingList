package com.shoppinglist.service;

import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteCategoryService {
    private CategoryRepositoryInterface repository;

    public String deleteCategoryById(Long id){
        Optional<Category> category = repository.deleteById(id);
        if (category.isPresent()) {
            return "Category with ID = " + id + " Name = " + category.get().getName() + " deleted";
        }
        return "Category with ID = " + id + " not found";
    }
}
