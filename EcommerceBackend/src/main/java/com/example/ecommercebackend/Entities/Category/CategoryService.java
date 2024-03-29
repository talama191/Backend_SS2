package com.example.ecommercebackend.Entities.Category;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Category category) {
        if (categoryRepository.existsById((long)category.getId())) {
            return categoryRepository.saveAndFlush(category);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteBulkCategory(Integer[] ids) {
        categoryRepository.deleteCategoriesByIds(Arrays.asList(ids));
    }

}
