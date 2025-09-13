package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.exception.AlreadyExistException;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddCategoryService {
    private CategoryRepository repository;

    public ResponseCategoryDto addCategory(RequestAddCategoryDto request){
        Optional<Category> categoryForCheckOptional = repository.findByName(request.getName());
        if (categoryForCheckOptional.isPresent()) {
            throw new AlreadyExistException("Category width name [" + request.getName() + "] already exist");
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setProducts(new ArrayList<>());
        repository.save(category);
        return ResponseCategoryDto.toDTO(category);
    }
}
