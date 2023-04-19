package com.example.ecommercebackend.Entities.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query(value="select * from cart where cart.user_id =?1",nativeQuery = true)
    public Cart getCartByUserID(int user_id);

}
