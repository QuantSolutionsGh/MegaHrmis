/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public abstract class JpaParentDao<T> implements IGenericDao{
    @PersistenceContext
    private EntityManager entityManager;
    
    private Class<T> clazz;
    
    public JpaParentDao(Class<T> clazzToSet){
        this.clazz= clazzToSet;
    }
    

    @Override
    @Transactional
    public void delete(Object id) {
        entityManager.remove(find(id));
        
    }

    @Override
    @Transactional
    public Object find(Object id) {
       return entityManager.find(clazz,id);
    }

    @Override
    @Transactional
    public Collection<? extends Object> findAll() {
        return entityManager.createQuery("from "+this.clazz.getName()).getResultList();
        
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
