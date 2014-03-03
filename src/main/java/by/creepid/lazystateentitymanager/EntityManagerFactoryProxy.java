/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rusakovich
 */
public class EntityManagerFactoryProxy implements EntityManagerFactory {

    protected final EntityManagerFactory delegate;

    protected EntityManagerFactoryProxy(EntityManagerFactory emf) {
        this.delegate = emf;
    }

    @Override
    public EntityManager createEntityManager() {
        return delegate.createEntityManager();
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        return delegate.createEntityManager(map);
    }

    @Override
    public boolean isOpen() {
        return delegate.isOpen();
    }

    @Override
    public void close() {
        delegate.close();
    }
}
