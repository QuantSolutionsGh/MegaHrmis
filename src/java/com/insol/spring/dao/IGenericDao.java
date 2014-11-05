/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import java.util.Collection;

/**
 *
 * @author BERNARD
 */
public interface IGenericDao {

    public void store(Object entity);

    public void update(Object entity);

    public void delete(Object id);

    public Object find(Object id);

    public void refresh(Object entity);

    //public void update(AccountGroup accountGroup);
    public Collection<? extends Object> findAll();
}
