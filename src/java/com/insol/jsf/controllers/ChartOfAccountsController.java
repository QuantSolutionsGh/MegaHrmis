/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.GLAccount;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class ChartOfAccountsController {

    private IGenericDao chartOfAccountsDao;
    private Collection<GLAccount> chartOfAccountsList;
    private GLAccount curGLAccount;

    public GLAccount getCurGLAccount() {
        return curGLAccount;
    }

    public void setChartOfAccountsDao(IGenericDao chartOfAccountsDao) {
        this.chartOfAccountsDao = chartOfAccountsDao;
    }

    public void setCurGLAccount(GLAccount curGLAccount) {
        this.curGLAccount = curGLAccount;
    }

    public Collection<GLAccount> getChartOfAccountsList() {
        chartOfAccountsList = (Collection<GLAccount>) chartOfAccountsDao.findAll();
        return chartOfAccountsList;
    }

    public ChartOfAccountsController() {
    }

    public void doNew() {



        this.curGLAccount = new GLAccount();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doSave() {

        try {
            chartOfAccountsDao.store(curGLAccount);
            this.curGLAccount = null;
            RequestContext.getCurrentInstance().execute("dlgGL.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(GLAccount gl) {
        this.curGLAccount = gl;
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doCancel() {
        this.curGLAccount = null;
        RequestContext.getCurrentInstance().execute("dlgGL.hide();");

    }

    public void doDelete(GLAccount gl) {
        try {

            chartOfAccountsDao.delete(gl.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
