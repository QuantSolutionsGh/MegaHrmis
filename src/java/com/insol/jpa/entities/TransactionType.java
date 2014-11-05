/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insol.jpa.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD 
 * Transaction types - Deposit, Withdrawal, COT, Charges, Interest
 */
@Entity
@Table(name="TransactionType")
public class TransactionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID")
    private String id;
    @Column(name="TRANSACTION_CODE",unique=true)
    private String transactionCode;
    @Column(name="TRANSACTION_DESCRIPTION")
    private String transactionDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.transactionCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionType other = (TransactionType) obj;
        if (!Objects.equals(this.transactionCode, other.transactionCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return transactionDescription;
    }
    
    
    
}
