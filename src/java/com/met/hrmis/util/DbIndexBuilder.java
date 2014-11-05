/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author BERNARD
 */
public class DbIndexBuilder implements IEndexBuilder {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void buildIndex() {
         FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            // This will ensure that index for already inserted data is created.
            fullTextEntityManager.createIndexer().startAndWait();}
        catch(Exception e        ){
            
        }
        
    }
}
