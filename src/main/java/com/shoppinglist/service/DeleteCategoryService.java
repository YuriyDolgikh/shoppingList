package com.shoppinglist.service;

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
public class DeleteCategoryService {
    private CategoryRepositoryInterface repository;

    public MainResponse<ResponseCategoryDto> deleteCategoryById(Long id){
        Optional<Category> categoryForDelete = repository.deleteById(id);
        if (categoryForDelete.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Category with id [" + id + "] successfully deleted", ResponseCategoryDto.toDTO(categoryForDelete.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Category with id [" + id + "] not found", null);
    }
}
