package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.CartLine.CartLine;
import com.example.ecommercebackend.Entities.CartLine.CartLineService;
import com.example.ecommercebackend.Entities.DummyEntity.DummyCart;
import com.example.ecommercebackend.Entities.User.UserService;
import com.example.ecommercebackend.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    CartLineService cartLineService;
    @Autowired
    UserService userService;

    @GetMapping("/cart")
    public ResponseData getCartsByUserID(@RequestParam int user_id) {
        List<Cart> carts = cartService.getCartsByUserID(user_id);
        if (carts == null || carts.isEmpty()) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        return new ResponseData(carts, 200, HttpStatus.OK);
    }

    @GetMapping("/cart/detail")
    public ResponseData getCartDetailByCartID(@RequestParam int cart_id) {
        List<CartLine> cartLines = cartLineService.getCartLinesByCartID(cart_id);
        List<Integer> productIds = new ArrayList<>();
        for (int i = 0; i < cartLines.size(); i++) {
            productIds.add(cartLines.get(i).getProduct_id());
        }
        Cart cart = cartService.getCartByCartID(cart_id);
        if (cartLines == null) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        if (cartLines.isEmpty()) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        return new ResponseData(new DummyCart(cart.getUser().getUser_id(), cartLines), 200, HttpStatus.OK);
    }

    @DeleteMapping("/cart/clear")
    public ResponseData clearCart(@RequestParam int cart_id) {
        boolean isDeleted = cartLineService.deleteCartLinesByCartID(cart_id);
        if (!isDeleted) {
            return new ResponseData(false, 400, HttpStatus.BAD_REQUEST);
        }
        return new ResponseData(true, 200, HttpStatus.OK);
    }

    @PostMapping("/cart/create")
    public ResponseData createCart(@RequestBody DummyCart dummyCart) {
//        cartService.createNewCart()
        if (userService.getUserByUserID(dummyCart.getUser_id()) == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        }
        Cart cart = cartService.createNewCart(dummyCart.getUser_id());
        for (int i = 0; i < dummyCart.getCartLineList().size(); i++) {
            dummyCart.getCartLineList().get(i).setCart_id(cart.getCart_id());
            cartLineService.CreateCartLine(dummyCart.getCartLineList().get(i));
        }
        return new ResponseData(cartLineService.getCartLinesByCartID(cart.getCart_id()), 200, HttpStatus.OK);
    }
}
