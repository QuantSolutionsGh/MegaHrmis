/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
public class EmpXLeavePlanner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "ID")
    @ManyToOne()
    private Employee employee;
    
    @JoinColumn(name="LEAVE_TYPE", referencedColumnName="ID")
    @OneToOne()
    private LeaveTypes leaveType;
    @Column(name = "ALERT_SENT")     //whether the staff has been alerted
    private String alertSent = "N";
    @Transient
    private Long startDateAsNumber;
    @Transient
    private Long endDateAsNumber;

    public Long getEndDateAsNumber() {
        if (this.endDate != null) {
            return this.getEndDate().getTime();
        }
        return 9999999999999L;
    }

    public Long getStartDateAsNumber() {
         if (this.startDate != null) {
            return this.startDate.getTime();
        }
        return 9999999999999L;
    }

    public String getAlertSent() {
        return alertSent;
    }

    public void setAlertSent(String alertSent) {
        this.alertSent = alertSent;
    }

    public LeaveTypes getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypes leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpXLeavePlanner other = (EmpXLeavePlanner) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
