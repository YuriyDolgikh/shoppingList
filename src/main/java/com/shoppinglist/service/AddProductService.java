package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddProductDto;
import com.shoppinglist.dto.ResponseCategoryDto;
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
public class AddProductService {

    private ProductRepository repository;
    private FindCategoryService findCategoryService;

    public MainResponse<ResponseProductDto> addProduct(RequestAddProductDto request){
        Optional<Product> productForCheckOptional = repository.findByName(request.getName());
        if (productForCheckOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Product with name [" + request.getName() + "] already exist", null);
        }
        MainResponse<ResponseCategoryDto> categoryNameForCheckOptional = findCategoryService.findByName(request.getCategoryName());
        if (categoryNameForCheckOptional.getStatus().is2xxSuccessful()) {
            Category category = findCategoryService.findByNameForProductServices(request.getCategoryName()).get();
            Product product = new Product();
            product.setName(request.getName());
            product.setQuantity(request.getQuantity());
            product.setPurchased(request.isPurchased());
            product.setCategory(category);
            Product savedProduct = repository.save(product);
            return new MainResponse<>(HttpStatus.CREATED, "Product with name [" + request.getName() + "] successfully added", ResponseProductDto.toDTO(savedProduct));
        }
        return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category with name [" + request.getCategoryName() + "] not found", null);
    }
}
