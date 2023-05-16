package com.example.ecommercebackend.Entities.CartLine;


import com.example.ecommercebackend.Entities.Cart.Cart;
import com.example.ecommercebackend.Entities.Product.Product;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "cart_line")
@IdClass(CartLineId.class)
public class CartLine {
    @Column(columnDefinition = "integer default 0")
    private int quantity;
    @Column(columnDefinition = "integer default 0")
    private Integer unit_price;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
//    private Cart cart;
    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @NonNull
    @Id
    @Column(insertable=false, updatable=false)
    private int cart_id;
    @NonNull
    @Id
    @Column(insertable=false, updatable=false)
    private int product_id;
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

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
