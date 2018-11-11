package com.teamZero.app.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
@Transactional
public abstract class AbstractDao<T, PK extends Serializable> {

    private final Class<T> persistentClass;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getById(PK key) {
        return getSession().get(persistentClass, key);
    }

    public void add(T t) {
         getSession().persist(t);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public void update(T t) {
        getSession().update(t);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String hql = "FROM " + persistentClass.getSimpleName();
        return getSession().createQuery(hql).getResultList();
    }

    public T deleteById(PK key) {
        T t = getById(key);
        if (t != null) {
            delete(t);
        }
        return t;
    }

}
