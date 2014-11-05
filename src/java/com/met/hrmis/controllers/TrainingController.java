/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Training;
import com.met.hrmis.util.FacesUtils;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;

/**
 *
 * @author BERNARD
 */
public class TrainingController extends ParentController{
    
    public TrainingController(){
        super(Training.class);
    }
    
    public void dispCostItems(String id){
        
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("trainingXcost",options,null);
        
    }
    
      public void dispTrainingParticipants(String id) {
        Map<String,Object> options = new HashMap<>();
              

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        /* we are storing the id in the session*/
        FacesUtils.setSessionMapValue("id", id);
        
        

        RequestContext.getCurrentInstance().openDialog("trainingXemployees",options,null);
    }
    
}
