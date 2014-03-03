/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rusakovich
 */
public class LazyStatePersistenceManager extends PersistenceManager {

    protected LazyStatePersistenceManager() {
    }

    @Override
    protected EntityManagerFactory createEntityManagerFactory() {
        return new LazyStateEntityManagerFactory(
                super.createEntityManagerFactory());
    }

}
