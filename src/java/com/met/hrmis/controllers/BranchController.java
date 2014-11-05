/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Branches;

/**
 *
 * @author BERNARD
 */
public class BranchController extends ParentController{
    
    public BranchController(){
        super(Branches.class);
    }
    
    
}
