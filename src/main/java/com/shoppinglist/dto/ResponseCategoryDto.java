package com.shoppinglist.dto;

import com.shoppinglist.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoryDto {

    private Long id;
    private String name;

    public static ResponseCategoryDto toDTO(Category category){
        return new ResponseCategoryDto(category.getId(), category.getName());
    }
}
