package com.example.ecommercebackend.Product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public List<Product> getProductsByCategory(int category_id) {
        List<Product> products = new ArrayList<>();
        products = productRepository.getProductsByCategory(category_id);
        return products;
    }

    @Transactional
    public Product addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        product = productRepository.saveAndFlush(product);
        return product;
    }

    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.saveAndFlush(product);
        }
        return null;
    }

    @Transactional
    public int deleteProduct(int id) {
        return productRepository.deleteProductByID(id);
    }
}
