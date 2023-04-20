package com.example.ecommercebackend.Entities.Category;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Delete all category with ids specified in {@code ids} parameter
     *
     * @param ids List of category ids
     */
    @Modifying
    @Query("delete from Category u where u.id in ?1")
    void deleteCategoriesByIds(List<Integer> ids);
}
