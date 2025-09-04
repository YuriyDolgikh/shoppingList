package com.shoppinglist.dto;

import com.shoppinglist.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDto {

    private Long id;
    private String name;
    private Double quantity;
    private boolean isPurchased;
    private Category category;

}
