package com.assessment.Product_Category.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.Product_Category.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
