/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Employee;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class EmployeeDao extends HrParentDao {
    
    public EmployeeDao(){
        super(Employee.class);
    }

    @Override
    @Transactional
    public Object find(Object id) {
        Employee emp= (Employee) super.find(id);
         Hibernate.initialize(emp.getMembershipCollection());
         Hibernate.initialize(emp.getEducationCollection());
         Hibernate.initialize(emp.getSkillsCollection());
         Hibernate.initialize(emp.getRelationshipCollection());
         Hibernate.initialize(emp.getEmpHistoryCollection());
         Hibernate.initialize(emp.getEmpTrainingCollection());
         Hibernate.initialize(emp.getEmpLeaveCollection());
         return emp;
    }
    
   
    
    

    
    
}
