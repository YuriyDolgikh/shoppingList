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
    public List<Product> findByIsPurchased(boolean isPurchased) {
        List<Product> result = products.values().stream()
                .filter(product -> product.isPurchased() == isPurchased)
                .toList();
        return result;
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        List<Product> result = products.values().stream()
                .filter(product -> product.getCategory().getId().equals(categoryId))
                .toList();
        return result;
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        List<Product> result = products.values().stream()
                .filter(product -> product.getCategory().getName().equalsIgnoreCase(categoryName))
                .toList();
        return result;
    }

    @Override
    public Optional<Product> updateNameById(Long id, String name) {
        Product productToUpdate = products.get(id);
        if (productToUpdate != null) {
            productToUpdate.setName(name);
            return Optional.of(productToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateQuantityById(Long id, Double quantity) {
        Product productToUpdate = products.get(id);
        if (productToUpdate != null) {
            productToUpdate.setQuantity(quantity);
            return Optional.of(productToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateIsPurchasedById(Long id, boolean isPurchased) {
        Product productToUpdate = products.get(id);
        if (productToUpdate != null) {
            productToUpdate.setPurchased(isPurchased);
            return Optional.of(productToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }
}
