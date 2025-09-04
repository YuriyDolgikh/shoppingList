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

    public List<ResponseCategoryDto> findAll(){
        return repository.findAll().stream()
                .map(category -> new ResponseCategoryDto(category.getId(), category.getName()))
                .toList();
    }

    public String findBiId(Long id){
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get().toString();
        }
        return "Category with ID = " + id + " not found";
    }

    public String findByName(String name){
        Optional<Category> categoryOptional = repository.findByName(name);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get().toString();
        }
        return "Category with name = " + name + " not found";
    }

}
