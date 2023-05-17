package com.example.ecommercebackend.Entities.CartLine;

import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cart_lines")
public class CartLineController {
    @Autowired
    CartLineService cartLineService;

    @GetMapping("")
    public ResponseData getAllCartLines() {
        return new ResponseData(cartLineService.getAllCartLines(), 200, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseData getCartLinesByCartID(@RequestParam int cart_id) {
        return new ResponseData(cartLineService.getCartLinesByCartID(cart_id), 200, HttpStatus.OK);
    }
}
