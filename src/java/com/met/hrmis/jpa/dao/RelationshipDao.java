/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Relationship;

/**
 *
 * @author BERNARD
 */
public class RelationshipDao extends HrParentDao {
    
    public RelationshipDao(){
        super(Relationship.class);
    }
    
}
