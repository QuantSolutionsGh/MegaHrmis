/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.GLAccount;
import com.insol.jpa.entities.TransactionType;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class TransactionTypeController {

    private IGenericDao transactionTypeDao;
    private Collection<TransactionType> transactionTypeList;
    private TransactionType currTransactionType;

    public void setCurrTransactionType(TransactionType currTransactionType) {
        this.currTransactionType = currTransactionType;
    }

    public void setTransactionTypeList(Collection<TransactionType> transactionTypeList) {
        this.transactionTypeList = transactionTypeList;
    }

    public void setTransactionTypeDao(IGenericDao transactionTypeDao) {
        this.transactionTypeDao = transactionTypeDao;
    }

    public TransactionType getCurrTransactionType() {
        return currTransactionType;
    }

    public Collection<TransactionType> getTransactionTypeList() {
        transactionTypeList = (Collection<TransactionType>) transactionTypeDao.findAll();
        return transactionTypeList;
    }


    public TransactionTypeController() {
    }

    public void doNew() {



        this.currTransactionType = new TransactionType();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doSave() {

        try {
            transactionTypeDao.store(currTransactionType);
            this.currTransactionType = null;
            RequestContext.getCurrentInstance().execute("dlgGL.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(TransactionType gl) {
        this.currTransactionType = gl;
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doCancel() {
        this.currTransactionType = null;
        RequestContext.getCurrentInstance().execute("dlgGL.hide();");

    }

    public void doDelete(TransactionType gl) {
        try {

            transactionTypeDao.delete(gl.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
