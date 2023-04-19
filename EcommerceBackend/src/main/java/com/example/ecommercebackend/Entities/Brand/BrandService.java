package com.example.ecommercebackend.Entities.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

}
