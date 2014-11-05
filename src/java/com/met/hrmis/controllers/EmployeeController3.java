/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.util.IEmployeeSearch;
import java.util.Collection;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author BERNARD
 */
public class EmployeeController3 extends EmployeeController {

    private Collection<Employee> displayItems;
    private IEntityManagerFactory em;
    private String searchText;

    public Collection<Employee> getDisplayItems() {
        if (searchText == null) {
            return super.getObjList();
        } else {
            return displayItems;
        }
    }

    public void setDisplayItems(Collection<Employee> displayItems) {
        this.displayItems = displayItems;
    }

    public IEntityManagerFactory getEm() {
        return em;
    }

    public void setEm(IEntityManagerFactory em) {
        this.em = em;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    
    public void doSearch() {
        
        IHrGenericDao ab =this.getGenericDao();

        

        displayItems = ((IEmployeeSearch)ab).EmployeeSearch(searchText);
    }
}
