package com.shoppinglist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestUpdateCategoryDto {

    private Long id;
    private String name;

}
