package com.example.ecommercebackend.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where product.category_id=?1", nativeQuery = true)
    public Product getProductsByCategory(int category);

    @Transactional
    @Modifying
//    @Query(value = "delete from Product p  where p.id=?1")
    @Query("delete from Product p where p.id in(:integers)")
    void deleteAllIds(List<Integer> integers);
}
