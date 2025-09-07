package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.repository.CategoryRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindCategoryService {

    private CategoryRepositoryInterface repository;

    public MainResponse<List<ResponseCategoryDto>> findAll(){
        List<ResponseCategoryDto> categoryDtoList = repository.findAll().stream()
                .map(category -> new ResponseCategoryDto(category.getId(), category.getName()))
                .toList();
        if (!categoryDtoList.isEmpty()) {
            return new MainResponse<>(HttpStatus.OK, "Categories successfully found", categoryDtoList);
        }else {
            return new MainResponse<>(HttpStatus.NO_CONTENT, "Categories not found", null);
        }
    }

    public MainResponse<ResponseCategoryDto> findById(Long id){
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Category with id [" + id + "] successfully found", ResponseCategoryDto.toDTO(categoryOptional.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Category with id [" + id + "] not found", null);
    }

    public MainResponse<ResponseCategoryDto> findByName(String name){
        Optional<Category> categoryOptional = repository.findByName(name);
        if (categoryOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Category with name [" + name + "] successfully found", ResponseCategoryDto.toDTO(categoryOptional.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Category with name [" + name + "] not found", null);
    }

    public Optional<Category> findByNameForProductServices(String name){
        return repository.findByName(name);
    }

    public Optional<Category> findByIdForProductServices(Long id){
        return repository.findById(id);
    }
}
