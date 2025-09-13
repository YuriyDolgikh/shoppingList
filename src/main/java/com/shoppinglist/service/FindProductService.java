package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.Product;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindProductService {

    private ProductRepository repository;
    private FindCategoryService findCategoryService;

    public List<ResponseProductDto> findAll() {
        return repository.findAll().stream().map(ResponseProductDto::toDTO).toList();
    }

    public ResponseProductDto findById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id [" + id + "] not found");
        }
        return ResponseProductDto.toDTO(productOptional.get());
    }

    public List<ResponseProductDto> findByNameContext(String name) {
        return ResponseProductDto.toDTOs(repository.findByNameContainingIgnoreCase(name));
    }

    public List<ResponseProductDto> findByPurchased(boolean purchased) {
        return ResponseProductDto.toDTOs(repository.findByPurchased(purchased));
    }

    public List<ResponseProductDto> findByCategoryName(String categoryName) {
        Optional<Category> category = findCategoryService.findByNameForProductServices(categoryName);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with name [" + categoryName + "] not found");
        }
        return ResponseProductDto.toDTOs(repository.findByCategoryName(categoryName));
    }
}
