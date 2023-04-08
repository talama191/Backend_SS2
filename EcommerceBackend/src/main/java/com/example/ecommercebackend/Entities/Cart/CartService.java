package com.example.ecommercebackend.Entities.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public Cart getCartByUserID(int user_id) {
        return cartRepository.getCartByUserID(user_id);
    }
    public Cart createNewCart(int user_id){
        Cart new_cart=new Cart();
        new_cart.setUser_id(user_id);
        return cartRepository.save(new_cart);
    }
}
