package com.example.ecommercebackend.Entities.Category;

import com.example.ecommercebackend.Product.Product;
import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseData getAllCategory() {
        return new ResponseData(categoryService.findAll(),200, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseData createNewCategory(@RequestBody Category category) {
        return new ResponseData(categoryService.save(category),200, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseData updateCategory(@RequestBody Category category) {
        Category returnCat = categoryService.updateProduct(category);
        System.out.println(returnCat);
        if (returnCat == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(returnCat, 200, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseData getCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> new ResponseData(category,200 ,HttpStatus.OK))
                .orElseGet(() -> new ResponseData(null, 400,HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> {
            categoryService.remove(id);
            return new ResponseData(category,200, HttpStatus.OK);
        }).orElseGet(() -> new ResponseData(null,400, HttpStatus.NOT_FOUND));
    }

}
