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

    /**
     * Add a new category to the database
     * @param request request with category name
     * @return ResponseEntity with status and category dto if the category was added successfully,
     *         or status otherwise
     */
    @PostMapping("")
    public ResponseEntity<ResponseCategoryDto> addNewCategory(@RequestBody RequestAddCategoryDto request){
        MainResponse<ResponseCategoryDto> response = addCategoryService.addCategory(request);
        if (response.getStatus().is2xxSuccessful()) {
            return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
        } else {
            return new ResponseEntity<>(response.getStatus());
        }
    }

    /**
     * Get all categories from the database
     * @return ResponseEntity with status and list of category dtos
     */
    @GetMapping("")
    public ResponseEntity<List<ResponseCategoryDto>> findAll() {
        MainResponse<List<ResponseCategoryDto>> response = findCategoryService.findAll();
        return new ResponseEntity<>(response.getResponseObject(), response.getStatus());
    }

    /**
     *
     * @param id id of the category to find
     * @param name name of the category to find
     * @return ResponseEntity with status and category dto if the category was found successfully,
     *         or status otherwise.
     * @Note: id and name can't be specified at the same time.
     */
    @GetMapping("/item")
    public ResponseEntity<ResponseCategoryDto> findCategoryWithParams(@RequestParam(required = false, defaultValue = "-1") Long id,
                                                                      @RequestParam(required = false, defaultValue = "") String name) {
        if (id != -1 && !name.isBlank()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (id != -1) {
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

    /**
     * Delete category by id
     * @param categoryIdToDelete id of the category to delete
     * @return ResponseEntity with status and category dto if the category was deleted successfully,
     *         or status otherwise
     */
    @DeleteMapping("/{categoryIdToDelete}")
    public ResponseEntity<ResponseCategoryDto> deleteCategoryById(@PathVariable Long categoryIdToDelete) {
        MainResponse<ResponseCategoryDto> response = deleteCategoryService.deleteCategoryById(categoryIdToDelete);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }

    /**
     * Update category by id
     * @param request request with category name and id
     * @return ResponseEntity with status and category dto if the category was updated successfully,
     *         or status otherwise
     */
    @PutMapping("")
    public ResponseEntity<ResponseCategoryDto> updateCategory(@RequestBody RequestUpdateCategoryDto request) {
        MainResponse<ResponseCategoryDto> response = updateCategoryService.updateCategory(request);
        return response.getStatus().is2xxSuccessful() ? new ResponseEntity<>(response.getResponseObject(), response.getStatus())
                : new ResponseEntity<>(response.getStatus());
    }
}
