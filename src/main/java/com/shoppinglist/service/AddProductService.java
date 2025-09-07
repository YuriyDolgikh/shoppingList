package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddProductDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public Optional<ResponseProductDto> addProduct(RequestAddProductDto request){
        Optional<Product> productForCheckOptional = repository.findByName(request.getName());
        if (productForCheckOptional.isPresent()) {
            return Optional.of(new ResponseProductDto(productForCheckOptional.get().getId(),
                    productForCheckOptional.get().getName(),
                    productForCheckOptional.get().getQuantity(),
                    productForCheckOptional.get().isPurchased(),
                    productForCheckOptional.get().getCategory().getName()));
        }
        MainResponse<ResponseCategoryDto> categoryNameForCheckOptional = findCategoryService.findByName(request.getCategoryName());
        if (categoryNameForCheckOptional.getStatus().is2xxSuccessful()) {
            Category category = findCategoryService.findByNameForCreatingProduct(request.getCategoryName()).get();
            Product product = new Product(request.getName(), request.getQuantity(), request.isPurchased(), category);
            Product savedProduct = repository.add(product);
            return Optional.of(new ResponseProductDto(savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getQuantity(),
                    savedProduct.isPurchased(),
                    savedProduct.getCategory().getName()));
        }
        return Optional.empty();
    }
}
