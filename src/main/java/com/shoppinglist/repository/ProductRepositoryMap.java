package com.shoppinglist.repository;

import com.shoppinglist.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryMap implements ProductRepositoryInterface{

    Map<Long, Product> products;
    Long idCounter;

    public ProductRepositoryMap() {
        this.products = new HashMap<>();
        this.idCounter = 0L;
    }


    @Override
    public Product add(Product product) {
        product.setId(++idCounter);
        products.put(idCounter, product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Optional<Product> findByName(String name) {
        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findByPurchased(boolean purchased) {
        return products.values().stream()
                .filter(product -> product.isPurchased() == purchased)
                .toList();
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return products.values().stream()
                .filter(product -> product.getCategory().getId().equals(categoryId))
                .toList();
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return products.values().stream()
                .filter(product -> product.getCategory().getName().equalsIgnoreCase(categoryName))
                .toList();
    }

    @Override
    public Optional<Product> update(Product product) {
        Product productToUpdate = products.get(product.getId()) ;
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setQuantity(product.getQuantity());
            productToUpdate.setPurchased(product.isPurchased());
            return Optional.of(productToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }
}
