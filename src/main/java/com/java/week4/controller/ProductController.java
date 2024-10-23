package com.java.week4.controller;

import com.java.week4.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private long currentId = 1;

    public ProductController() {
        products.add(new Product(1L, "Laptop", "A high-performance laptop", 1200.00));
        products.add(new Product(2L, "Smartphone", "A cutting-edge smartphone", 799.99));
        products.add(new Product(3L, "Headphones", "Noise-cancelling headphones", 199.99));
    }

    // GET products
    @GetMapping("/api/v1/products")
    public List<Product> getAllProducts() {
        return products;
    }

    // GET product by ID using Path Variable
    @GetMapping("/api/v1/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // POST
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(currentId++);
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
