package com.shoppinglist.service;

import com.shoppinglist.dto.RequestUpdateProductDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.Product;
import com.shoppinglist.exception.AlreadyExistException;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {

    private ProductRepository repository;
    private FindCategoryService findCategoryService;

    public ResponseProductDto setPurchased(Long productId, boolean isPurchased) {
        Optional<Product> productOptional = repository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id [" + productId + "] not found");
        }
        Product product = productOptional.get();
        product.setPurchased(isPurchased);
        return ResponseProductDto.toDTO(repository.save(product));
    }

    public ResponseProductDto updateProduct(RequestUpdateProductDto request) {
        Optional<Product> productOptional = repository.findById(request.getId());
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id [" + request.getId() + "] not found");
        }
        String nameForCheck = request.getName();
        if (!isProductNameUnique(nameForCheck) && !productOptional.get().getName().equals(nameForCheck)) {
            throw new AlreadyExistException("Product with name [" + nameForCheck + "] already exist or equal to the name of the product to update");
        }
        Optional<Category> newCategoryOptional = findCategoryService.findByNameForProductServices(request.getCategoryName());
        if (newCategoryOptional.isEmpty()) {
            throw new NotFoundException("Category with name [" + request.getCategoryName() + "] not found");
        }

        Product product = productOptional.get();
        Category newCategory = newCategoryOptional.get();
        Category oldCategory = product.getCategory();
        if (!oldCategory.equals(newCategory)) {
            oldCategory.getProducts().remove(product);
            newCategory.getProducts().add(product);
        }
        product.setCategory(newCategory);
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPurchased(request.isPurchased());

        return ResponseProductDto.toDTO(repository.save(product));
    }

    private boolean isProductNameUnique(String name) {
        return repository.findByName(name).isEmpty();
    }

}
