package com.shoppinglist.dto;

import com.shoppinglist.entity.Product;
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
    private String categoryName;

    public static ResponseProductDto toDTO(Product product){
        return new ResponseProductDto(product.getId(),
                product.getName(),
                product.getQuantity(),
                product.isPurchased(),
                product.getCategory().getName());
    }

}
