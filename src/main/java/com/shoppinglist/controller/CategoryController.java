package com.shoppinglist.controller;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.RequestUpdateCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.service.AddCategoryService;
import com.shoppinglist.service.DeleteCategoryService;
import com.shoppinglist.service.FindCategoryService;
import com.shoppinglist.service.UpdateCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private AddCategoryService addCategoryService;
    private FindCategoryService findCategoryService;
    private UpdateCategoryService updateCategoryService;
    private DeleteCategoryService deleteCategoryService;

    @PostMapping("")
    public ResponseEntity<ResponseCategoryDto> addNewCategory(@RequestBody RequestAddCategoryDto request) {
        return new ResponseEntity<>(addCategoryService.addCategory(request), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseCategoryDto>> findAll() {
        List<ResponseCategoryDto> response = findCategoryService.findAll();
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseCategoryDto> findCategoryById(@PathVariable Long id) {
        ResponseCategoryDto response = findCategoryService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseCategoryDto> findCategoryByName(@PathVariable String name) {
        ResponseCategoryDto response = findCategoryService.findByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryIdToDelete}")
    public ResponseEntity<ResponseCategoryDto> deleteCategoryById(@PathVariable Long categoryIdToDelete) {
        ResponseCategoryDto response = deleteCategoryService.deleteCategoryById(categoryIdToDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ResponseCategoryDto> updateCategory(@RequestBody RequestUpdateCategoryDto request) {
        ResponseCategoryDto response = updateCategoryService.updateCategory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
