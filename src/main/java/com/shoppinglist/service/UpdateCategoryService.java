package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCategoryService {
    private CategoryRepositoryInterface repository;

    /**
     * Update category name by id
     * @param request RequestUpdateCategoryDto - request with category id and new category name
     * @return Optional<ResponseCategoryDto> - category with id and name if category was updated or name already exists,
     * empty otherwise if id is not found
     */
    public Optional<ResponseCategoryDto> updateCategory(RequestUpdateCategoryDto request){
        String nameForCheck = request.getName();
        if (!isCategoryNameUnique(nameForCheck)) {
            return Optional.of(new ResponseCategoryDto(repository.findByName(nameForCheck).get().getId(), nameForCheck));
        }
        Optional<Category> updatedCategoryOptional = repository.update(new Category(request.getId(), request.getName()));
        if (updatedCategoryOptional.isPresent()) {
            return Optional.of(new ResponseCategoryDto(updatedCategoryOptional.get().getId(), nameForCheck));
        }
        return Optional.empty();
    }

    /**
     * Check if the category name is unique in the database
     * @param name category name
     * @return boolean - true if the category name is unique, false otherwise
     */
    private boolean isCategoryNameUnique(String name){
        return repository.findByName(name).isEmpty();
    }
}
