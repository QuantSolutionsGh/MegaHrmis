/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import com.met.hrmis.jpa.entities.Relationship;

/**
 *
 * @author BERNARD
 */
public class RelationshipController extends ParentController {
    
    public RelationshipController(){
        super(Relationship.class);
    }
    
}
