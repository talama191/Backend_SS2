package com.example.ecommercebackend.Entities.Cart;

import com.example.ecommercebackend.Entities.User.User;
import jakarta.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user_id;

    public User getUser() {
        return user_id;
    }

    public void setUser(User user_id) {
        this.user_id = user_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_id() {
        return cart_id;
    }
}
