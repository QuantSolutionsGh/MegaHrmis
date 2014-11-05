/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.CustomerClass;

/**
 *
 * @author BERNARD
 */
public class CustClassDao extends JpaParentDao{

    public CustClassDao() {
        super(CustomerClass.class);
    }

    
    
}
