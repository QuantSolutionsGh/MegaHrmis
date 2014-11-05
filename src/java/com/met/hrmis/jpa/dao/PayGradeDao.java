/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.PayGrade;

/**
 *
 * @author BERNARD
 */
public class PayGradeDao  extends HrParentDao{
    
    public PayGradeDao(){
        super(PayGrade.class);
    }
    
}
