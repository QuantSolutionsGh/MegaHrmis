/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.LeavePeriods;
import com.met.hrmis.util.IProcessEntitlement;

/**
 *
 * @author BERNARD
 */
public class LeavePeriodsController extends ParentController {
    
    private IProcessEntitlement entitlementProcessor;

    public void setEntitlementProcessor(IProcessEntitlement entitlementProcessor) {
        this.entitlementProcessor = entitlementProcessor;
    }
    
    
    
   public LeavePeriodsController(){
       super(LeavePeriods.class);
   }
   
  
    public void processLeaveEntitlement(LeavePeriods lp){
        
        entitlementProcessor.processLeaveEntitlement(lp);
        lp.setProcessed(true);
        this.getGenericDao().update(lp);
        
        
    }
}
