/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.*;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EntitlementProcessor implements IProcessEntitlement {

    private IHrGenericDao payGradeXLeaveTypeXDaysDao;
    private IHrGenericDao leaveEntitlementDao;

    public void setLeaveEntitlementDao(IHrGenericDao leaveEntitlementDao) {
        this.leaveEntitlementDao = leaveEntitlementDao;
    }

    public void setPayGradeXLeaveTypeXDaysDao(IHrGenericDao payGradeXLeaveTypeXDaysDao) {
        this.payGradeXLeaveTypeXDaysDao = payGradeXLeaveTypeXDaysDao;
    }

    @Override
    @Transactional
    public void processLeaveEntitlement(LeavePeriods lp) {


        for (PayGradeXLeaveTypeXDays payGradeXLeaveTypeXDays : (Collection<PayGradeXLeaveTypeXDays>) payGradeXLeaveTypeXDaysDao.findAll()) {
            PayGrade pg = payGradeXLeaveTypeXDays.getPayGrade();
            /*
             * Hibernate.initialize(pg.getEmpCollection());
             *
             * For an unknown reason yet payGradeXLeaveTypeXDays.getPayGrade()
             * method initialise the empCollection the
             * Hibernate.initialize(pg.getEmpCollection()) routine is not
             * relevant here
             */
            for (Employee emp : pg.getEmpCollection()) {
                
                

                //insert emp, payGrade, Leave Type,  Number of days
                EmployeeLeaveEntitlement el = new EmployeeLeaveEntitlement();

                el.setEmployee(emp);

                el.setLeavePeriods(lp);

                el.setLeaveTypes(payGradeXLeaveTypeXDays.getLeaveType());

                el.setDaysAllocated(payGradeXLeaveTypeXDays.getDaysAllocated());

                el.setDaysAvailable(payGradeXLeaveTypeXDays.getDaysAllocated());

                leaveEntitlementDao.store(el);



            }

        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Entitlement Processing completed successfully.", null));
        lp.setProcessed(true);


    }
}
