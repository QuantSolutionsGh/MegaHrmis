/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.util.IEmployeeSearch;
import java.util.Collection;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EmployeeDao2 extends EmployeeDao implements IEmployeeSearch{

    @Override
    @Transactional
    public Collection<Employee> EmployeeSearch(String queryString) {
        
         QueryBuilder queryBuilder;
        FullTextEntityManager fullTextEntityManager;
        fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.getEntityManager());
        
        queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
        
         org.apache.lucene.search.Query query = queryBuilder.keyword().wildcard().onField("empId").andField("firstName").andField("lastName").matching(queryString).createQuery();


       // org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("empId", "firstName", "lastName").matching(queryString).createQuery();

        FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Employee.class);


        return persistenceQuery.getResultList();
        
        
    }
    
}
