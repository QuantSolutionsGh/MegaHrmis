/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Authorities;

/**
 *
 * @author BERNARD
 */
public class UserAuthoritiesDao  extends HrParentDao{

    public UserAuthoritiesDao(){
        super(Authorities.class);
    }
}
