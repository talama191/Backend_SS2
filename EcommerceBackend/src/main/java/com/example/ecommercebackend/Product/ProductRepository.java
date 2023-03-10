package com.example.ecommercebackend.Product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where product.category_id=?1", nativeQuery = true)
    public Product getProductsByCategory(int category);

    @Transactional
    @Modifying
    @Query(value = "delete from Product p  where p.id=?1")
    public int deleteProductByID(int id);
}
