package com.shoppinglist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAddCategoryDto {

    @NotNull(message = "Category name can't be null")
    @NotBlank(message = "Category name can't be blank")
    @Size(min = 3, max = 20, message = "Category name must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Category name can contain only letters and numbers")
    private String name;

}
