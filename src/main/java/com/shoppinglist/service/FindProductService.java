package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public List<ResponseProductDto> findAll() {
        return repository.findAll().stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
    }

    public Optional<ResponseProductDto> findById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            return Optional.of(new ResponseProductDto(productOptional.get().getId(),
                    productOptional.get().getName(),
                    productOptional.get().getQuantity(),
                    productOptional.get().isPurchased(),
                    productOptional.get().getCategory().getName()));
        }
        return Optional.empty();
    }

    public Optional<ResponseProductDto> findByName(String name) {
        Optional<Product> productOptional = repository.findByName(name);
        if (productOptional.isPresent()) {
            return Optional.of(new ResponseProductDto(productOptional.get().getId(),
                    productOptional.get().getName(),
                    productOptional.get().getQuantity(),
                    productOptional.get().isPurchased(),
                    productOptional.get().getCategory().getName()));
        }
        return Optional.empty();
    }

    public List<ResponseProductDto> findByPurchased(boolean purchased) {
        return repository.findByPurchased(purchased).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
    }

    public List<ResponseProductDto> findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
    }

    public List<ResponseProductDto> findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
    }

}
