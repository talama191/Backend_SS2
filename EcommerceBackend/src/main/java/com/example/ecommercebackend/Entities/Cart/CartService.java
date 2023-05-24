package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.CartLine.CartLine;
import com.example.ecommercebackend.Entities.CartLine.CartLineRepository;
import com.example.ecommercebackend.Entities.User.User;
import com.example.ecommercebackend.Entities.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartLineRepository cartLineRepository;

    public List<Cart> getCartsByUserID(int user_id) {
        return cartRepository.getCartByUserID(user_id);
    }
    public List<Cart > getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart getActiveCartByUserID(int user_id) {
        List<Cart> carts = cartRepository.getCartByUserID(user_id);
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCart_status() == 0) {
                return carts.get(i);
            }
        }
        return null;
    }

    public CartLine modifyProductToCurrentCart(int user_id, int product_id, int quantity) {
        int cart_id = getActiveCartByUserID(user_id).getCart_id();
        CartLine cartLine = cartLineRepository.getCartLine(cart_id, product_id);
        if (cartLine == null) {
            if (quantity <= 0) {
                return null;
            }
            cartLine = new CartLine();
            cartLine.setQuantity(quantity);
            cartLine.setProduct_id(product_id);
            cartLine.setCart_id(cart_id);
        } else {
            cartLine.setQuantity(quantity);
            if (quantity <= 0) {
                cartLineRepository.delete(cartLine);
                return null;
            }
        }
        return cartLineRepository.save(cartLine);
    }

    public CartLine addProductsToCurrentCart(int user_id, int product_id, int quantity) {
        if (quantity <= 0) {
            return null;
        }
        int cart_id = getActiveCartByUserID(user_id).getCart_id();
        CartLine cartLine = cartLineRepository.getCartLine(cart_id, product_id);
        if (cartLine == null) {
            cartLine = new CartLine();
            cartLine.setQuantity(quantity);
            cartLine.setProduct_id(product_id);
            cartLine.setCart_id(cart_id);
        } else {
            cartLine.setQuantity(cartLine.getQuantity() + quantity);
        }
        return cartLineRepository.save(cartLine);
    }

    public Cart getCartByCartID(int cart_id) {
        return cartRepository.getCartByID(cart_id);
    }

    public Cart createNewCart(int user_id) {
        Cart new_cart = new Cart();
        User user = userRepository.getUserByUserID(user_id);
        new_cart.setUser(user);
        return cartRepository.save(new_cart);
    }

    public Cart setCartStatus(int cart_id, int status) {
        Cart cart = cartRepository.getCartByID(cart_id);
        if (cart != null) {
            if (cart.getCart_status() > status) {
                return null;
            }
            Date date = new Date();
            if (status == 1) {
                cart.setOrdered_at(new Timestamp(date.getTime()));
            } else {
                cart.setCompleted_at(new Timestamp(date.getTime()));
            }
            cart.setCart_status(status);
            return cartRepository.save(cart);
        }
        return null;
    }

    public Cart OrderWithoutPay(Cart cart) {
        Cart dbCart = cartRepository.getCartByID(cart.getCart_id());
        if (dbCart == null) {
            return null;
        }
        Date date = new Date();

        dbCart.setOrdered_at(new Timestamp(date.getTime()));
        dbCart.setHasPaid(false);
        dbCart.setPhoneNumber(cart.getPhoneNumber());
        dbCart.setAddress(cart.getAddress());
        dbCart.setCart_status(1);
        cartRepository.save(dbCart);

        return dbCart;
    }
}
