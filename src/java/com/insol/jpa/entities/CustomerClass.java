/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@Table(name="CUSTOMERCLASS")
public class CustomerClass implements Serializable {
    private static final long serialVersionUID = 1L;
   @Id @GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID")
    private String id;
    
    @Column(name ="CUST_TYPE",unique=true)
    
    private String custType;
    
    @Column(name="CUST_CLASS_PREFIX")
    private String custClassPrefix;
    
    @Column(name="NO_OF_DIGITS")
    private Integer noOfDigits;
    
    @Column(name="CURRENT_VALUE")
    private Long currentValue;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="custType")
    private Collection<Customer> custCollection;

    public Collection<Customer> getCustCollection() {
        return custCollection;
    }

    public void setCustCollection(Collection<Customer> custCollection) {
        this.custCollection = custCollection;
    }
       

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    public String getCustClassPrefix() {
        return custClassPrefix;
    }

    public void setCustClassPrefix(String custClassPrefix) {
        this.custClassPrefix = custClassPrefix;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNoOfDigits() {
        return noOfDigits;
    }

    public void setNoOfDigits(Integer noOfDigits) {
        this.noOfDigits = noOfDigits;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerClass other = (CustomerClass) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "CustomerClass{" + "id=" + id + ", custType=" + custType + ", custClassPrefix=" + custClassPrefix + ", noOfDigits=" + noOfDigits + ", currentValue=" + currentValue + '}';
    }
    
    
}
