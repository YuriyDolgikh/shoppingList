package com.shoppinglist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    @NotNull(message = "Product name can't be null")
    @NotBlank(message = "Product name can't be blank")
 //   @Size(min = 3, max = 20, message = "Product name must be between 3 and 20 characters")
 //   @Pattern(regexp = "^[a-zA-Z0-9!#$%*()_-]+$", message = "Product name can contain only letters, numbers, !#$%*()_- characters")
    private String name;

    @NotNull(message = "Product quantity can't be null")
    @Min(value = 0, message = "Product quantity must be greater than or equal to 0")
    private Double quantity;

    @NotNull(message = "Product purchased status can't be null")
    private boolean purchased;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
