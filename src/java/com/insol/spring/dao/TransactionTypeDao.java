/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.TransactionType;

/**
 *
 * @author BERNARD
 */
public class TransactionTypeDao extends JpaParentDao{
    
     public TransactionTypeDao() {
        super(TransactionType.class);
    }
    
}
