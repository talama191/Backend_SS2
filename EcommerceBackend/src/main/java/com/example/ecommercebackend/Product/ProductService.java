package com.example.ecommercebackend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }
}
