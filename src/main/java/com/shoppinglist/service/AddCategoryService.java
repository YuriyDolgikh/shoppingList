package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddCategoryService {
    private CategoryRepositoryInterface repository;

    public ResponseCategoryDto addCategory(RequestAddCategoryDto request){
        Category category = new Category(request.getName());
        Category savedCategory = repository.add(category);
        return new ResponseCategoryDto(savedCategory.getId(), savedCategory.getName());
    }
}
