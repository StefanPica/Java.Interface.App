package com.example.javainterf.Domain;

public interface IEntityFactory<T extends IEntity> {

    String entityToString(T entity);

    T StringToEntity(String line);
}
