package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCategoryService {
    private CategoryRepository repository;

    public MainResponse<ResponseCategoryDto> updateCategory(RequestUpdateCategoryDto request) {
        Optional<Category> categoryOptional = repository.findById(request.getId());
        if (categoryOptional.isEmpty()) {
            return new MainResponse<>(HttpStatus.NOT_FOUND, "Category with id [" + request.getId() + "] not found", null
            );
        }
        String nameForCheck = request.getName();
        if (!isCategoryNameUnique(nameForCheck)) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category width name [" + nameForCheck + "] already exist", null);
        }

        Category category = categoryOptional.get();
        category.setName(request.getName());
        Category updatedCategory = repository.save(category);
        return new MainResponse<>(HttpStatus.OK, "Category with id [" + request.getId() + "] successfully updated", ResponseCategoryDto.toDTO(updatedCategory));
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
