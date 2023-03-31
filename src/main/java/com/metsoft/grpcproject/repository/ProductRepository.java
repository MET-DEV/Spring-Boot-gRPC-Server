package com.metsoft.grpcproject.repository;

import com.metsoft.grpcproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
