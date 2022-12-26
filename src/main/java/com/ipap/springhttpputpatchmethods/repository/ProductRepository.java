package com.ipap.springhttpputpatchmethods.repository;

import com.ipap.springhttpputpatchmethods.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
