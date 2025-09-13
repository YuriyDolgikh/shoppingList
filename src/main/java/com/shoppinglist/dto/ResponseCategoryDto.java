package com.shoppinglist.dto;

import com.shoppinglist.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoryDto {

    private Long id;
    private String name;

    public static ResponseCategoryDto toDTO(Category category){
        return new ResponseCategoryDto(category.getId(), category.getName());
    }

    public static List<ResponseCategoryDto> toDTOs(List<Category> categories){
        return categories.stream().map(ResponseCategoryDto::toDTO).toList();
    }
}
