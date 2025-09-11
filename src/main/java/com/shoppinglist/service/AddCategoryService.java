package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddCategoryService {
    private CategoryRepository repository;

    public MainResponse<ResponseCategoryDto> addCategory(RequestAddCategoryDto request){
        Optional<Category> categoryForCheckOptional = repository.findByName(request.getName());
        if (categoryForCheckOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category width name [" + request.getName() + "] already exist", null);
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setProducts(new ArrayList<>());
        Category savedCategory = repository.save(category);
        return new MainResponse<>(HttpStatus.CREATED, "Category width name [" + request.getName() + "] successfully added", ResponseCategoryDto.toDTO(savedCategory));
    }
}
