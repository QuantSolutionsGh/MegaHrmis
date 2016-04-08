/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeLeaveEntitlement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "ID")
    @ManyToOne()
    private Employee employee;
    @JoinColumn(name="LEAVE_PERIOD",referencedColumnName="ID")
    @ManyToOne()
    private LeavePeriods leavePeriods;    //Not necessary anymore, only here because i don't know implications to code
    @JoinColumn(name="LEAVE_TYPE",referencedColumnName="ID")
    @ManyToOne()
    private LeaveTypes leaveTypes;
    @Column(name="DAYS_AVAILABLE")
    private int daysAvailable;
    @Column(name="DAYS_ALLOCATED")
    private int daysAllocated;

    public int getDaysAllocated() {
        return daysAllocated;
    }

    public void setDaysAllocated(int daysAllocated) {
        this.daysAllocated = daysAllocated;
    }

    public int getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(int daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeavePeriods getLeavePeriods() {
        return leavePeriods;
    }

    public void setLeavePeriods(LeavePeriods leavePeriods) {
        this.leavePeriods = leavePeriods;
    }

    public LeaveTypes getLeaveTypes() {
        return leaveTypes;
    }

    public void setLeaveTypes(LeaveTypes leaveTypes) {
        this.leaveTypes = leaveTypes;
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
        if (!(object instanceof EmployeeLeaveEntitlement)) {
            return false;
        }
        EmployeeLeaveEntitlement other = (EmployeeLeaveEntitlement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.EmployeeLeaveEntitlement[ id=" + id + " ]";
    }
}
