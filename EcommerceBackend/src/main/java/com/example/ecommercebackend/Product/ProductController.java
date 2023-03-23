package com.example.ecommercebackend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam int category_id) {
        List<Product> products = productService.getProductsByCategory(category_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product returnProduct = productService.addProduct(product);
        if (returnProduct == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnProduct, HttpStatus.OK);
    }

    @PostMapping("/products/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product returnProduct = productService.updateProduct(product);
        if (returnProduct == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam int id) {
        int lineDeleted = productService.deleteProduct(id);
        if (lineDeleted == 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
