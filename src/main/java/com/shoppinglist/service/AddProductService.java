package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddProductDto;
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
public class AddProductService {

    private ProductRepository repository;
    private FindCategoryService findCategoryService;

    public ResponseProductDto addProduct(RequestAddProductDto request) {
        Optional<Product> productForCheckOptional = repository.findByName(request.getName());
        if (productForCheckOptional.isPresent()) {
            throw new AlreadyExistException("Product with name [" + request.getName() + "] already exist");
        }
        Optional<Category> categoryOptional = findCategoryService.findByNameForProductServices(request.getCategoryName());
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category with name [" + request.getCategoryName() + "] not found");
        }
        Category category = categoryOptional.get();
        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPurchased(request.isPurchased());
        product.setCategory(category);
        category.getProducts().add(product);
        Product savedProduct = repository.save(product);
        return ResponseProductDto.toDTO(savedProduct);
    }
}
