/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Authorities;
import com.met.hrmis.jpa.entities.Users;
import java.util.ArrayList;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bernard
 */
public class SupportUserCreator implements ICreateUsers{
    
    public IHrGenericDao dao;  //this is for the userDao
    
    public IHrGenericDao authoritiesDao;

    public void setAuthoritiesDao(IHrGenericDao authoritiesDao) {
        this.authoritiesDao = authoritiesDao;
    }
    
    

    public void setDao(IHrGenericDao dao) {
        this.dao = dao;
    }
    
    

    @Override
    @Transactional
    public void createSupportUser() {
         Query query = this.dao.getEntityManager().createQuery("select e from Users e where"
                + " e.userName= :user");
        query.setParameter("user", "Support");
        
        ArrayList<Users> qResult = (ArrayList<Users>) query.getResultList();
        
       
        
        if (qResult.isEmpty()){
            Users a= new Users();
            a.setUserName("Support");
            a.setUserFullName("Support");
            a.setEnabled(1);
            
            a.setPassword("koala");
            
            
            
            
            
            this.dao.store(a);
            this.dao.update(a);
            
            Authorities au= new Authorities();
            
            au.setAppUser(a);
            au.setAuthority("ROLE_SUPERVISOR");
            
            this.authoritiesDao.store(au);
            
                    
        }

        


       
    
    }
    
}
