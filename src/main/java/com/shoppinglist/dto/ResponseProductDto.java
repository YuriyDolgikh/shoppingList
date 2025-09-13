package com.shoppinglist.dto;

import com.shoppinglist.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static List<ResponseProductDto> toDTOs(List<Product> products){
        return products.stream().map(ResponseProductDto::toDTO).toList();
    }

}
