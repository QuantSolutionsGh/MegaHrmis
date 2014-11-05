/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.TrainingXEmployees;
import com.met.hrmis.util.FacesUtils;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author BERNARD
 */
public class EmployeeXTrainingController  extends EmployeeController{

    @Override
    public Collection<TrainingXEmployees> getObjList() {
        try {
            
            String myParam=(String) FacesUtils.getSessionMapValue("id");

            Employee employee = (Employee) this.getGenericDao().find(myParam);
          
           // emp.getMembershipCollection().size();
            return employee.getEmpTrainingCollection();
            
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), null));
            return null;
        }
    }
    
    
    
}
