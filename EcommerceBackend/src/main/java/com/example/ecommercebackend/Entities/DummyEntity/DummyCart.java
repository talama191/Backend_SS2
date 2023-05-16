package com.example.ecommercebackend.Entities.DummyEntity;

import com.example.ecommercebackend.Entities.CartLine.CartLine;

import java.util.List;

public class DummyCart {
    private int user_id;
    private List<CartLine> cartLineList;

    public DummyCart(int user_id, List<CartLine> cartLineList) {
        this.user_id = user_id;
        this.cartLineList = cartLineList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<CartLine> getCartLineList() {
        return cartLineList;
    }

    public void setCartLineList(List<CartLine> cartLineList) {
        this.cartLineList = cartLineList;
    }
}
