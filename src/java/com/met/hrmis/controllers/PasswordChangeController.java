/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Users;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author BERNARD
 */
public class PasswordChangeController extends UserController {

    public Users currentUser;
    public String oldPassword;
    public String newPassword;
    public String newPassword2;

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
    
    

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /*
     * business logic should never be place in getters. Nonetheless it is here
     * because this part is not too critcal
     */
    private Users getCurrentUser() {
        
        Query myQuery = this.getGenericDao().getEntityManager().createQuery("select e from Users e where e.userName= :username");

        myQuery.setParameter("username", loginController.getUsername());


        this.currentUser = (Users) myQuery.getSingleResult();
     //   this.currentUser.setPassword(loginController.getPassword());
        return this.currentUser;

    }

  //  public void setCurrentUser(Users currentUser) {
  //      this.currentUser = currentUser;
  //  }

    @Override
    public void doSave() {

        try {
            this.currentUser =getCurrentUser();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            
            if (!passwordEncoder.matches(oldPassword, this.currentUser.getPassword())){
                throw new Exception("Old password incorrect.");
            }
            
            if (newPassword == null ? newPassword2 != null : !newPassword.equals(newPassword2)){
                throw new Exception("Please confirm new password.");
            }
            
        
            this.currentUser.setPassword(this.newPassword);
            this.getGenericDao().update(currentUser);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));

        }
    }
}
