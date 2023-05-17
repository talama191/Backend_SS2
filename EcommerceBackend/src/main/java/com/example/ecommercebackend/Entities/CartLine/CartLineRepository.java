package com.example.ecommercebackend.Entities.CartLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartLineRepository extends JpaRepository<CartLine, CartLineId> {

    @Query(value = "select * from cart_line where cart_line.cart_id=?1", nativeQuery = true)
    public List<CartLine> getCartLinesByCart_id(int cart_id);

    @Query(value = "select * from cart_line where cart_line.cart_id=?1  and cart_line.product_id=?2", nativeQuery = true)
    public CartLine getCartLine(int cart_id, int product_id);

    @Transactional
    @Modifying
    @Query("delete from CartLine c  where c.cart_id=?1")
    public void deleteCartLinesByCartID(int cart_id);
}
