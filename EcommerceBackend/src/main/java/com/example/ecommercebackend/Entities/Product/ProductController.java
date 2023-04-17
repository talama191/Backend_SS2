package com.example.ecommercebackend.Entities.Product;

import com.example.ecommercebackend.Entities.SearchFilter.ProductSearchFilter;
import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseData getAllProducts() {
        return new ResponseData(productService.getAllProducts(), 200, HttpStatus.OK);
    }

    @GetMapping("/products/category")
    public ResponseData getProductsByCategory(@RequestParam int category_id) {
        List<Product> products = productService.getProductsByCategory(category_id);
        return new ResponseData(products, 200, HttpStatus.OK);
    }

    @PostMapping("/products/add")
    public ResponseData addProduct(@RequestBody Product product) {
        Product returnProduct = productService.addProduct(product);
        if (returnProduct == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(returnProduct, 201, HttpStatus.CREATED);
    }

    @PostMapping("/products/update")
    public ResponseData updateProduct(@RequestBody Product product) {
        Product returnProduct = productService.updateProduct(product);
        if (returnProduct == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(returnProduct, 200, HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseData searchProduct(@RequestBody ProductSearchFilter filter) {
        List<Product> products = productService.searchProduct(filter);

        return new ResponseData(products, 200, HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseData deleteProduct(@RequestParam List<Integer> ids) {
        boolean isLineDeleted = productService.deleteProduct(ids);
        if (!isLineDeleted) {
            return new ResponseData(false, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(true, 200, HttpStatus.OK);
    }


}
