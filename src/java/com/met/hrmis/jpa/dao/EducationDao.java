/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Education;

/**
 *
 * @author BERNARD
 */
public class EducationDao extends HrParentDao{
    
    public EducationDao(){
        super(Education.class);
    }
}
