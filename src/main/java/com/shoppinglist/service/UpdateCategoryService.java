package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.exception.AlreadyExistException;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCategoryService {
    private CategoryRepository repository;

    public ResponseCategoryDto updateCategory(RequestUpdateCategoryDto request) {
        Optional<Category> categoryOptional = repository.findById(request.getId());
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category with id [" + request.getId() + "] not found");
        }
        String nameForCheck = request.getName();
        if (!isCategoryNameUnique(nameForCheck)) {
            throw new AlreadyExistException("Category width name [" + nameForCheck + "] already exist or equal to the name of the category to update");
        }
        Category category = categoryOptional.get();
        category.setName(request.getName());
        Category updatedCategory = repository.save(category);
        return ResponseCategoryDto.toDTO(updatedCategory);
    }

    /**
     * Check if the category name is unique in the database
     *
     * @param name category name
     * @return boolean - true if the category name is unique, false otherwise
     */
    private boolean isCategoryNameUnique(String name) {
        return repository.findByName(name).isEmpty();
    }
}
