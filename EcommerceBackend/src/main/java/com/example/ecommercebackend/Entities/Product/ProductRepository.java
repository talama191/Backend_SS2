package com.example.ecommercebackend.Entities.Product;

import com.example.ecommercebackend.Entities.SearchFilter.ProductSearchFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product p where p.category_id=?1", nativeQuery = true)
    public List<Product> getProductsByCategory(int category);

    @Transactional
    @Modifying
//    @Query(value = "delete from Product p  where p.id=?1")
    @Query("delete from Product p where p.id in(:integers)")
    void deleteAllIds(List<Integer> integers);

    @Query("select p from Product p where p.name like (:#{#filter.getKeyword()}) and p.category.id in (:#{#filter.category_ids} ) and p.brand.id in(:#{#filter.category_ids} ) ")
    List<Product> findAllByName(@Param("filter") ProductSearchFilter filter, Pageable pageable);

//    @Query(value ="select p from Product p where p.id in(:#{filter.keyword})" +
//            "and p.category.id in (:#{filter.category_ids}) " +
//            "and p.brand.id in (:#{filter.brand_ids}) " +
//            "limit ?#{filter.perPage}" )
//    public List<Product> searchProduct(@Param("filter") ProductSearchFilter filter)
}
