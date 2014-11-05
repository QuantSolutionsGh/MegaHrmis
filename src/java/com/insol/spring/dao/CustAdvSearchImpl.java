/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.Customer;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class CustAdvSearchImpl implements ICustAdvSearch {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Collection<Customer> custFindByName(String custName) {
        // getJpaTemplate().findByNamedQueryAndNamedParams(custName, null)
        Query query = entityManager.createQuery(
                "select c from Customer c where c.custFullName like '%:custName%'");
        query.setParameter("custName", custName);
        return query.getResultList();

    }
}
