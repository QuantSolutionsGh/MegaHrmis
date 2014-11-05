/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.EmployeeHistory;

/**
 *
 * @author BERNARD
 */
public class EmployeeHistoryDao extends HrParentDao {
    
    public EmployeeHistoryDao(){
        super(EmployeeHistory.class);
    }
    
}
