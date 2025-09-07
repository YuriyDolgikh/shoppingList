package com.shoppinglist.controller;

import com.shoppinglist.dto.RequestAddCategoryDto;
import com.shoppinglist.dto.RequestUpdateCategoryDto;
import com.shoppinglist.dto.ResponseCategoryDto;
import com.shoppinglist.entity.MainResponse;
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

    @PostMapping("/new")
    public ResponseEntity<ResponseCategoryDto> addNewCategory(@RequestBody RequestAddCategoryDto request){
        MainResponse<ResponseCategoryDto> response = addCategoryService.addCategory(request);
        if (response.getStatus().is2xxSuccessful()) {
            return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
        } else {
            return new ResponseEntity<>(response.getStatus());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseCategoryDto>> findAll() {
        MainResponse<List<ResponseCategoryDto>> response = findCategoryService.findAll();
        return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
    }

    @GetMapping("/item")
    public ResponseEntity<ResponseCategoryDto> findById(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null && !name.isBlank()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (id != null) {
            MainResponse<ResponseCategoryDto> response = findCategoryService.findById(id);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else if (!name.isBlank()) {
            MainResponse<ResponseCategoryDto> response = findCategoryService.findByName(name);
            return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                    : new ResponseEntity<>(response.getStatus());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{categoryIdToDelete}")
    public ResponseEntity<ResponseCategoryDto> deleteCategoryById(@PathVariable Long categoryIdToDelete) {
        MainResponse<ResponseCategoryDto> response = deleteCategoryService.deleteCategoryById(categoryIdToDelete);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }

    @PutMapping("")
    public ResponseEntity<ResponseCategoryDto> updateCategory(@RequestBody RequestUpdateCategoryDto request) {
        MainResponse<ResponseCategoryDto> response = updateCategoryService.updateCategory(request);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }
}
