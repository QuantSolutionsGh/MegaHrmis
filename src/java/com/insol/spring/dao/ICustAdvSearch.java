/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.Customer;
import java.util.Collection;

/**
 *
 * @author BERNARD
 */
public interface ICustAdvSearch {
    
    Collection<Customer> custFindByName(String custName);
    
    
    
    
}
