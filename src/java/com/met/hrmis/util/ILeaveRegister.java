/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.LeaveTypes;

/**
 *
 * @author BERNARD
 */
public interface ILeaveRegister {
    
    public void postTransaction(Employee e, LeaveTypes l, String transType, String reason, int leaveDays);
    
}
