package com.haulmont.testtask.repository;

import java.util.List;

public interface CrudRepository<T> {

    void save(T obj);

    void delete(T obj);

    void update(T obj);

    T getById(Long id);

    List<T> getAll();

}
