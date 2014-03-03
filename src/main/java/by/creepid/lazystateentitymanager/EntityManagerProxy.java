/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

/**
 *
 * @author rusakovich
 */
abstract class EntityManagerProxy implements EntityManager {

    protected final EntityManager delegate;

    public EntityManagerProxy(EntityManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public void persist(Object object) {
        delegate.persist(object);
    }

    @Override
    public <T> T merge(T entity) {
        return delegate.merge(entity);
    }

    @Override
    public void remove(Object object) {
        delegate.remove(object);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return delegate.find(entityClass, primaryKey);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return delegate.getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        delegate.flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        delegate.setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return delegate.getFlushMode();
    }

    @Override
    public void lock(Object object, LockModeType lockModeType) {
        delegate.lock(object, lockModeType);
    }

    @Override
    public void refresh(Object object) {
        delegate.refresh(object);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean contains(Object object) {
        return delegate.contains(object);
    }

    @Override
    public Query createQuery(String string) {
        return delegate.createQuery(string);
    }

    @Override
    public Query createNamedQuery(String string) {
        return delegate.createNamedQuery(string);
    }

    @Override
    public Query createNativeQuery(String string) {
        return delegate.createNativeQuery(string);
    }

    @Override
    public Query createNativeQuery(String string, Class aClass) {
        return delegate.createNativeQuery(string, aClass);
    }

    @Override
    public Query createNativeQuery(String string, String string0) {
        return delegate.createNativeQuery(string, string0);
    }

    @Override
    public void joinTransaction() {
        delegate.joinTransaction();
    }

    @Override
    public Object getDelegate() {
        return delegate.getDelegate();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public boolean isOpen() {
        return delegate.isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return delegate.getTransaction();
    }

}
