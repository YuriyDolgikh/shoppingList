package com.shoppinglist.service;

import com.shoppinglist.dto.RequestAddProductDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
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

    /**
     * Add a product to the database
     * @param request RequestAddProductDto - request with product name, quantity, isPurchased and category name
     * @return Optional<ResponseProductDto> - product with product name, quantity, isPurchased and category name if product was added or product name already exists,
     * empty otherwise if category name is not found
     */
    public Optional<ResponseProductDto> addProduct(RequestAddProductDto request){
        Optional<Product> productForCheckOptional = repository.findByName(request.getName());
        if (productForCheckOptional.isPresent()) {
            return Optional.of(new ResponseProductDto(productForCheckOptional.get().getId(),
                    productForCheckOptional.get().getName(),
                    productForCheckOptional.get().getQuantity(),
                    productForCheckOptional.get().isPurchased(),
                    productForCheckOptional.get().getCategory().getName()));
        }
        Optional<ResponseCategoryDto> categoryNameForCheckOptional = findCategoryService.findByName(request.getCategoryName());
        if (categoryNameForCheckOptional.isPresent()) {
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
