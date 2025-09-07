package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Category;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindProductService {

    private ProductRepositoryInterface repository;
    private FindCategoryService findCategoryService;

    public MainResponse<List<ResponseProductDto>> findAll() {
        List<ResponseProductDto> resultList = repository.findAll().stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
        if (!resultList.isEmpty()) {
            return new MainResponse<>(HttpStatus.OK, "Products successfully found", resultList);
        }
        return new MainResponse<>(HttpStatus.NO_CONTENT, "Products not found", resultList);
    }

    public MainResponse<ResponseProductDto> findById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Product with id [" + id + "] successfully found", ResponseProductDto.toDTO(productOptional.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with id [" + id + "] not found", null);
    }

    public MainResponse<ResponseProductDto> findByName(String name) {
        Optional<Product> productOptional = repository.findByName(name);
        if (productOptional.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Product with name [" + name + "] successfully found", ResponseProductDto.toDTO(productOptional.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with name [" + name + "] not found", null);
    }

    public MainResponse<List<ResponseProductDto>> findByPurchased(boolean purchased) {
        List<ResponseProductDto> resultList =  repository.findByPurchased(purchased).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
        if (!resultList.isEmpty()) {
            return new MainResponse<>(HttpStatus.OK, "Products successfully found", resultList);
        }
        return new MainResponse<>(HttpStatus.NO_CONTENT, "Products not found", resultList);
    }

    public MainResponse<List<ResponseProductDto>> findByCategoryId(Long categoryId) {
        Optional<Category> category = findCategoryService.findByIdForProductServices(categoryId);
        if (category.isEmpty()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category with id [" + categoryId + "] not exists", null);
        }
        List<ResponseProductDto> resultList = repository.findByCategoryId(categoryId).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
        if (!resultList.isEmpty()) {
            return new MainResponse<>(HttpStatus.OK, "Products successfully found", resultList);
        }
        return new MainResponse<>(HttpStatus.NO_CONTENT, "Products not found", resultList);
    }

    public MainResponse<List<ResponseProductDto>> findByCategoryName(String categoryName) {
        Optional<Category> category = findCategoryService.findByNameForProductServices(categoryName);
        if (category.isEmpty()) {
            return new MainResponse<>(HttpStatus.BAD_REQUEST, "Category with name [" + categoryName + "] not exists", null);
        }
        List<ResponseProductDto> resultList = repository.findByCategoryName(categoryName).stream()
                .map(product -> new ResponseProductDto(product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.isPurchased(),
                        product.getCategory().getName()))
                .toList();
        if (!resultList.isEmpty()) {
            return new MainResponse<>(HttpStatus.OK, "Products successfully found", resultList);
        }
        return new MainResponse<>(HttpStatus.NO_CONTENT, "Products not found", resultList);
    }

}
