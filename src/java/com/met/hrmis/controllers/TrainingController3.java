/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;
import com.met.hrmis.jpa.entities.Training;
import com.met.hrmis.jpa.entities.TrainingXEmployees;
import com.met.hrmis.util.IEmployeeMethods;
import com.met.hrmis.util.ITrainingSearch;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author BERNARD
 */
public class TrainingController3 extends TrainingController{
    
    private Collection<Training> displayItems;
    private IEntityManagerFactory em;
    private String searchText;

    public Collection<Training> getDisplayItems() {
        if (searchText == null) {
            return super.getObjList();
        } else {
            return displayItems;
        }
    }

    public void setDisplayItems(Collection<Training> displayItems) {
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

        

        displayItems = ((ITrainingSearch)ab).search(searchText);
    }
    
    public List<TrainingXEmployees> getTrainingParticipants(Training t){
        
       Query query = this.em.getEntityManager().createQuery("select e from TrainingXEmployees e where e.training = :training");
       query.setParameter("training",t);
       return query.getResultList();
        

               
        
    }
    
}
