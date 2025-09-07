package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddProductDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public MainResponse<ResponseProductDto> addProduct(RequestAddProductDto request){
        Optional<Product> productForCheckOptional = repository.findByName(request.getName());
        if (productForCheckOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Product with name [" + request.getName() + "] already exist", null);
        }
        MainResponse<ResponseCategoryDto> categoryNameForCheckOptional = findCategoryService.findByName(request.getCategoryName());
        if (categoryNameForCheckOptional.getStatus().is2xxSuccessful()) {
            Category category = findCategoryService.findByNameForProductServices(request.getCategoryName()).get();
            Product product = new Product(request.getName(), request.getQuantity(), request.isPurchased(), category);
            Product savedProduct = repository.add(product);
            return new MainResponse<>(HttpStatus.CREATED, "Product with name [" + request.getName() + "] successfully added", ResponseProductDto.toDTO(savedProduct));
        }
        return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category with name [" + request.getCategoryName() + "] not found", null);
    }
}
