package com.example.ecommercebackend.Entities.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Brand> findAllBrand() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(Integer id) {
        return brandRepository.findById(id);
    }

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean delete(List<Integer> ids) {
        try {
            brandRepository.deleteAllById(ids);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
