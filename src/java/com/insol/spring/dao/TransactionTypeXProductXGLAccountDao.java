/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.TransactionTypeXProductXGlAccount;

/**
 *
 * @author BERNARD
 */
public class TransactionTypeXProductXGLAccountDao  extends JpaParentDao{
    
    public TransactionTypeXProductXGLAccountDao(){
        super(TransactionTypeXProductXGlAccount.class);
    }
}
