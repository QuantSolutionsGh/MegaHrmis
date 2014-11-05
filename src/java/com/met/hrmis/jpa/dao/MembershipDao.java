/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Membership;

/**
 *
 * @author BERNARD
 */
public class MembershipDao extends HrParentDao {
    
    public MembershipDao(){
        super(Membership.class);
    }
    
}
