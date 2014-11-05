/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.Customer;
import com.insol.spring.dao.ICustAdvSearch;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class CustomerController {

    private IGenericDao genericDao;
    private Customer currCustomer;
    private Collection<Customer> customerList;

    public Customer getCurrCustomer() {
        return currCustomer;
    }

    public void setCurrCustomer(Customer currCustomer) {
        this.currCustomer = currCustomer;
    }

    public Collection<Customer> getCustomerList() {
        return (Collection<Customer>) genericDao.findAll();
    }
    
   
    public void setCustomerList(Collection<Customer> customerList) {
        this.customerList = customerList;
    }
    
    public  Collection<Customer> getCustomerList(String searchString){
        
        ICustAdvSearch custAdvSearch = (ICustAdvSearch)genericDao;
        return custAdvSearch.custFindByName(searchString);
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }
    
    


    
  
    public CustomerController() {
    }

   
    public void doNew() {



        this.currCustomer = new Customer();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }
    
      public void doNew2() {



      //  this.currCustomer = new Customer();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("mytest.show();");

    }

    public void doSave() {

        try {
            
         if (currCustomer.getId()==null){
             genericDao.store(currCustomer);
         } else
            genericDao.update(currCustomer);
         
         
            
    //        genericDao.refresh(currCustomer);
            
          //  this.currCustomer = null;
        //    RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {
            

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(Customer ag) {
        this.currCustomer = ag;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }
    
    public void doCancel(){
        this.currCustomer=null;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
        
    }

    public void doDelete(Customer ag) {
        try {
            
            genericDao.delete(ag.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
