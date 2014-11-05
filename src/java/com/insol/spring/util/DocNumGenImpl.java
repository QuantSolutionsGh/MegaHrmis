/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.util;

import com.insol.jpa.entities.CustomerClass;
import com.insol.spring.dao.IGenericDao;

/**
 *
 * @author BERNARD
 */
public class DocNumGenImpl implements IDocNumGen {

    private IGenericDao custClassDao;
    private CustomerClass custClass;

    public void setCustClassDao(IGenericDao custClassDao) {
        this.custClassDao = custClassDao;
    }

    @Override
    public synchronized String getCustCode(CustomerClass custClass) {
        //  this.custClass = (CustomerClass) custClassDao.find(custClass.getId());
        // custClassDao.getJpaTemplate().getEntityManager().lock(custClass,LockModeType.READ);
        this.custClass = (CustomerClass) custClassDao.find(custClass.getId());
        String curValue = this.custClass.getCurrentValue().toString();

        //get no of zeros to use in formatter beow
      //  Integer zeroes = custClass.getNoOfDigits() - curValue.length() + 1;


        //   String padded=String.format("%0"+zeroes.toString()+"d",this.custClass.getCurrentValue() );

        String custCode = this.custClass.getCustClassPrefix().concat(String.format("%0"
                + this.custClass.getNoOfDigits().toString() + "d", this.custClass.getCurrentValue()));

        this.custClass.setCurrentValue(this.custClass.getCurrentValue() + 1);


        return custCode;
        //   return custC
    }
}
