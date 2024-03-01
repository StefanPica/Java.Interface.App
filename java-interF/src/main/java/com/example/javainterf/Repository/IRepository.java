package com.example.javainterf.Repository;

public interface IRepository<T> {

    void add(T entity);

    void delete(Integer id);

    void update(Integer id, T entity);

    T findById(Integer id);

    Iterable<T> getAll();
}