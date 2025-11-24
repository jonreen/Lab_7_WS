package com.ubana.Lab_7.controller;

import com.ubana.Lab_7.model.Product;
import com.ubana.Lab_7.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    // Constructor injection (cleaner than @Autowired, but SAME RESULT)
    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = service.getProductById(id);

        return (product == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(product);
    }

    // CREATE PRODUCT
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = service.addProduct(product);

        return ResponseEntity
                .created(URI.create("/api/products/" + created.getId()))
                .body(created);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product updated = service.updateProduct(id, product);

        return (updated == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.deleteProduct(id);

        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
