package com.shoppinglist.service;

import com.shoppinglist.dto.ResponseProductDto;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.entity.Product;
import com.shoppinglist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductRepository repository;

    public MainResponse<ResponseProductDto> deleteProductById(Long id) {
        Product productForDelete = repository.findById(id).orElse(null);
        if (productForDelete != null) {
            repository.deleteById(id);
            return new MainResponse<>(HttpStatus.OK, "Product with id [" + id + "] successfully deleted", ResponseProductDto.toDTO(productForDelete));
        }
        return new MainResponse<>(HttpStatus.NOT_FOUND, "Product with id [" + id + "] not found", null);
    }
}
