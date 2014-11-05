/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import java.util.Collection;
import javax.persistence.EntityManager;

/**
 *
 * @author BERNARD
 */
public interface IHrGenericDao {

    

    public void store(Object entity);

    public void saveOrUpdate(Object entity, Object key);

    public void update(Object entity);

    public void delete(Object id);

    public Object find(Object id);

    public void refresh(Object entity);

    public Collection<? extends Object> findAll();
    
    public EntityManager getEntityManager();
}
