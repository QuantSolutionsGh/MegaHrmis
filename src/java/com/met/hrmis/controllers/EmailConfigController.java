/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.EmailConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author BERNARD
 */
public class EmailConfigController extends ParentController {

    private EmailConfig emailConfig;

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }
    
    

    public EmailConfigController() {
        super(EmailConfig.class);
        
     
        
        
        
    }

    public void dispConfig(ComponentSystemEvent event) {

        if (this.getGenericDao().findAll().isEmpty()) {
            this.emailConfig = new EmailConfig();
            
        } else {
            this.emailConfig = (EmailConfig) this.getGenericDao().getEntityManager().
                    createQuery("select e from EmailConfig e").getResultList().get(0);
                    
       
        
    
        }
    }

    @Override
    public void doSave() {
         try {
             
             this.getGenericDao().update(emailConfig);          
            
           
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes have been saved.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
    
    
    
}
