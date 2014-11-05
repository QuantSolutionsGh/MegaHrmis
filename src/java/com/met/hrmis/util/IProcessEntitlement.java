/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.LeavePeriods;

/**
 *
 * @author BERNARD
 */
public interface IProcessEntitlement {
    public void processLeaveEntitlement(LeavePeriods lp);
}
