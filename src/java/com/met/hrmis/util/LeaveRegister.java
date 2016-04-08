/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement;
import com.met.hrmis.jpa.entities.EmployeeXEntitlementXDetails;
import com.met.hrmis.jpa.entities.LeaveTypes;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class LeaveRegister implements ILeaveRegister{
    
    public IHrGenericDao empXEntitlementXLeaveDetailsDao;
    
    public IHrGenericDao empXEntitlementDao;

    public void setEmpXEntitlementDao(IHrGenericDao empXEntitlementDao) {
        this.empXEntitlementDao = empXEntitlementDao;
    }

    public void setEmpXEntitlementXLeaveDetailsDao(IHrGenericDao empXEntitlementXLeaveDetailsDao) {
        this.empXEntitlementXLeaveDetailsDao = empXEntitlementXLeaveDetailsDao;
    }
    
         
    

    @Override
    @Transactional
    public void postTransaction(Employee e, LeaveTypes l, String transType, String reason, int leaveDays) {
       
        EmployeeLeaveEntitlement el = null;

        Query query = this.empXEntitlementDao.getEntityManager().createQuery("select e from EmployeeLeaveEntitlement e where"
                + " e.employee= :employee and e.leaveTypes= :leaveType");
        query.setParameter("employee", e);

        query.setParameter("leaveType", l);


        ArrayList<EmployeeLeaveEntitlement> qResults = (ArrayList<EmployeeLeaveEntitlement>) query.getResultList();

        if (qResults.isEmpty()) {
            el = new EmployeeLeaveEntitlement();

            el.setEmployee(e);

            //    el.setLeavePeriods(lp); this field is not relevant anymore

            el.setLeaveTypes(l);

            el.setDaysAllocated(leaveDays);

            el.setDaysAvailable(leaveDays);

            empXEntitlementDao.store(el);

        } else {

            if ("C".equals(transType)) {
                qResults.get(0).setDaysAvailable(qResults.get(0).getDaysAvailable() + leaveDays);
            } else {
                qResults.get(0).setDaysAvailable(qResults.get(0).getDaysAvailable() - leaveDays);

            }
            el = qResults.get(0);
            empXEntitlementDao.update(qResults.get(0));
        }

        //okay now insert into empLeaveDetails
        EmployeeXEntitlementXDetails empLeaveDetails = new EmployeeXEntitlementXDetails();
        empLeaveDetails.setEmployee(e);
        empLeaveDetails.setTransactionType(transType);
        empLeaveDetails.setEmployeeLeaveEntitlement(el);

        empLeaveDetails.setRegistrationDate(new Date());

        empLeaveDetails.setStatus("A");
        empLeaveDetails.setReason(reason);
        empLeaveDetails.setLeaveDays(leaveDays);


        this.empXEntitlementXLeaveDetailsDao.store(empLeaveDetails);





    }
    
}
