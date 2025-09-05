package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddCategoryService {
    private CategoryRepositoryInterface repository;

    /**
     * Add category to database
     * @param request
     * Request with a category name to add to the database (DTO Class: RequestAddCategoryDto)
     * @return
     * If category with same name already exists, return category with same name.
     * If a category with the same name does not exist, add a category to the database and return the category with the same name.
     */
    public ResponseCategoryDto addCategory(RequestAddCategoryDto request){
        Optional<Category> categoryForCheckOptional = repository.findByName(request.getName());
        if (categoryForCheckOptional.isPresent()) {
            return new ResponseCategoryDto(categoryForCheckOptional.get().getId(), categoryForCheckOptional.get().getName());
        }
        Category category = new Category(request.getName());
        Category savedCategory = repository.add(category);
        return new ResponseCategoryDto(savedCategory.getId(), savedCategory.getName());
    }
}
