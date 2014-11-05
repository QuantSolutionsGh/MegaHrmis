/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.JobCategories;

/**
 *
 * @author BERNARD
 */
public class JobCategoriesController extends ParentController {
    
    public JobCategoriesController(){
        super(JobCategories.class);
    }
}
