package com.compsys.dao.impl;

import com.compsys.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E,I> {

    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    @Override
    public List<E> findByCriteria(Criterion criterion) {
       //Transaction transaction= getCurrentSession().beginTransaction();
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
       // criteria.add(criterion);
        System.out.println("Here is the criterion"+criteria.toString());
        return criteria.list();
    }
}
