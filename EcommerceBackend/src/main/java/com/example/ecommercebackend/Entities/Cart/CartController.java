package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.CartLine.CartLine;
import com.example.ecommercebackend.Entities.CartLine.CartLineService;
import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    CartLineService cartLineService;

    @GetMapping("/cart")
    public ResponseData getCartByUserID(@RequestParam int user_id) {
        Cart cart = cartService.getCartByUserID(user_id);
        if (cart == null) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        return new ResponseData(cartService.getCartByUserID(user_id), 200, HttpStatus.OK);
    }

    @GetMapping("/cart/detail")
    public ResponseData getCartDetailByCartID(@RequestParam int cart_id) {
        List<CartLine> cartLines = cartLineService.getCartLinesByCartID(cart_id);
        if (cartLines == null) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }

        if (cartLines.isEmpty()) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        return new ResponseData(cartLines, 200, HttpStatus.OK);
    }

    @DeleteMapping("/cart/clear")
    public ResponseData clearCart(@RequestParam int cart_id) {
        boolean isDeleted = cartLineService.deleteCartLinesByCartID(cart_id);
        if (!isDeleted) {
            return new ResponseData(false, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(true, 200, HttpStatus.OK);
    }
}
