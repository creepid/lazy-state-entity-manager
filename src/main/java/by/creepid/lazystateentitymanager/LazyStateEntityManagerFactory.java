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
public class LazyStateEntityManagerFactory extends EntityManagerFactoryProxy
        implements LazyStateExecutor {

    private final ThreadLocal<LazyStateEntityManager> threadLocal;

    protected LazyStateEntityManagerFactory(EntityManagerFactory emf) {
        super(emf);

        this.threadLocal = new ThreadLocal<LazyStateEntityManager>();
    }

    @Override
    public EntityManager createEntityManager(Map map) {

        LazyStateEntityManager em = threadLocal.get();
        if (em == null) {
            em = new LazyStateEntityManager(super.createEntityManager(map));
            createEntityManager(em);
        }

        return em;
    }

    @Override
    public EntityManager createEntityManager() {

        LazyStateEntityManager em = threadLocal.get();
        if (em == null) {
            em = new LazyStateEntityManager(super.createEntityManager());
            createEntityManager(em);
        }

        return em;
    }

    private void createEntityManager(LazyStateEntityManager em) {
        threadLocal.set(em);
        em.setLazyStateExecutor(this);
    }

    protected LazyStateEntityManager getEntityManager() {
        return threadLocal.get();
    }

    @Override
    public void lazyClose() {
        threadLocal.set(null);
    }
}
