package com.met.hrmis.controllers;


import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.entities.EmailConfig;
import java.io.IOException;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author BERNARD
 */
public class LoginController {
    
    private IEntityManagerFactory emf;
    
   

    

    private String username;
    
    private String companyName;

    public String getCompanyName() {
        
        
        
                    
         List<EmailConfig> config= this.emf.getEntityManager().createQuery("select e from EmailConfig e").getResultList();
         
         if (!config.isEmpty()){
             companyName=config.get(0).getCompany();
         } else companyName="NOT DEFINED";
        
    
        
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    

    public void setEmf(IEntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        if (this.username == null){
            return "GUEST";
        }
        else
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String password;

    public LoginController() {
        
    }

    public String doLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }
}
