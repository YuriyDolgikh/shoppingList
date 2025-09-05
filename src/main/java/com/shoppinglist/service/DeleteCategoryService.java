package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteCategoryService {
    private CategoryRepositoryInterface repository;

    /**
     * Delete category from the database by id
     * @param id category id to delete
     * @return Optional<ResponseCategoryDto> - category with id and name if category was deleted,
     * empty otherwise if id is not found
     */
    public Optional<ResponseCategoryDto> deleteCategoryById(Long id){
        Optional<Category> category = repository.deleteById(id);
        if (category.isPresent()) {
            return Optional.of(new ResponseCategoryDto(category.get().getId(), category.get().getName()));
        }
        return Optional.empty();
    }
}
