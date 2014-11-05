/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;

/**
 *
 * @author BERNARD
 */
public class LeaveEntitlementController extends ParentController{
    
    public LeaveEntitlementController(){
        super(EmployeeLeaveEntitlement.class);
    }
    
}
