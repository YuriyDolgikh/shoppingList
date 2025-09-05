package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateProductDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public Optional<ResponseProductDto> updateProduct(RequestUpdateProductDto request) {
        String nameForCheck = request.getName();
        if(!isProductNameUnique(nameForCheck)) {
            Product productExisted = repository.findByName(nameForCheck).get();
            return Optional.of(new ResponseProductDto(productExisted.getId(),
                    productExisted.getName(),
                    productExisted.getQuantity(),
                    productExisted.isPurchased(),
                    productExisted.getCategory().getName()));
        }
        Optional<Product> updatedProductOptional = repository.update(new Product(request.getId(),
                request.getName(),
                request.getQuantity(),
                request.isPurchased(),
                findCategoryService.findByNameForCreatingProduct(request.getCategoryName()).get()));
        if (updatedProductOptional.isPresent()) {
            return Optional.of(new ResponseProductDto(updatedProductOptional.get().getId(),
                    updatedProductOptional.get().getName(),
                    updatedProductOptional.get().getQuantity(),
                    updatedProductOptional.get().isPurchased(),
                    updatedProductOptional.get().getCategory().getName()));
        }
        return Optional.empty();
    }

    private boolean isProductNameUnique(String name){
        return repository.findByName(name).isEmpty();
    }

}
