package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateProductDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public MainResponse<ResponseProductDto> updateProduct(RequestUpdateProductDto request) {
        String nameForCheck = request.getName();
        if (!isProductNameUnique(nameForCheck)) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Product with name [" + nameForCheck + "] already exist", null);
        }
        Optional<Product> updatedProductOptional = repository.update(new Product(request.getId(),
                request.getName(),
                request.getQuantity(),
                request.isPurchased(),
                findCategoryService.findByNameForProductServices(request.getCategoryName()).get()));
        if (updatedProductOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Product with id [" + request.getId() + "] successfully updated", ResponseProductDto.toDTO(updatedProductOptional.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with id [" + request.getId() + "] not found", null);
    }

    private boolean isProductNameUnique(String name) {
        return repository.findByName(name).isEmpty();
    }

}
