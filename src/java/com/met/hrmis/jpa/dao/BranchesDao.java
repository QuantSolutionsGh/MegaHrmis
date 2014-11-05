/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Branches;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BERNARD
 */
@Repository
public class BranchesDao extends HrParentDao{
    
     public BranchesDao() {
        super(Branches.class);
    }
    
}
