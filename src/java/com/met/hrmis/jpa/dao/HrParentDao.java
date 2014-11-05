/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */

public abstract class HrParentDao<T> implements IHrGenericDao {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> clazz;

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    

    public HrParentDao(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    @Transactional
    public void delete(Object id) {
        entityManager.remove(find(id));

    }

    @Override
    public void saveOrUpdate(Object entity, Object key) {
        if (key == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public Object find(Object id) {
        return entityManager.find(clazz, id);
    }

    @Override
  
    public Collection<? extends Object> findAll() {
        return entityManager.createQuery("from " + this.clazz.getName()).getResultList();

    }

    @Override
    public void refresh(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public void store(Object entity) {
        entityManager.persist(entity);
        
        //entityManager.refresh(entity);

    }

    @Override
    @Transactional
    public void update(Object entity) {
        entityManager.merge(entity);
    }
}
