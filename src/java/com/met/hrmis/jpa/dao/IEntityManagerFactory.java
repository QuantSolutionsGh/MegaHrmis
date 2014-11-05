/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author BERNARD
 */
public interface IEntityManagerFactory {
    
    public EntityManager getEntityManager();
    
}
