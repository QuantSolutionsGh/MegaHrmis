/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Department;

/**
 *
 * @author BERNARD
 */
public class DepartmentController extends ParentController{
    
    public DepartmentController(){
        super(Department.class);
    }
    
}
