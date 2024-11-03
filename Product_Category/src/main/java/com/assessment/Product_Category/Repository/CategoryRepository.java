package com.assessment.Product_Category.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.Product_Category.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
