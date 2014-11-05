/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
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
           @UniqueConstraint(columnNames = {"PAYGRADE"}))
public class PayGrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name = "PAYGRADE")
    private String payGrade;
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "payGrade") 
    private Collection<Employee> empCollection;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="payGrade")
    private Collection<PayGradeXLeaveTypeXDays> payGradeXLeaveTypeXDaysCollection;

    public Collection<PayGradeXLeaveTypeXDays> getPayGradeXLeaveTypeXDaysCollection() {
        return payGradeXLeaveTypeXDaysCollection;
    }

    public void setPayGradeXLeaveTypeXDaysCollection(Collection<PayGradeXLeaveTypeXDays> payGradeXLeaveTypeXDaysCollection) {
        this.payGradeXLeaveTypeXDaysCollection = payGradeXLeaveTypeXDaysCollection;
    }
    
    

    public Collection<Employee> getEmpCollection() {
        return empCollection;
    }

    public void setEmpCollection(Collection<Employee> empCollection) {
        this.empCollection = empCollection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(String payGrade) {
        this.payGrade = payGrade;
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
        if (!(object instanceof PayGrade)) {
            return false;
        }
        PayGrade other = (PayGrade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return payGrade;
    }
}
