/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD After leave notification let's delete reminder
 */
public class LeaveNotifier implements INotifier {

    private IEntityManagerFactory emFactory;
    private IHrGenericDao leavePlannerDao;
    private IHrGenericDao leaveDetailsDao;
    private ISendMail mailer;
    private EmailConfig emailConfig;
    
   
    
    public LeaveNotifier(){
        
    }

    public void setLeaveDetailsDao(IHrGenericDao leaveDetailsDao) {
        this.leaveDetailsDao = leaveDetailsDao;
    }

    public IHrGenericDao getLeavePlannerDao() {
        return leavePlannerDao;
    }

    public void setLeavePlannerDao(IHrGenericDao leavePlannerDao) {
        this.leavePlannerDao = leavePlannerDao;
    }

    public void setEmFactory(IEntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public void setMailer(ISendMail mailer) {
        this.mailer = mailer;
    }

    @Override
    @Transactional
    public void alert() {


        //ok get email config details
        this.emailConfig = (EmailConfig) emFactory.getEntityManager().
                createQuery("select e from EmailConfig e").getResultList().get(0);


        Calendar now = Calendar.getInstance();



        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        





//          Query query = emFactory.getEntityManager().createQuery("select e from EmpXLeavePlanner e where "
//                 + "month(e.startDate -10) <= :month and day(e.startDate - 10)<= :day and e.alertSent='N'");
//
//          query.setParameter("month", month);
//          query.setParameter("day", day);


        //    Query query = emFactory.getEntityManager().createQuery("select e from EmpXLeavePlanner e where "
        //           + "e.startDate - :myDate <=10 and e.alertSent='N'");
        //    query.setParameter("myDate", new Date());

        Query query = emFactory.getEntityManager().createQuery("select e from EmpXLeavePlanner e where "
                + "e.startDate-10 <= :aDate and e.alertSent='N'");

        query.setParameter("aDate", now.getTime());



        ArrayList<EmpXLeavePlanner> qResult = (ArrayList<EmpXLeavePlanner>) query.getResultList();

        for (EmpXLeavePlanner empXleavePlanner : qResult) {
            // we will now record details in employeeXEntitlementXDetails
            EmployeeXEntitlementXDetails empLeaveDetails = new EmployeeXEntitlementXDetails();
            // 20/1/2016  i think that in the leave planner the leave period should be tied to a leave type
            // so i get to parametize here rather than hardcoding here.
//            Query query1 = emFactory.getEntityManager().createQuery("select e from "
//                    + " EmployeeLeaveEntitlement e where e.leaveTypes.leaveType='ANNUAL' "
//                    + " and e.employee.id= :id");
            Query query1 = emFactory.getEntityManager().createQuery("select e from "
                    + " EmployeeLeaveEntitlement e where e.leaveTypes.leaveType=:leaveType "
                    + " and e.employee.id= :id");
            query1.setParameter("id", empXleavePlanner.getEmployee().getId());
            query1.setParameter("leaveType",empXleavePlanner.getLeaveType());
            EmployeeLeaveEntitlement empLeaveEntitlement = (EmployeeLeaveEntitlement) query1.getSingleResult();


            empLeaveDetails.setEmployee(empXleavePlanner.getEmployee());
            empLeaveDetails.setStartDate(empXleavePlanner.getStartDate());
            empLeaveDetails.setEndDate(empXleavePlanner.getEndDate());
            empLeaveDetails.setLeaveDays(FacesUtils.getWorkingDaysBetweenTwoDates(empXleavePlanner.getStartDate(),
                    empXleavePlanner.getEndDate()));
            empLeaveDetails.setRegistrationDate(new Date());
            empLeaveDetails.setEmployeeLeaveEntitlement(empLeaveEntitlement);
            this.leaveDetailsDao.update(empLeaveDetails);

            //lets notify supervisor and copy hr that employee is due to go on leave

            SimpleDateFormat ft = new SimpleDateFormat("dd MMM YYYY");
     
         


          String  url = emailConfig.getAppContext() + "/faces/pendingleaverequests.xhtml?supId=" + empLeaveDetails.getEmployee().getSupervisor().getId();



            Map model = new HashMap();

            model.put("startDate", ft.format(empXleavePlanner.getStartDate()));
            model.put("endDate", ft.format(empXleavePlanner.getEndDate()));

            //   model.put("leavePeriod", this.empLeaveEntitlement.getLeavePeriods().toString()); not relevant anymore
            model.put("leaveDaysOutstanding", empLeaveEntitlement.getDaysAvailable());
            model.put("leaveType", empLeaveEntitlement.getLeaveTypes());
            model.put("url", url);
            model.put("leaveDays", empLeaveDetails.getLeaveDays());

            Mail mail = new Mail();
            mail.setMailFrom(emailConfig.getMailSender());
            mail.setMailTo(empXleavePlanner.getEmployee().getSupervisor().getEmail());
            mail.setMailSubject("Leave Due - " + empXleavePlanner.getEmployee());
            mail.setTemplateName("/velocity/leave_request_supervisor.vm");
            mail.setMailCc(emailConfig.getLeaveApprovalNotificationSubscribers());  //here use george panford and emma or abeka
            mail.setMailSendDate(new Date());

            mailer.sendMail(model, mail);
            
            
            //okay lets notify employee as well
            
            mail = new Mail();
            mail.setMailFrom(emailConfig.getMailSender());
            mail.setMailTo(empXleavePlanner.getEmployee().getEmail());
            mail.setMailSubject("Leave Due");
            mail.setTemplateName("/velocity/leave_request_emp_notifier.vm");
            mail.setMailSendDate(new Date());
            
            mailer.sendMail(model,mail);
            
            

            //after sending out alert delete from leave Planner
            leavePlannerDao.delete(empXleavePlanner.getId());
        }
    }
}
