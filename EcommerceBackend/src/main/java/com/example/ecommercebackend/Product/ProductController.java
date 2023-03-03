package com.example.ecommercebackend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/Products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
