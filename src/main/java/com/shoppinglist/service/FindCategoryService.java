package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindCategoryService {

    private CategoryRepository repository;

    public List<ResponseCategoryDto> findAll() {
        return ResponseCategoryDto.toDTOs(repository.findAll());
    }

    public ResponseCategoryDto findById(Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category with id [" + id + "] not found");
        }
        return ResponseCategoryDto.toDTO(categoryOptional.get());
    }

    public ResponseCategoryDto findByName(String name) {
        Optional<Category> categoryOptional = repository.findByName(name);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category with name [" + name + "] not found");
        }
        return ResponseCategoryDto.toDTO(categoryOptional.get());
    }

    public Optional<Category> findByNameForProductServices(String name) {
        return repository.findByName(name);
    }
}
