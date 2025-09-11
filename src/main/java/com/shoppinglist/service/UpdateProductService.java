package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateProductDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {

    private ProductRepository repository;
    private FindCategoryService findCategoryService;

    public MainResponse<ResponseProductDto> updateProduct(RequestUpdateProductDto request) {
        Optional<Product> productOptional = repository.findById(request.getId());
        if (productOptional.isEmpty()) {
            return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with id [" + request.getId() + "] not found", null);
        }
        String nameForCheck = request.getName();
        if (!isProductNameUnique(nameForCheck)) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Product with name [" + nameForCheck + "] already exist", null);
        }

        Product product = productOptional.get();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPurchased(request.isPurchased());

        if (request.getCategoryName() != null) {
            Optional<Category> categoryOptional = findCategoryService.findByNameForProductServices(request.getCategoryName());
            if (categoryOptional.isEmpty()) {
                return new MainResponse<>(HttpStatus.NOT_FOUND, "Category with name [" + request.getCategoryName() + "] not found", null);
            }
            product.setCategory(categoryOptional.get());
        }
        Product updatedProduct = repository.save(product);
        return new MainResponse<>(HttpStatus.OK, "Product with id [" + request.getId() + "] successfully updated", ResponseProductDto.toDTO(updatedProduct));
    }

    private boolean isProductNameUnique(String name) {
        return repository.findByName(name).isEmpty();
    }

}
