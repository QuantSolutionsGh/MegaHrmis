/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Membership;

/**
 *
 * @author BERNARD
 */
public class MembershipController extends ParentController {
    
    public MembershipController(){
        super(Membership.class);
    }
    
}
