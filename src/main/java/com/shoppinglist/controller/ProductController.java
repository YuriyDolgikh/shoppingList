package com.shoppinglist.controller;

import com.shoppinglist.dto.*;
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

    @PostMapping("")
    public ResponseEntity<ResponseProductDto> addNewProduct(@RequestBody RequestAddProductDto request){
        ResponseProductDto response = addProductService.addProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseProductDto>> findAll() {
        List<ResponseProductDto> response = findProductService.findAll();
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseProductDto> findProductById(@PathVariable Long id) {
        ResponseProductDto response = findProductService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponseProductDto>> findProductByNameContext(@PathVariable String name) {
        List<ResponseProductDto> response = findProductService.findByNameContext(name);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/purchased/{purchased}")
    public ResponseEntity<List<ResponseProductDto>> findProductsByPurchased(@PathVariable Boolean purchased) {
        List<ResponseProductDto> response = findProductService.findByPurchased(purchased);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<List<ResponseProductDto>> findProductsByCategoryName(@PathVariable String categoryName) {
        List<ResponseProductDto> response = findProductService.findByCategoryName(categoryName);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productIdToDelete}")
    public ResponseEntity<ResponseProductDto> deleteProductById(@PathVariable Long productIdToDelete) {
        return new ResponseEntity<>(deleteProductService.deleteProductById(productIdToDelete), HttpStatus.OK);
    }

    @PutMapping("/{productId}/setPurchased/{purchased}")
    public ResponseEntity<ResponseProductDto> setPurchased(@PathVariable Long productId, @PathVariable Boolean purchased) {
        return new ResponseEntity<>(updateProductService.setPurchased(productId, purchased), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ResponseProductDto> updateProduct(@RequestBody RequestUpdateProductDto request) {
        return new ResponseEntity<>(updateProductService.updateProduct(request), HttpStatus.OK);
    }
}
