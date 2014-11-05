/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.Mail;
import java.util.Map;

/**
 *
 * @author BERNARD
 */
public interface ISendMail {
    
    public void sendMail(final Map model, Mail mail);
}
