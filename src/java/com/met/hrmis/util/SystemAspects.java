/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author BERNARD
 */
@Aspect
public class SystemAspects {
    //

    //  @Around("execution(* com.insol.spring.dao.CustomerDao.store(..))")
    // @Around("execution(public * *(..))")
    @Around("execution(* com.met.hrmis.controllers.IGenericController.*(..))")
   
    public void SystemLogger(ProceedingJoinPoint jp) throws Throwable {

        try {
            System.out.println("Before...........................................................");
            jp.proceed();
            System.out.println("After.............................................................");
        } catch (Exception e) {

            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Error :" + jp.getSourceLocation().getLine());
            

    }
}
}
