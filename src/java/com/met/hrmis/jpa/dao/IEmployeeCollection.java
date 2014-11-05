/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.*;
import java.util.Collection;

/**
 *
 * @author BERNARD 
 * not used for now
 */
public interface IEmployeeCollection {
    
    public Collection<EmployeeXMembership> getMembershipCollection(Object id);
    
    public Collection<EmployeeXEducation> getEducationCollection(Object id);
    
    public Collection<EmployeeXRelationship> getRelationshipCollection(Object id);
    
    public Collection<EmployeeXSkills> getSkillsCollection(Object id);
    
    public Collection<EmployeeHistory> getEmpHistoryCollection(Object id);
    
}
