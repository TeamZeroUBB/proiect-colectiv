package com.teamZero.app.core.dao;



import java.util.List;

public interface Dao<ID,E> {
    List<E> getAll();
    void add(E entity);
    void delete(E entity);
    void update(E entity);
    E getById(ID id);
    E deleteById(ID id);
}
