/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.EmployeeXEducation;
import com.met.hrmis.jpa.entities.EmployeeXMembership;
import com.met.hrmis.util.FacesUtils;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author BERNARD
 */
public class EmployeeXEducationController extends ParentController{
    
      private  IHrGenericDao employeeDao;
    private String activeTab = "showAll";

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public void setEmployeeDao(IHrGenericDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    
    
    
    public EmployeeXEducationController(){
        super(EmployeeXEducation.class);
    }
    
      @Override
    public void doCancel() {
        activeTab = "showAll";
        this.setCurrentElement(null);

        //super.doCancel();


    }

    @Override
    public void doDelete(Object obj) {
        try {
            activeTab = "showAll";
            this.getGenericDao().delete(obj);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Item has been deleted.", null));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), null));


        }
    }

    @Override
    public  Collection getObjList() {

        
        try {
            
            String myParam=(String) FacesUtils.getSessionMapValue("id");

            Employee emp = (Employee) employeeDao.find(myParam);
          
           // emp.getMembershipCollection().size();
            
            return emp.getEducationCollection();
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), null));
            return null;
        }
        //   return emp.getMembershipCollection();
      //  return null;

    }

    @Override
    public void doEdit(Object obj) {
        // super.doEdit(obj);
        this.setCurrentElement(obj);
        this.setDlgTitle("Edit Entry");
        activeTab = "showEdit";

    }

    @Override
    public void doNew() {
        activeTab = "showEdit";
        this.setDlgTitle("New Entry");
        this.setCurrentElement(new EmployeeXEducation());

    }

    @Override
    public void doSave() {
        // super.doSave();
        activeTab = "showAll"; 
        String myParam=(String) FacesUtils.getSessionMapValue("id");
        Employee emp = (Employee) employeeDao.find(myParam);
        
        EmployeeXEducation em = (EmployeeXEducation)this.getCurrentElement();
        em.setEmployee(emp);
        
         

        this.getGenericDao().update(this.getCurrentElement());
        this.setCurrentElement(null);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry has been saved successfully.", null));


    }
    
}
