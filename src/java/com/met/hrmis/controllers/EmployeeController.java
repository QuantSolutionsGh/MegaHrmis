/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.util.FacesUtils;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class EmployeeController extends ParentController {

    public EmployeeController() {
        super(Employee.class);
    }

    public void dispEmpMemberships(String id) {
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("empXmembership",options,null);
    }
    
    public void dispEmpFamily(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("empXfamily",options,null);
    }
    
   
    
    public void dispTrainingAttended(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("employeeXtraining",options,null);
        
    }
    
        public void dispLeaveDetails(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("employeeXleavedetails",options,null);
        
    }
    
    public void dispEmpSkills(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("empXskills",options,null);
    }
    
    
    public void dispEmpHistory(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("emphistory",options,null);
    }
    
    public void dispEmpEducation(String id){
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("empXeducation.xhtml",options,null);
    }
}
