package com.assessment.Product_Category.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assessment.Product_Category.Entity.Category;
import com.assessment.Product_Category.Entity.Product;
import com.assessment.Product_Category.Repository.CategoryRepository;
import com.assessment.Product_Category.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getCategory_id());
        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Category not found with id: " + product.getCategory().getCategory_id());  
        }

        Category category = categoryOptional.get();
        product.setCategory(category);  

        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        Optional<Category> categoryOptional = categoryRepository.findById(productDetails.getCategory().getCategory_id());
        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Category not found with id: " + productDetails.getCategory().getCategory_id());
        }

        Category category = categoryOptional.get();

        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setProductPrice(productDetails.getProductPrice());
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }

    public Product deleteProduct(int id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(existingProduct);
        return existingProduct;
    }
}
