/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.Customer;

/**
 *
 * @author BERNARD
 */
public class CustomerDao extends JpaParentDao  {
    
    public CustomerDao(){
        super(Customer.class);
        
    }

    
    
  
        
}
