package com.example.ecommercebackend.Entities.CartLine;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "cart_line")
@IdClass(CartLineId.class)
public class CartLine {
    private int quantity;
    private Integer unit_price;

    @NonNull
    @Id
    private int cart_id;
    @NonNull
    @Id
    private int product_id;

    public CartLine(int cart_id, int product_id) {

        this.cart_id = cart_id;
        this.product_id = product_id;
    }

    public CartLine() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
