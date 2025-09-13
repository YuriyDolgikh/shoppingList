package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Product;
import com.shoppinglist.exception.NotFoundException;
import com.shoppinglist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductRepository repository;

    public ResponseProductDto deleteProductById(Long id) {
        Optional<Product> productForDelete = repository.deleteProductById(id);
        if (productForDelete.isEmpty()) {
            throw new NotFoundException("Product with id [" + id + "] not found");
        }
        return ResponseProductDto.toDTO(productForDelete.get());
    }
}
