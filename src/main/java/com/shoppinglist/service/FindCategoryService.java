package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindCategoryService {

    private CategoryRepositoryInterface repository;

    /**
     * Find all categories from the database
     * @return List<ResponseCategoryDto> - list of categories (DTO Class: ResponseCategoryDto)
     */
    public List<ResponseCategoryDto> findAll(){
        return repository.findAll().stream()
                .map(category -> new ResponseCategoryDto(category.getId(), category.getName()))
                .toList();
    }

    /**
     * Find category by id
     * @param id category id to find
     * @return Optional<ResponseCategoryDto> - category with id and name if category was found, empty otherwise
     */
    public Optional<ResponseCategoryDto> findBiId(Long id){
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            return Optional.of(new ResponseCategoryDto(categoryOptional.get().getId(), categoryOptional.get().getName()));
        }
        return Optional.empty();
    }

    /**
     * Find category by name
     * @param name category name to find
     * @return String - message about success or failure
     */
    public Optional<ResponseCategoryDto> findByName(String name){
        Optional<Category> categoryOptional = repository.findByName(name);
        if (categoryOptional.isPresent()) {
            return Optional.of(new ResponseCategoryDto(categoryOptional.get().getId(), categoryOptional.get().getName()));
        }
        return Optional.empty();
    }

    public Optional<Category> findByNameForCreatingProduct(String name){
        return repository.findByName(name);
    }
}
