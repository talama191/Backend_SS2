package com.example.ecommercebackend.Entities.CartLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartLineController {
    @Autowired
    CartLineService cartLineService;
}
