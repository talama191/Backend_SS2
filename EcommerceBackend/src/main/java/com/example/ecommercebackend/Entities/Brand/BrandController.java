package com.example.ecommercebackend.Entities.Brand;

import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping()
    public ResponseData getAllBrands() {
        return new ResponseData(brandService.findAllBrand(), 200, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseData getBrandByID(@PathVariable Integer id) {
        Optional<Brand> brand = brandService.findById(id);
        if (brand.isEmpty()) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(brandService.findById(id), 200, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseData createNewBrand(@RequestBody Brand brand) {
        return new ResponseData(brandService.save(brand), 200, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseData updateBrand(@RequestBody Brand brand) {
        if (brand.getId() == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(brandService.save(brand), 200, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseData deleteBrand(@RequestBody List<Integer> brandIds) {
        boolean isOk = brandService.delete(brandIds);
        if (isOk) {
            return new ResponseData(true, 200, HttpStatus.OK);
        } else {
            return new ResponseData(false, 400, HttpStatus.BAD_REQUEST);
        }

    }
}
