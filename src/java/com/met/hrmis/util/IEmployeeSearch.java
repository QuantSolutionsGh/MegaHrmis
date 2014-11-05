/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.Employee;
import java.util.Collection;

/**
 *
 * @author BERNARD
 */
public interface IEmployeeSearch {
    
    public Collection<Employee> EmployeeSearch(String queryString);
    
}
