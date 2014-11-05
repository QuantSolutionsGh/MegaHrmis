/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.JobCategories;

/**
 *
 * @author BERNARD
 */
public class JobCategoriesDao extends HrParentDao {
    
    public JobCategoriesDao(){
        super(JobCategories.class);
    }
    
}
