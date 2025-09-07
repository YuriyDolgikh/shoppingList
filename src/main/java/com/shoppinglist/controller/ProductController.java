package com.shoppinglist.controller;

import com.shoppinglist.dto.*;
import com.shoppinglist.entity.MainResponse;
import com.shoppinglist.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private AddProductService addProductService;
    private FindProductService findProductService;
    private UpdateProductService updateProductService;
    private DeleteProductService deleteProductService;

    @PostMapping("/new")
    public ResponseEntity<ResponseProductDto> addNewCategory(@RequestBody RequestAddProductDto request){
        MainResponse<ResponseProductDto> response = addProductService.addProduct(request);
        if (response.getStatus().is2xxSuccessful()) {
            return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
        } else {
            return new ResponseEntity<>(response.getStatus());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseProductDto>> findAll() {
        MainResponse<List<ResponseProductDto>> response = findProductService.findAll();
        return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
    }

    @GetMapping("/item")
    public ResponseEntity<ResponseProductDto> findProductWithParams(@RequestParam(required = false, defaultValue = "-1") Long id,
                                                                    @RequestParam(required = false, defaultValue = "") String name) {
        if (id != -1 && !name.isBlank()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (id != -1) {
            MainResponse<ResponseProductDto> response = findProductService.findById(id);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else if (!name.isBlank()) {
            MainResponse<ResponseProductDto> response = findProductService.findByName(name);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<ResponseProductDto>> findProductsWithParams(@RequestParam(required = false, defaultValue = "null") Boolean purchased,
                                                                           @RequestParam(required = false, defaultValue = "-1") Long categoryId,
                                                                           @RequestParam(required = false, defaultValue = "") String categoryName) {

        if (purchased != null ^ categoryId != -1 ^ !categoryName.isBlank()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (purchased != null) {
            MainResponse<List<ResponseProductDto>> response = findProductService.findByPurchased(purchased);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        }
        if (categoryId != -1) {
            MainResponse<List<ResponseProductDto>> response = findProductService.findByCategoryId(categoryId);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else if (!categoryName.isBlank()) {
            MainResponse<List<ResponseProductDto>> response = findProductService.findByCategoryName(categoryName);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{productIdToDelete}")
    public ResponseEntity<ResponseProductDto> deleteProductById(@PathVariable Long productIdToDelete) {
        MainResponse<ResponseProductDto> response = deleteProductService.deleteProductById(productIdToDelete);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }

    @PutMapping("")
    public ResponseEntity<ResponseProductDto> updateProduct(@RequestBody RequestUpdateProductDto request) {
        MainResponse<ResponseProductDto> response = updateProductService.updateProduct(request);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }
}
