/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EntitlementProcessor1 implements IProcessEntitlement {

    private IHrGenericDao payGradeXLeaveTypeXDaysDao;
    private IHrGenericDao leaveEntitlementDao;
    private IHrGenericDao leaveEntitlementDetailsDao;

    public void setLeaveEntitlementDetailsDao(IHrGenericDao leaveEntitlementDetailsDao) {
        this.leaveEntitlementDetailsDao = leaveEntitlementDetailsDao;
    }

    public void setLeaveEntitlementDao(IHrGenericDao leaveEntitlementDao) {
        this.leaveEntitlementDao = leaveEntitlementDao;
    }

    public void setPayGradeXLeaveTypeXDaysDao(IHrGenericDao payGradeXLeaveTypeXDaysDao) {
        this.payGradeXLeaveTypeXDaysDao = payGradeXLeaveTypeXDaysDao;
    }

    @Override
    @Transactional
    public void processLeaveEntitlement(LeavePeriods lp) {
        EmployeeLeaveEntitlement el = null;


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

                Query query = leaveEntitlementDao.getEntityManager().createQuery("select e from EmployeeLeaveEntitlement e where"
                        + " e.employee= :employee and e.leaveTypes= :leaveType");
                query.setParameter("employee", emp);

                query.setParameter("leaveType", payGradeXLeaveTypeXDays.getLeaveType());


                ArrayList<EmployeeLeaveEntitlement> qResults = (ArrayList<EmployeeLeaveEntitlement>) query.getResultList();

                if (qResults.isEmpty()) {
                    el = new EmployeeLeaveEntitlement();

                    el.setEmployee(emp);

                    //    el.setLeavePeriods(lp); this field is not relevant anymore

                    el.setLeaveTypes(payGradeXLeaveTypeXDays.getLeaveType());

                    el.setDaysAllocated(payGradeXLeaveTypeXDays.getDaysAllocated());

                    el.setDaysAvailable(payGradeXLeaveTypeXDays.getDaysAllocated());

                    leaveEntitlementDao.store(el);

                } else {
                    qResults.get(0).setDaysAvailable(qResults.get(0).getDaysAvailable() + payGradeXLeaveTypeXDays.getDaysAllocated());
                    el = qResults.get(0);
                    leaveEntitlementDao.update(qResults.get(0));
                }

                //okay now insert into empLeaveDetails
                EmployeeXEntitlementXDetails empLeaveDetails = new EmployeeXEntitlementXDetails();
                empLeaveDetails.setEmployee(emp);
                empLeaveDetails.setTransactionType("C");
                empLeaveDetails.setEmployeeLeaveEntitlement(el);
                
                empLeaveDetails.setRegistrationDate(new Date());
                
                empLeaveDetails.setStatus("A");
                empLeaveDetails.setReason(lp.toString());
                empLeaveDetails.setLeaveDays(payGradeXLeaveTypeXDays.getDaysAllocated());


                this.leaveEntitlementDetailsDao.store(empLeaveDetails);

            }

        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Entitlement Processing completed successfully.", null));
        lp.setProcessed(true);


    }
}
