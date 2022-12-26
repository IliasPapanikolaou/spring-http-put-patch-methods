package com.ipap.springhttpputpatchmethods.controller;

import com.ipap.springhttpputpatchmethods.entity.Product;
import com.ipap.springhttpputpatchmethods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.of(service.saveProduct(product));
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.of(service.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        return ResponseEntity.of(service.updateProduct(id, productRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.of(service.updateProductByFields(id, fields));
    }

    @DeleteMapping("/{id}")
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
