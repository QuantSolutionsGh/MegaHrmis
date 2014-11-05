/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.TransactionType;
import com.insol.jpa.entities.TransactionTypeXProductXGlAccount;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class TransactionTypeXProductXGlAccountController {

    private IGenericDao transGlSetup;
    private Collection<TransactionTypeXProductXGlAccount> transGlSetupList;
    private TransactionTypeXProductXGlAccount currTransGlSetup;

    public TransactionTypeXProductXGlAccount getCurrTransGlSetup() {
        return currTransGlSetup;
    }

    public void setCurrTransGlSetup(TransactionTypeXProductXGlAccount currTransGlSetup) {
        this.currTransGlSetup = currTransGlSetup;
    }

    public IGenericDao getTransGlSetup() {
        return transGlSetup;
    }

    public void setTransGlSetup(IGenericDao transGlSetup) {
        this.transGlSetup = transGlSetup;
    }

    public Collection<TransactionTypeXProductXGlAccount> getTransGlSetupList() {
        return (Collection<TransactionTypeXProductXGlAccount>) transGlSetup.findAll();
    }

 
    
    

   

    public TransactionTypeXProductXGlAccountController() {
    }

    public void doNew() {



        this.currTransGlSetup = new TransactionTypeXProductXGlAccount();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doSave() {

        try {
            transGlSetup.store(currTransGlSetup);
            this.currTransGlSetup = null;
            RequestContext.getCurrentInstance().execute("dlgGL.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(TransactionTypeXProductXGlAccount gl) {
        this.currTransGlSetup = gl;
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doCancel() {
        this.currTransGlSetup = null;
        RequestContext.getCurrentInstance().execute("dlgGL.hide();");

    }

    public void doDelete(TransactionTypeXProductXGlAccount gl) {
        try {

            transGlSetup.delete(gl.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
