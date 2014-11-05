/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jpa.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@Table(name = "TransactionTypeXProductXGlAccount")
public class TransactionTypeXProductXGlAccount implements Serializable {

    private static final long serialVersionUID = 1L;
   @Id @GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID")
    private String id;

    @JoinColumn(name = "TRANSACTION_CODE", referencedColumnName = "TRANSACTION_CODE")
    @ManyToOne()
    private TransactionType transType;
    @JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "PRODUCT_CODE")
    @ManyToOne()
    private Product product;

    //private Branch branch;
    @JoinColumn(name = "ACCOUNT_CODE_BANK", referencedColumnName = "ACCOUNT_CODE")
    @ManyToOne()
    private GLAccount glBank;

    @JoinColumn(name = "ACCOUNT_CODE_CUSTOMER", referencedColumnName = "ACCOUNT_CODE")
    @ManyToOne()
    private GLAccount glCustomer;

    public GLAccount getGlCustomer() {
        return glCustomer;
    }

    public void setGlCustomer(GLAccount glCustomer) {
        this.glCustomer = glCustomer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionType getTransType() {
        return transType;
    }

    public void setTransType(TransactionType transType) {
        this.transType = transType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public GLAccount getGlBank() {
        return glBank;
    }

    public void setGlBank(GLAccount glBank) {
        this.glBank = glBank;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionTypeXProductXGlAccount other = (TransactionTypeXProductXGlAccount) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "TransactionTypeXProductXGlAccount{" + "id=" + id + ", transType=" + transType + ", product=" + product + ", glBank=" + glBank + ", glCustomer=" + glCustomer + '}';
    }
    
    

}
