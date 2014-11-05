/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jpa.entities;

import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name = "CUST_CODE")
    private String custCode;
    @Column(name = "CUST_GENDER")
    private String custGender;
    @Column(name = "CUST_LAST_NAME")
    private String custLastName;
    @Column(name = "CUST_FIRST_NAME")
    private String custFirstName;
    @Column(name = "CUST_MIDDLE_NAME")
    private String custMiddleName;
    @Column(name = "CUST_FULL_NAME")
    private String custFullName;
    @Column(name = "CUST_ADDR_LINE1")
    private String custAddrLine1;
    @Column(name = "CUST_ADDR_LINE2")
    private String custAddrLine2;
    @Column(name = "CUST_ADDR_LINE3")
    private String custAddrLine3;
    @Column(name = "CUST_PHONE")
    private String custPhone;
    @Column(name = "CUST_NEXT_OF_KIN")
    private String custNextOfKin;
    @JoinColumn(name = "CUST_TYPE", referencedColumnName = "CUST_TYPE")
    @ManyToOne()
    private CustomerClass custType;

    public String getCustAddrLine1() {
        return custAddrLine1;
    }

    public void setCustAddrLine1(String custAddrLine1) {
        this.custAddrLine1 = custAddrLine1;
    }

    public String getCustAddrLine2() {
        return custAddrLine2;
    }

    public void setCustAddrLine2(String custAddrLine2) {
        this.custAddrLine2 = custAddrLine2;
    }

    public String getCustAddrLine3() {
        return custAddrLine3;
    }

    public void setCustAddrLine3(String custAddrLine3) {
        this.custAddrLine3 = custAddrLine3;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustMiddleName() {
        return custMiddleName;
    }

    public void setCustMiddleName(String custMiddleName) {
        this.custMiddleName = custMiddleName;
    }

    public String getCustFullName() {
        this.custFullName = this.custFirstName + ' ' + this.custMiddleName + ' ' + this.custLastName;
        return this.custFullName;
    }

    public void setCustFullName(String custFullName) {
        this.custFullName = custFullName;
    }

    public String getCustNextOfKin() {
        return custNextOfKin;
    }

    public void setCustNextOfKin(String custNextOfKin) {
        this.custNextOfKin = custNextOfKin;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public CustomerClass getCustType() {
        return custType;
    }

    public void setCustType(CustomerClass custType) {
        this.custType = custType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

   

    @Override
    public String toString() {
        return "Customer{" + "custLastName=" + custLastName + ", custFirstName=" + custFirstName + ", custMiddleName=" + custMiddleName + '}';
    }
}
