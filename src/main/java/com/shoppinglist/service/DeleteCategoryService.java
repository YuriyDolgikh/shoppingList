package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteCategoryService {
    private CategoryRepository repository;

    public ResponseCategoryDto deleteCategoryById(Long id) {
        Optional<Category> categoryForDeleteOptional = repository.findById(id);
        if (categoryForDeleteOptional.isEmpty()) {
            throw new NotFoundException("Category with id [" + id + "] not found");
        }
        repository.deleteById(id);
        return ResponseCategoryDto.toDTO(categoryForDeleteOptional.get());
    }
}
