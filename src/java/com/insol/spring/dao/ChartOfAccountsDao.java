/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.GLAccount;

/**
 *
 * @author BERNARD
 */
public class ChartOfAccountsDao extends JpaParentDao {

    public ChartOfAccountsDao() {
        super(GLAccount.class);
    }
    
}
