/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.util;

import com.insol.jpa.entities.Customer;
import com.insol.spring.dao.ICustAdvSearch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 *
 * @author BERNARD
 */
@Aspect
public class SystemAspects {
   //
    @DeclareParents(value = "com.insol.spring.dao.CustomerDao",
    defaultImpl = com.insol.spring.dao.CustAdvSearchImpl.class)
    public ICustAdvSearch custAdvSearch;
    
    private IDocNumGen docNumGen;

    public void setDocNumGen(IDocNumGen docNumGen) {
        this.docNumGen = docNumGen;
    }

    //  @Around("execution(* com.insol.spring.dao.CustomerDao.store(..))")
    @Around("execution(* com.insol.spring.dao.IGenericDao.store(..)) ")
    public void customerBeforeStore(ProceedingJoinPoint jp) throws Throwable {

        Object[] args = jp.getArgs();
      //could try  jp.getTarget().getClass()= CustomerDao

        if (args[0].getClass() == Customer.class) {
            Customer cust = (Customer) args[0];

            if (cust.getCustCode()==null) {

                cust.setCustCode(docNumGen.getCustCode(cust.getCustType()));

            }




            args[0] = cust;

        }

        jp.proceed(args);
    }
}
