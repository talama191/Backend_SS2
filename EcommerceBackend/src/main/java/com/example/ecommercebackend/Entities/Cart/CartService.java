package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.User.User;
import com.example.ecommercebackend.Entities.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    public List<Cart> getCartsByUserID(int user_id) {
        return cartRepository.getCartByUserID(user_id);
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
}
