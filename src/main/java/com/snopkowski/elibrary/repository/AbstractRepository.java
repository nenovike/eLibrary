package com.snopkowski.elibrary.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractRepository<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    AbstractRepository() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    void persist(T entity) {
        getSession().persist(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

    Criteria createEntityCriteria(Class aClass, String s) {
        return getSession().createCriteria(aClass, s);
    }


}
