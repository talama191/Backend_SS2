package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.CartLine.CartLine;
import com.example.ecommercebackend.Entities.CartLine.CartLineService;
import com.example.ecommercebackend.Entities.DummyEntity.DummyCart;
import com.example.ecommercebackend.Entities.Product.ProductService;
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
    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public ResponseData getCartsByUserID(@RequestParam int user_id) {
        List<Cart> carts = cartService.getCartsByUserID(user_id);
        if (carts == null || carts.isEmpty()) {
            return new ResponseData(null, 404, HttpStatus.NOT_FOUND);
        }
        return new ResponseData(carts, 200, HttpStatus.OK);
    }
    @GetMapping("/cart/all")
    public ResponseData getAllCart() {
        List<Cart> carts = cartService.getAllCarts();
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
            cartLines.get(i).setProduct(productService.getProductByID(cartLines.get(i).getProduct_id()));
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

    @GetMapping("/cart/get_current")
    public ResponseData getCurrentCartByUserID(@RequestParam int user_id) {
        Cart cart = cartService.getActiveCartByUserID(user_id);
        if (cart == null) {
            cart = cartService.createNewCart(user_id);
        }
        return new ResponseData(cart, 200, HttpStatus.OK);
    }

    //    @PostMapping("/cart/add_product")
//    public ResponseData addProductToCurrentCartByUserID(@RequestParam int user_id, @RequestParam int product_id) {
//        throw new NotYetImplementedException();
//    }
    @PostMapping("/cart/modify")
    public ResponseData modifyProductOfCurrentCartByUserID(@RequestParam int user_id, @RequestParam int product_id, @RequestParam int quantity) {
        CartLine cartLine = cartService.modifyProductToCurrentCart(user_id, product_id, quantity);
        if (cartLine == null) {
            return new ResponseData(null, 202, HttpStatus.ACCEPTED);
        } else {
            return new ResponseData(cartLine, 200, HttpStatus.OK);
        }
    }

    @PostMapping("/cart/order_without_pay")
    public ResponseData OrderWithoutPay(@RequestBody Cart cart, @RequestParam Integer cart_id) {
        cart.setCart_id(cart_id);
        Cart newCart = cartService.OrderWithoutPay(cart);
        if (newCart == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseData(newCart, 200, HttpStatus.OK);
        }
    }

    @PostMapping("/cart/modify_add_single")
    public ResponseData addProductToCurrentCartByUserID(@RequestParam int user_id, @RequestParam int product_id, @RequestParam int quantity) {
        CartLine cartLine = cartService.addProductsToCurrentCart(user_id, product_id, quantity);
        if (cartLine == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseData(cartLine, 200, HttpStatus.OK);
        }
    }

    @PostMapping("/cart/set_status")
    public ResponseData setCartStatus(@RequestParam int cart_id, @RequestParam int cart_status) {
        Cart cart = cartService.setCartStatus(cart_id, cart_status);
        if (cart == null) {
            return new ResponseData(null, 400, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseData(cart, 200, HttpStatus.OK);
        }
    }
}
