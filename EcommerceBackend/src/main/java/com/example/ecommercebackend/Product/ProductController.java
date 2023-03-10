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

    @GetMapping("/Products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/Products/Category")
    public List<Product> getProductsByCategory(@RequestParam int category_id) {
        List<Product> products = productService.getProductsByCategory(category_id);
        return products;
    }

    @PostMapping("/Products/Add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product returnProduct = productService.addProduct(product);
        if (returnProduct == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnProduct, HttpStatus.OK);
    }

    @PostMapping("/Products/Update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product returnProduct = productService.updateProduct(product);
        if (returnProduct == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnProduct, HttpStatus.OK);
    }

    @DeleteMapping("/Products/Delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam int id) {
        int lineDeleted = productService.deleteProduct(id);
        if (lineDeleted == 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
