/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.ProductGroup;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class ProductGroupController {

    private IGenericDao productGroupDao;
    private ProductGroup currProductGroup;
    private Collection<ProductGroup> productGroupList;

    public IGenericDao getProductGroupDao() {
        return productGroupDao;
    }

    public void setProductGroupDao(IGenericDao productGroupDao) {
        this.productGroupDao = productGroupDao;
    }

    public Collection<ProductGroup> getProductGroupList() {
     
        return (Collection<ProductGroup>) productGroupDao.findAll();
    }


    public ProductGroup getCurrProductGroup() {
        return currProductGroup;
    }

    public void setCurrProductGroup(ProductGroup currProductGroup) {
        this.currProductGroup = currProductGroup;
    }
    
    
    

    public ProductGroupController() {
    }

   

    public void doNew() {



        this.currProductGroup = new ProductGroup();
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }

    public void doSave() {

        try {
            productGroupDao.store(currProductGroup);
            this.currProductGroup = null;
            RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(ProductGroup ag) {
        this.currProductGroup = ag;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.show();");

    }
    
    public void doCancel(){
        this.currProductGroup=null;
        RequestContext.getCurrentInstance().execute("dlgAccountGroup.hide();");
        
    }

    public void doDelete(ProductGroup ag) {
        try {
            
            productGroupDao.delete(ag.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
