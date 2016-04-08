/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 * This is for the leave request
 */
@Entity
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeXEntitlementXDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    
    @JoinColumn(name="EMPLOYEE",referencedColumnName="ID")
    @ManyToOne()
    private Employee employee;
    
    
    @JoinColumn(name="EMP_LEAVE_ENTITLEMENT", referencedColumnName="ID")
    @ManyToOne()
    private EmployeeLeaveEntitlement employeeLeaveEntitlement;
    
    @Column(name="STARTDATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(name="ENDDATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    @Column(name="TRANSACTION_TYPE")   //deposit or widthdrawal
    private String transactionType;
    
    @Column(name="LEAVE_DAYS")
    private int leaveDays;
    
    
    @Column(name="REGISTRATION_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registrationDate;
    
    @Column(name="APPROVAL_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvalDate;
    
    @Column(name="REASON")
    private String reason;
    
    @Column(name="COMMENTS_OF_SUPERVISOR")
    private String comments_supervisor;
    
    @Column(name="STATUS")
    private String status;
    
    @Column(name="OUTSTANDING_BALANCE")
    
    private int outstandingBalance;

    public int getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(int outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
    
    

    public String getComments_supervisor() {
        return comments_supervisor;
    }

    public void setComments_supervisor(String comments_supervisor) {
        this.comments_supervisor = comments_supervisor;
    }
    
    

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeLeaveEntitlement getEmployeeLeaveEntitlement() {
        return employeeLeaveEntitlement;
    }

    public void setEmployeeLeaveEntitlement(EmployeeLeaveEntitlement employeeLeaveEntitlement) {
        this.employeeLeaveEntitlement = employeeLeaveEntitlement;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
    
    

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeXEntitlementXDetails)) {
            return false;
        }
        EmployeeXEntitlementXDetails other = (EmployeeXEntitlementXDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.EmployeeXLeaveEntitlementXLeaveDetails[ id=" + id + " ]";
    }
    
}
