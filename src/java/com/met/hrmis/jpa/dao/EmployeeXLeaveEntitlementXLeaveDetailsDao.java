/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;
import com.met.hrmis.jpa.entities.EmployeeXEntitlementXDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EmployeeXLeaveEntitlementXLeaveDetailsDao extends HrParentDao{
    
    private IHrGenericDao leaveEntitlementDao;
    
    public EmployeeXLeaveEntitlementXLeaveDetailsDao(){
        super(EmployeeXEntitlementXDetails.class);
    }

    public void setLeaveEntitlementDao(IHrGenericDao leaveEntitlementDao) {
        this.leaveEntitlementDao = leaveEntitlementDao;
    }
    
    

    @Override
    @Transactional
    public void update(Object entity) {
         EmployeeXEntitlementXDetails var = (EmployeeXEntitlementXDetails)entity;

        if ("A".equals(var.getStatus())) {
            var.setTransactionType("W");
            EmployeeLeaveEntitlement empLeaveEntitlement =(EmployeeLeaveEntitlement) leaveEntitlementDao.find(var.getEmployeeLeaveEntitlement().getId());
            empLeaveEntitlement.setDaysAvailable(empLeaveEntitlement.getDaysAvailable()-var.getLeaveDays());
              
                     
                    
        }

    
        super.update(entity);
    }
    
    
}
