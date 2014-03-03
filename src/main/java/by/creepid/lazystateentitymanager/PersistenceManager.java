/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rusakovich
 */
public class PersistenceManager {

    protected static final String DEFAULT_PERSISTENCE_UNIT = "DefaultPU";
    private static String unitName = DEFAULT_PERSISTENCE_UNIT;

    private static volatile PersistenceManager singleton;

    //EntityManagerFactory class is thread-safe and heavy
    protected volatile static EntityManagerFactory emf;

    public static PersistenceManager getInstance() {
        PersistenceManager localInstance = singleton;
        if (localInstance == null) {
            synchronized (PersistenceManager.class) {
                localInstance = singleton;
                if (localInstance == null) {
                    singleton = localInstance = new LazyStatePersistenceManager();
                }
            }
        }
        return localInstance;
    }

    PersistenceManager() {
        if (unitName == null) {
            throw new IllegalStateException("Unit name must be set!");
        }
    }

    public synchronized EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            emf = createEntityManagerFactory();
        }

        return emf;
    }

    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }

    protected EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(unitName);
    }

    public void lazyClose() {
        if (singleton instanceof LazyStatePersistenceManager) {
            LazyStateEntityManagerFactory lazyEMF = (LazyStateEntityManagerFactory) emf;
            if (lazyEMF.isOpen()) {
                LazyStateEntityManager lazyEM = lazyEMF.getEntityManager();

                if (lazyEM != null && lazyEM.isOpen()) {
                    lazyEM.lazyClose();
                }
            }

        }
    }

    public static String getUnitName() {
        return unitName;
    }

    public static void setUnitName(String unitName) {
        PersistenceManager.unitName = unitName;
    }

}
