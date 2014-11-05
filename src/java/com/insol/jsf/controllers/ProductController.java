/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jsf.controllers;

import com.insol.jpa.entities.Product;
import com.insol.spring.dao.IGenericDao;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class ProductController {

    private IGenericDao productDao;
    private Collection<Product> productList;
    private Product curProduct;
  
    
    

    
    public void setCurProduct(Product curProduct) {
        this.curProduct = curProduct;
    }

    public void setProductDao(IGenericDao productDao) {
        this.productDao = productDao;
    }

    public Collection<Product> getProductList() {
        productList = (Collection<Product>) productDao.findAll();
        return productList;
    }

    public Product getCurProduct() {
        return curProduct;
    }

    public void setProductList(Collection<Product> productList) {
        this.productList = productList;
    }


  

    public ProductController() {
    }

    public void doNew() {



        this.curProduct = new Product();
        
        //this.curRisk.setRiskClass("");
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doSave() {

        try {
            productDao.store(curProduct);
            this.curProduct = null;
            RequestContext.getCurrentInstance().execute("dlgGL.hide();");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }

    public void doEdit(Product gl) {
        this.curProduct = gl;
     
        RequestContext.getCurrentInstance().execute("dlgGL.show();");

    }

    public void doCancel() {
        this.curProduct = null;
        RequestContext.getCurrentInstance().execute("dlgGL.hide();");

    }

    public void doDelete(Product gl) {
        try {

            productDao.delete(gl.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
