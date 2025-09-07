package com.shoppinglist.service;

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
public class DeleteProductService {
    private ProductRepositoryInterface repository;

    public MainResponse<ResponseProductDto> deleteProductById(Long id) {
        Optional<Product> product = repository.deleteById(id);
        if (product.isPresent()) {
            return new MainResponse<>(HttpStatus.OK, "Product with id [" + id + "] successfully deleted", ResponseProductDto.toDTO(product.get()));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with id [" + id + "] not found", null);
    }
}
