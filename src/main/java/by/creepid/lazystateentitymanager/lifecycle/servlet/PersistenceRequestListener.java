/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.lazystateentitymanager.lifecycle.servlet;

import by.creepid.lazystateentitymanager.LazyStatePersistenceManager;
import by.creepid.lazystateentitymanager.PersistenceManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author rusakovich
 */
public class PersistenceRequestListener implements ServletContextListener, ServletRequestListener {

    private static final String PU_PARAMETER_NAME = "by.creepid.persistence.PERSISTENCE_UNIT";

    public void contextInitialized(ServletContextEvent evt) {

        PersistenceManager pm = PersistenceManager.getInstance();

        if (pm instanceof LazyStatePersistenceManager) {
            String pu = evt.getServletContext().getInitParameter(PU_PARAMETER_NAME);
            if (pu != null && pu.isEmpty()) {

                PersistenceManager.setUnitName(pu);
            }
        }

    }

    public void contextDestroyed(ServletContextEvent evt) {
        PersistenceManager.getInstance().closeEntityManagerFactory();
    }

    /**
     * ### Method from ServletRequestListener ###
     *
     * The request is about to come into scope of the web application.
     */
    public void requestInitialized(ServletRequestEvent evt) {

    }

    /**
     * ### Method from ServletRequestListener ###
     *
     * The request is about to go out of scope of the web application.
     */
    public void requestDestroyed(ServletRequestEvent evt) {
        PersistenceManager.getInstance().lazyClose();
    }
}
