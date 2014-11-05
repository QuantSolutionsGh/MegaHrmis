/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Education;

/**
 *
 * @author BERNARD
 */
public class LevelOfEducationController extends ParentController {
    
    public LevelOfEducationController(){
        super(Education.class);
    }
    
}
