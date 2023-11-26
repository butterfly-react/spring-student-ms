package com.sha.student.query;

import java.util.List;

public interface GenericDAO<T> {

    void save(T entity);

    T findById(int id, Class<T> entityType);

    void deleteById(int id, Class<T> entityType);

    List<T> findAll(Class<T> entityType);

    void update(T entity);
}
