/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 *
 * @author BERNARD
 */
@Aspect
public class HRMISSystemAspects {
   //
    @DeclareParents(value = "com.met.hrmis.jpa.dao.LeavePeriodsDao",
    defaultImpl = com.met.hrmis.util.EntitlementProcessor.class)
    public IProcessEntitlement entitlementProcessor;
    
   
}
