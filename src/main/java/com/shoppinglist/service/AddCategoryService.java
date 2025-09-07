package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddCategoryService {
    private CategoryRepositoryInterface repository;

    public MainResponse<ResponseCategoryDto> addCategory(RequestAddCategoryDto request){
        Optional<Category> categoryForCheckOptional = repository.findByName(request.getName());
        if (categoryForCheckOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category width name [" + request.getName() + "] already exist", null);
        }
        Category category = new Category(request.getName());
        Category savedCategory = repository.add(category);
        return new MainResponse<>(HttpStatus.CREATED, "Category width name [" + request.getName() + "] successfully added", ResponseCategoryDto.toDTO(savedCategory));
    }
}
