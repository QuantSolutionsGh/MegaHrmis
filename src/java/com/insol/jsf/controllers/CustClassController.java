/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.AccountGroup;
import com.insol.jpa.entities.CustomerClass;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class CustClassController {

    private IGenericDao custClassDao;
    private CustomerClass currCustomerClass;
    private Collection<CustomerClass> customerClassList;

    public CustomerClass getCurrCustomerClass() {
        return currCustomerClass;
    }

    public void setCurrCustomerClass(CustomerClass currCustomerClass) {
        this.currCustomerClass = currCustomerClass;
    }

    public IGenericDao getCustClassDao() {
        return custClassDao;
    }

    public void setCustClassDao(IGenericDao custClassDao) {
        this.custClassDao = custClassDao;
    }

    public Collection<CustomerClass> getCustomerClassList() {
        return (Collection<CustomerClass>) custClassDao.findAll();
    }

    public void setCustomerClassList(Collection<CustomerClass> customerClassList) {
        this.customerClassList = customerClassList;
    }
    
    
  
    public CustClassController() {
    }

   
    public void doNew() {



        this.currCustomerClass = new CustomerClass();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }

    public void doSave() {

        try {
            custClassDao.store(currCustomerClass);
            this.currCustomerClass = null;
            RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {
            

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(CustomerClass ag) {
        this.currCustomerClass = ag;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }
    
    public void doCancel(){
        this.currCustomerClass=null;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
        
    }

    public void doDelete(CustomerClass ag) {
        try {
            
            custClassDao.delete(ag.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
