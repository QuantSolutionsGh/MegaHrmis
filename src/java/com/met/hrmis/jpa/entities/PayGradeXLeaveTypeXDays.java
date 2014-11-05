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
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"PAYGRADE","LEAVETYPE"}))
public class PayGradeXLeaveTypeXDays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @JoinColumn(name="PAYGRADE",referencedColumnName="ID")
    @ManyToOne
    private PayGrade payGrade;
    @JoinColumn(name="LEAVETYPE",referencedColumnName="ID")
    @ManyToOne
    private LeaveTypes leaveType;
    @Column(name="DAYS_ALLOCATED")
    private Integer daysAllocated;

    public Integer getDaysAllocated() {
        return daysAllocated;
    }

    public void setDaysAllocated(Integer daysAllocated) {
        this.daysAllocated = daysAllocated;
    }

    public LeaveTypes getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypes leaveType) {
        this.leaveType = leaveType;
    }

    public PayGrade getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(PayGrade payGrade) {
        this.payGrade = payGrade;
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
        if (!(object instanceof PayGradeXLeaveTypeXDays)) {
            return false;
        }
        PayGradeXLeaveTypeXDays other = (PayGradeXLeaveTypeXDays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.PayGradeXLeaveTypeXDays[ id=" + id + " ]";
    }
}
