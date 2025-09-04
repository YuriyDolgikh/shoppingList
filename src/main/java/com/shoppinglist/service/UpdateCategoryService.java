package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
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

    public String updateNameCategoryById(RequestUpdateCategoryDto request){
        String nameForCheck = request.getName();
        if (!isCategoryNameUnique(nameForCheck)) {
            return "Category with name = " + nameForCheck + " already exists";
        }
        Optional<Category> updatedCategoryOptional = repository.updateNameById(request.getId(), request.getName());
        if (updatedCategoryOptional.isPresent()) {
            return updatedCategoryOptional.get().toString();
        }
        return "Category with ID = " + request.getId() + " not found";
    }

    private boolean isCategoryNameUnique(String name){
        return repository.findByName(name).isEmpty();
    }
}
