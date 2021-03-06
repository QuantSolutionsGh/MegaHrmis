/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.entities.*;
import com.met.hrmis.util.FacesUtils;
import com.met.hrmis.util.ILeaveRegister;
import com.met.hrmis.util.ISendMail;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author BERNARD
 */
public class EmployeeXLeaveEntitlementXLeaveDetailsController extends ParentController {

    private IEntityManagerFactory em;
    private ILeaveRegister leaveRegister;
    private ISendMail mailer;
    private int leaveDays;
    private Date startDate;
    private Date endDate;
    private Employee employee;
    private LeavePeriods leavePeriod;
    private LeaveTypes leaveType;
    private String reason;
    private EmployeeLeaveEntitlement empLeaveEntitlement;
    private Boolean submitButtonEnabled = false;
    private String transType;
    private int outstandingDays;
    private ArrayList<EmployeeXEntitlementXDetails> qResult;

    public ILeaveRegister getLeaveRegister() {
        return leaveRegister;
    }

    public void setLeaveRegister(ILeaveRegister leaveRegister) {
        this.leaveRegister = leaveRegister;
    }
    
    
    

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public void setMailer(ISendMail mailer) {
        this.mailer = mailer;
    }

    public Boolean getSubmitButtonEnabled() {

        if (this.leaveDays > this.outstandingDays) {
            this.submitButtonEnabled = false;
        } else {
            if (this.leaveDays == 0) {
                this.submitButtonEnabled = false;
            } else {
                this.submitButtonEnabled = true;
            }

        }
        return submitButtonEnabled;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int LeaveDays) {
        this.leaveDays = LeaveDays;
    }

    public int getOutstandingDays() {
        return outstandingDays;
    }

    public void setOutstandingDays(int outstandingDays) {
        this.outstandingDays = outstandingDays;
    }

    public void calcOutstandingDays(javax.faces.event.AjaxBehaviorEvent e) throws AbortProcessingException {

        Query query = em.getEntityManager().createQuery("select e from EmployeeLeaveEntitlement e where"
                + " e.employee= :employee and e.leaveTypes= :leaveType ");
        query.setParameter("employee", this.employee);

        query.setParameter("leaveType", this.leaveType);

        //    query.setParameter("leavePeriod", this.leavePeriod);

        ArrayList<EmployeeLeaveEntitlement> qResults = (ArrayList<EmployeeLeaveEntitlement>) query.getResultList();

        if (qResults.isEmpty()) {

            this.outstandingDays = 0;
            this.empLeaveEntitlement = null;
        } else {
            this.outstandingDays = qResults.get(0).getDaysAvailable();
            this.empLeaveEntitlement = qResults.get(0);
        }


    }

    public void calcLeaveDays(SelectEvent e) {

        this.leaveDays = FacesUtils.getWorkingDaysBetweenTwoDates(startDate, endDate);
    }

    public void submitLeaveRequest(ActionEvent e) {


        EmployeeXEntitlementXDetails empLeaveDetails = new EmployeeXEntitlementXDetails();
        try {

            empLeaveDetails.setEmployee(employee);
            empLeaveDetails.setStartDate(startDate);
            empLeaveDetails.setEndDate(endDate);
            empLeaveDetails.setReason(reason);
            empLeaveDetails.setLeaveDays(this.leaveDays);
            empLeaveDetails.setRegistrationDate(new Date());
            empLeaveDetails.setEmployeeLeaveEntitlement(this.empLeaveEntitlement);
            this.getGenericDao().update(empLeaveDetails);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Leave Request has been submitted.", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));

        }






        //send email to supervisor copy employee and hr


        try {
            //obsolete
            //  EmailConfiguration config = (EmailConfiguration) FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("emailConfiguration");

            EmailConfig emailConfig = (EmailConfig) em.getEntityManager().
                    createQuery("select e from EmailConfig e").getResultList().get(0);

            Mail mail = new Mail();
            mail.setMailFrom(emailConfig.getMailSender());
            mail.setMailTo(employee.getEmail());
            mail.setMailSubject("Leave Request");
            mail.setTemplateName("/velocity/leave_request_response.vm");

            mail.setMailSendDate(new Date());

            mailer.sendMail(null, mail);
        } catch (Exception a) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, a.getMessage(), null));

        }

        //notify supervisor and copy hr
        SimpleDateFormat ft = new SimpleDateFormat("dd MMM YYYY");

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String url = req.getRequestURL().toString();




        url = url.substring(0, url.length() - req.getRequestURI().length())
                + req.getContextPath() + "/faces/pendingleaverequests.xhtml?supId=" + empLeaveDetails.getEmployee().getSupervisor().getId();

        Map model = new HashMap();
        model.put("startDate", ft.format(this.startDate));
        model.put("endDate", ft.format(this.endDate));

        //   model.put("leavePeriod", this.empLeaveEntitlement.getLeavePeriods().toString()); not relevant anymore
        model.put("leaveDaysOutstanding", this.empLeaveEntitlement.getDaysAvailable());
        model.put("leaveType", this.empLeaveEntitlement.getLeaveTypes());
        model.put("url", url);
        model.put("leaveDays", empLeaveDetails.getLeaveDays());


        //  mailer.sendMail(empLeaveDetails, mail);
        try {
            EmailConfig emailConfig = (EmailConfig) em.getEntityManager().
                    createQuery("select e from EmailConfig e").getResultList().get(0);
            Mail mail = new Mail();
            mail.setMailFrom(emailConfig.getMailSender());
            mail.setMailTo(employee.getSupervisor().getEmail());
            mail.setMailSubject("Leave Request - " + employee);
            mail.setTemplateName("/velocity/leave_request_supervisor.vm");
            mail.setMailCc(emailConfig.getLeaveApprovalNotificationSubscribers());  //here use george panford and emma or abeka
            mail.setMailSendDate(new Date());

            mailer.sendMail(model, mail);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));

        }

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeavePeriods getLeavePeriod() {
        return leavePeriod;
    }

    public void setLeavePeriod(LeavePeriods leavePeriod) {
        this.leavePeriod = leavePeriod;
    }

    public LeaveTypes getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypes leaveType) {
        this.leaveType = leaveType;
    }

    public void setEm(IEntityManagerFactory em) {
        this.em = em;
    }

    public EmployeeXLeaveEntitlementXLeaveDetailsController() {
        super(EmployeeXEntitlementXDetails.class);
    }

    @Override
    public Collection getObjList() {





        return qResult;

    }

    public void allObjs(ComponentSystemEvent event) {


        try {


            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();



            String id = params.get("supId");

            if (id != null) {
                FacesUtils.setSessionMapValue("supId", id);

            };

            if (FacesUtils.getSessionMapValue("supId") != null) {
                Query myQuery = em.getEntityManager().createQuery("select e from EmployeeXEntitlementXDetails e"
                        + " where e.employee.supervisor.id= :id and e.status=null ");
                myQuery.setParameter("id", FacesUtils.getSessionMapValue("supId"));

                qResult = (ArrayList<EmployeeXEntitlementXDetails>) myQuery.getResultList();



            } else {
                Query myQuery = em.getEntityManager().createQuery("select e from EmployeeXEntitlementXDetails e"
                        + " where e.status= null ");


                qResult = (ArrayList<EmployeeXEntitlementXDetails>) myQuery.getResultList();

            }




        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));


        }
        //   return emp.getMembershipCollection();
        //  return null;

    }

    public void postEntry() {

        try {
            leaveRegister.postTransaction(employee, leaveType, transType, reason, leaveDays);
           
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry successfully posted.", null));


        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

        }
    }

    public void doProcess() {
        try {


            //      EmailConfiguration config = (EmailConfiguration) FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("emailConfiguration");


            EmployeeXEntitlementXDetails empLeaveDetails = (EmployeeXEntitlementXDetails) this.getCurrentElement();
            SimpleDateFormat ft = new SimpleDateFormat("dd MMM YYYY");


            Map model = new HashMap();
            model.put("startDate", ft.format(empLeaveDetails.getStartDate()));
            model.put("endDate", ft.format(empLeaveDetails.getEndDate()));
            model.put("leaveDays", empLeaveDetails.getLeaveDays());
            //  model.put("leavePeriod", empLeaveDetails.getEmployeeLeaveEntitlement().getLeavePeriods());
            
            model.put("leaveType", empLeaveDetails.getEmployeeLeaveEntitlement().getLeaveTypes());
            model.put("url", FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            model.put("comments", empLeaveDetails.getComments_supervisor());


            super.doSave();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Processed Successfully", null));




            //send email to staff and copy hr


            EmailConfig emailConfig = (EmailConfig) em.getEntityManager().
                    createQuery("select e from EmailConfig e").getResultList().get(0);
            Mail mail = new Mail();
            mail.setMailFrom(emailConfig.getMailSender());
            mail.setMailTo(empLeaveDetails.getEmployee().getEmail());
            mail.setMailSubject("Leave Request Status - " + empLeaveDetails.getEmployee());
            mail.setTemplateName("/velocity/leave_request_supervisor.vm");
            mail.setMailCc(emailConfig.getLeaveApprovalNotificationSubscribers());  //here use george panford and emma or abeka


            if (empLeaveDetails.getStatus().contains("A")) {
                mail.setTemplateName("/velocity/leave_request_approved.vm");
                model.put("leaveDaysOutstanding", empLeaveDetails.getEmployeeLeaveEntitlement().getDaysAvailable()
                    - empLeaveDetails.getLeaveDays());

            } else if (empLeaveDetails.getStatus().contains("R")) {
                mail.setTemplateName("/velocity/leave_request_rejected.vm");
                model.put("leaveDaysOutstanding", empLeaveDetails.getEmployeeLeaveEntitlement().getDaysAvailable());
                   
            } else {
                mail.setTemplateName("/velocity/leave_request_cancelled.vm");
                model.put("leaveDaysOutstanding", empLeaveDetails.getEmployeeLeaveEntitlement().getDaysAvailable());
            }
            mail.setMailSendDate(new Date());

            mailer.sendMail(model, mail);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), null));

        }


    }
}
