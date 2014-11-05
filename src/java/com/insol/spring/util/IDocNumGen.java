/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.util;

import com.insol.jpa.entities.CustomerClass;

/**
 *
 * @author BERNARD
 */
public interface IDocNumGen {
    
    String getCustCode(CustomerClass custClass);
    
   
    
}
