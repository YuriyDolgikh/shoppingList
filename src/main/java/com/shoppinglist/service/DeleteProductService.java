package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductRepositoryInterface repository;

    public Optional<ResponseProductDto> deleteProductById(Long id) {
        Optional<Product> product = repository.deleteById(id);
        if (product.isPresent()) {
            return Optional.of(new ResponseProductDto(product.get().getId(),
                    product.get().getName(),
                    product.get().getQuantity(),
                    product.get().isPurchased(),
                    product.get().getCategory().getName()));
        }
        return Optional.empty();
    }
}
