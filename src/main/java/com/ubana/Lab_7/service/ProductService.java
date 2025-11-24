package com.ubana.Lab_7.service;

import com.ubana.Lab_7.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    // In-memory product storage
    private final Map<Long, Product> products = new HashMap<>();

    // Return all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // Find product by ID
    public Product getProductById(Long id) {
        return products.get(id);
    }

    // Add a new product
    public Product addProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    // Update an existing product
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = products.get(id);

        if (existing == null) {
            return null;
        }

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        return existing;
    }

    // Delete a product
    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
