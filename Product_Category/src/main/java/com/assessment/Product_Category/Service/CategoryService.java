package com.assessment.Product_Category.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assessment.Product_Category.Entity.Category;
import com.assessment.Product_Category.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Category getCategoryById(int id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category updateCategory(int id, Category categoryDetails) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		existingCategory.setCategoryName(categoryDetails.getCategoryName());

		return categoryRepository.save(existingCategory);
	}

	public Category deleteCategory(int id) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		categoryRepository.delete(existingCategory);
		return existingCategory;
	}
}
