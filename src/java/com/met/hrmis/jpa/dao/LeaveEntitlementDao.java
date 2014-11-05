/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;

/**
 *
 * @author BERNARD
 */
public class LeaveEntitlementDao extends HrParentDao{
    
    public LeaveEntitlementDao(){
        super(EmployeeLeaveEntitlement.class);
    }
    
}
