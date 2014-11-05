/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Authorities;

/**
 *
 * @author BERNARD
 */
public class UserAuthoritiesController extends ParentController {
    
    public UserAuthoritiesController(){
        super(Authorities.class);
    }
    
}
