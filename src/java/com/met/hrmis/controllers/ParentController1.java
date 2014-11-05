/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public abstract class ParentController1<T> implements IGenericController {

    private IHrGenericDao genericDao;
    private String dlgWindow;
    private String dlgTitle;
    //  protected abstract IHrGenericDao getDao();
    //  protected abstract Class<T> getNewElement();
    //  protected abstract String getDlgWindow();
    private Class<T> entityClass;
    private T currentElement;

    public String getDlgTitle() {
        return dlgTitle;
    }

    public void setDlgTitle(String dlgTitle) {
        this.dlgTitle = dlgTitle;
    }

    public String getDlgWindow() {
        return dlgWindow;
    }

    public T getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(T currentElement) {
        this.currentElement = currentElement;
    }

    public IHrGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IHrGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public void setDlgWindow(String dlgWindow) {
        this.dlgWindow = dlgWindow;
    }

    public ParentController1(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void doCancel() {

        this.currentElement = null;
        RequestContext.getCurrentInstance().execute(this.getDlgWindow() + ".hide();");

    }

    @Override
    public void doDelete(Object obj) {

        //could also use getDao.delete(obj)  --here obj is the primary key i want to delete
        //   a.getClass().getField("id").
        //if i am intrested in just the id
        //   String id = (String) entityClass.getField("id").get(obj);
        // obj.getClass().getField("id").get(obj)

        try {

            genericDao.delete(obj);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }



    }

    @Override
    public void doEdit(Object obj) {
        this.currentElement = (T) obj;

        this.dlgTitle = "Edit Entry";

        RequestContext.getCurrentInstance().execute(this.getDlgWindow() + ".show();");
    }

    @Override
    public void doNew() {
        try {
            this.currentElement = (T) this.entityClass.newInstance();
            this.dlgTitle = "New Entry";

            //this.curRisk.setRiskClass("");
            // RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

            RequestContext.getCurrentInstance().execute(this.getDlgWindow() + ".show();");
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ParentController1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void doSave() {
        try {

           
            
            genericDao.update(currentElement);  //calls merege and inserts if it

            this.currentElement = null;

            RequestContext.getCurrentInstance().execute(this.getDlgWindow() + ".hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    @Override
    public Collection<? extends Object> getObjList() {

        return (Collection<T>) genericDao.findAll();
    }
}
