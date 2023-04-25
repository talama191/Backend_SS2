package com.example.ecommercebackend.Entities.Category;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
