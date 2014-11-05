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
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"EMPSTATUS"}))
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EmploymentStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name = "EMPSTATUS")
    private String empStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empStatus")
    private Collection<Employee> empCollection;

    public Collection<Employee> getEmpCollection() {
        return empCollection;
    }

    public void setEmpCollection(Collection<Employee> empCollection) {
        this.empCollection = empCollection;
    }
    
    

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
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
        if (!(object instanceof EmploymentStatus)) {
            return false;
        }
        EmploymentStatus other = (EmploymentStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.EmploymentStatus[ id=" + id + " ]";
    }
}
