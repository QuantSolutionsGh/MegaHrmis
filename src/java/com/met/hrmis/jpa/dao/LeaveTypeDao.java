/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.LeaveTypes;

/**
 *
 * @author BERNARD
 */
public class LeaveTypeDao extends HrParentDao {
    
    public LeaveTypeDao(){
        super(LeaveTypes.class);
    }
    
}
