/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager;

import javax.persistence.EntityManager;


/**
 *
 * @author rusakovich
 */
public class LazyStateEntityManager extends EntityManagerProxy {

    private LazyStateExecutor executor;

    public LazyStateEntityManager(EntityManager delegate) {
        super(delegate);
    }

    public void setLazyStateExecutor(LazyStateExecutor executor) {
        this.executor = executor;
    }

    public LazyStateExecutor getLazyCloseExecutor() {
        return executor;
    }

    @Override
    public void close() {
    }

    protected void lazyClose() {
        super.close();

        if (executor != null) {
            executor.lazyClose();
        }
    }
}
