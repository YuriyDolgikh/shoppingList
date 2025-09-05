package com.shoppinglist.dto;

import com.shoppinglist.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAddProductDto {

    private String name;
    private Double quantity;
    private boolean isPurchased;
    private String categoryName;

}
