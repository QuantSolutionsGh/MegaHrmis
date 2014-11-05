/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.AccountGroup;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class AccountGroupController {

    private IGenericDao accountGroupDao;
    private AccountGroup currAcountGroup;
    private Collection<AccountGroup> accountGroupList;
    
    
    public void setAccountGroupDao(IGenericDao accountGroupDao) {
        this.accountGroupDao = accountGroupDao;
        
        
    }  
    
    
    

    

    public Collection<AccountGroup> getAccountGroupList() {
        return (Collection<AccountGroup>) accountGroupDao.findAll();
    }

    public void setAccountGroupList(Collection<AccountGroup> accountGroupList) {
        this.accountGroupList = accountGroupList;
    }

    public AccountGroupController() {
    }

    public AccountGroup getCurrAcountGroup() {
        return currAcountGroup;
    }

    public void setCurrAcountGroup(AccountGroup currAcountGroup) {
        this.currAcountGroup = currAcountGroup;
    }

    public void doNew() {



        this.currAcountGroup = new AccountGroup();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }

    public void doSave() {

        try {
            accountGroupDao.store(currAcountGroup);
            this.currAcountGroup = null;
            RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(AccountGroup ag) {
        this.currAcountGroup = ag;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }
    
    public void doCancel(){
        this.currAcountGroup=null;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
        
    }

    public void doDelete(AccountGroup ag) {
        try {
            
            accountGroupDao.delete(ag.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
