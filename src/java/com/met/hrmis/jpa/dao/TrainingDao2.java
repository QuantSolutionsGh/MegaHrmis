/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Training;
import com.met.hrmis.util.ITrainingSearch;
import java.util.Collection;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class TrainingDao2 extends TrainingDao implements ITrainingSearch {

    @Override
    @Transactional
    public Collection<Training> search(String queryString) {
        QueryBuilder queryBuilder;
        FullTextEntityManager fullTextEntityManager;
        fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.getEntityManager());
        
        queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Training.class).get();
        
         org.apache.lucene.search.Query query = queryBuilder.keyword().wildcard().onField("venue").andField("facilitator").andField("courseTitle").matching(queryString).createQuery();


       // org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("empId", "firstName", "lastName").matching(queryString).createQuery();

        FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Training.class);


        return persistenceQuery.getResultList();
    }
    
}
