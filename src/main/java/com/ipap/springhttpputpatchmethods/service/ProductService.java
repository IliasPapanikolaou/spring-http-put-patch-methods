package com.ipap.springhttpputpatchmethods.service;

import com.ipap.springhttpputpatchmethods.entity.Product;
import com.ipap.springhttpputpatchmethods.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> saveProduct(Product product) {
        return Optional.of(productRepository.save(product));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(int id, Product productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productRequest.getName());
                    product.setProductType(productRequest.getProductType());
                    product.setDescription(productRequest.getDescription());
                    product.setPrice(productRequest.getPrice());
                    return productRepository.save(product);
                });
    }

    public Optional<Product> updateProductByFields(int id, Map<String, Object> fields) {
         return productRepository.findById(id).map(product -> {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, product, value);
                }
            });
            return productRepository.save(product);
        });
    }

    public long deleteProduct(int id) {
        productRepository.deleteById(id);
        return productRepository.count();
    }
}
