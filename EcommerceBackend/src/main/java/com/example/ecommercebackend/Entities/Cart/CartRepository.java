package com.example.ecommercebackend.Entities.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query(value="select * from cart where cart.user_id =?1",nativeQuery = true)
    public List<Cart> getCartByUserID(int user_id);

    @Query(value="select * from cart where cart.cart_id=?1",nativeQuery = true)
    public Cart getCartByID(int cart_id);
}
