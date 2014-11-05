/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.AccountGroup;

/**
 *
 * @author BERNARD
 */
public class AccountGroupDao extends JpaParentDao{

    public AccountGroupDao() {
        super(AccountGroup.class);
    }

    
    
}
