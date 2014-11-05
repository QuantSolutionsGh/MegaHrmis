/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import java.util.Collection;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public abstract class ParentDao extends JpaDaoSupport implements IGenericDao {

    private Class entityClass;

    public ParentDao(Class entityClass) {
        this.entityClass = entityClass;

    }

    @Override
    @Transactional
    public void delete(Object id) {


        getJpaTemplate().remove(find(id));
        
       
    }

    @Override
    @Transactional
    public Object find(Object id) {
        return getJpaTemplate().find(entityClass, id);
    }

    @Override
    @Transactional
    public Collection<? extends Object> findAll() {
    
       
      return getJpaTemplate().find("select a from "+entityClass.getSimpleName()+" a");
        

    }

    @Override
    @Transactional
    public void store(Object entity) {
       getJpaTemplate().merge(entity);
   //    getJpaTemplate().flush();
  //     getJpaTemplate().refresh(entity);
        
      
        
       
    }
    
    @Override
    @Transactional
    public void refresh(Object entity){
        getJpaTemplate().refresh(entity);
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
