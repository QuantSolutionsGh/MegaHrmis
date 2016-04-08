/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;
import com.met.hrmis.jpa.entities.EmployeeXEntitlementXDetails;
import java.util.Collection;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EmployeeXLeaveEntitlementXLeaveDetailsDao extends HrParentDao {

    private IHrGenericDao leaveEntitlementDao;

    public EmployeeXLeaveEntitlementXLeaveDetailsDao() {
        super(EmployeeXEntitlementXDetails.class);
    }

    public void setLeaveEntitlementDao(IHrGenericDao leaveEntitlementDao) {
        this.leaveEntitlementDao = leaveEntitlementDao;
    }

    @Override
    @Transactional
    public void store(Object entity) {
        EmployeeXEntitlementXDetails var = (EmployeeXEntitlementXDetails) entity;
        var.setOutstandingBalance(var.getEmployeeLeaveEntitlement().getDaysAvailable());
        var.setApprovalDate(new Date());  //if leave is pending then approval date = regist_date but when it is approved approval date will be > regist
        super.store(entity);


    }

    @Override
    @Transactional
    public void update(Object entity) {
        EmployeeXEntitlementXDetails var = (EmployeeXEntitlementXDetails) entity;

        if ("A".equals(var.getStatus())) {
            var.setTransactionType("D");
            var.setApprovalDate(new Date());
            EmployeeLeaveEntitlement empLeaveEntitlement = (EmployeeLeaveEntitlement) leaveEntitlementDao.find(var.getEmployeeLeaveEntitlement().getId());
            empLeaveEntitlement.setDaysAvailable(empLeaveEntitlement.getDaysAvailable() - var.getLeaveDays());
            var.setOutstandingBalance(empLeaveEntitlement.getDaysAvailable());
            leaveEntitlementDao.update(empLeaveEntitlement);



        }


        super.update(entity);
    }

    @Override
    public Collection findAll() {
        //  return entityManager.createQuery("from " + this.clazz.getName()).getResultList();

        //   this.getEntityManager().createQuery("from "+ this.getClass().getName() + " order by ")
        return super.findAll();
    }
}
