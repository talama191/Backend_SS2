package com.example.ecommercebackend.Entities.Category;

import jakarta.transaction.Transactional;

public interface ICategoryService extends IGeneralService<Category> {

    @Transactional
    void deleteBulkCategory(Integer[] ids);
}
