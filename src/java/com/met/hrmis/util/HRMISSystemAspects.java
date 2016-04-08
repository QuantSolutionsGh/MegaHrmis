/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.EmployeeXLeaveEntitlementXLeaveDetailsDao;
import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;
import com.met.hrmis.jpa.entities.EmployeeXEntitlementXDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
@Aspect
public class HRMISSystemAspects {

    private IHrGenericDao leaveEntitlementDao;

    public IHrGenericDao getLeaveEntitlementDao() {
        return leaveEntitlementDao;
    }

    public void setLeaveEntitlementDao(IHrGenericDao leaveEntitlementDao) {
        this.leaveEntitlementDao = leaveEntitlementDao;
    }

    //  @Around("execution(* com.insol.spring.dao.CustomerDao.store(..))")
    @Around("execution(* com.met.hrmis.jpa.dao.IHrGenericDao.store(..)))")
    public void empXentitlementXdetailsBeforeStore(ProceedingJoinPoint jp) throws Throwable {  //actually before insert

        Object[] args = jp.getArgs();
        //could try  jp.getTarget().getClass()= CustomerDao

        if (jp.getTarget().getClass() == EmployeeXLeaveEntitlementXLeaveDetailsDao.class) {
            EmployeeXEntitlementXDetails empXentitlementXdetails = (EmployeeXEntitlementXDetails) args[0];

            empXentitlementXdetails.setOutstandingBalance(empXentitlementXdetails.getEmployeeLeaveEntitlement().getDaysAvailable());



            args[0] = empXentitlementXdetails;



        }

        jp.proceed(args);
    }

    @Around("execution(* com.met.hrmis.jpa.dao.IHrGenericDao.update(..))")
    @Transactional
    public void empXentitlementXdetailsBeforeUpdate(ProceedingJoinPoint jp) throws Throwable {

        Object[] args = jp.getArgs();
        //could try  jp.getTarget().getClass()= CustomerDao

        if (jp.getTarget().getClass() == EmployeeXLeaveEntitlementXLeaveDetailsDao.class) {
            EmployeeXEntitlementXDetails var = (EmployeeXEntitlementXDetails) args[0];

            if ("A".equals(var.getStatus())) {  //if a pending leave request is approved
                var.setTransactionType("D");
                EmployeeLeaveEntitlement empLeaveEntitlement = (EmployeeLeaveEntitlement) leaveEntitlementDao.find(var.getEmployeeLeaveEntitlement().getId());
                empLeaveEntitlement.setDaysAvailable(empLeaveEntitlement.getDaysAvailable() - var.getLeaveDays());
                leaveEntitlementDao.update(empLeaveEntitlement);


                var.setOutstandingBalance(empLeaveEntitlement.getDaysAvailable());

            }








            args[0] = var;

        }

        jp.proceed(args);
    }
}
