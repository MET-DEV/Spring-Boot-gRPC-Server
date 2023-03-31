package com.metsoft.grpcproject.repository;

import com.metsoft.grpcproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
