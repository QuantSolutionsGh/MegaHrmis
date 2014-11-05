/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.EmploymentStatus;

/**
 *
 * @author BERNARD
 */
public class EmploymentStatusDao extends HrParentDao {
    public EmploymentStatusDao(){
        super(EmploymentStatus.class);
    }
    
}
