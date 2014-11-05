/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.converters;

import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.Users;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author BERNARD
 */
public class UserConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
         try {

             IHrGenericDao userDao=(IHrGenericDao)FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("usersDao");
             Users a = null;
            List<Users> allUsers = (List<Users>) userDao.findAll();
            if (string.trim().equals("")) {
                return null;
            } else {
                for (Users b : allUsers) {
                    if (b.getUserFullName().equals(string)) {

                        return b;
                    }

                }
                // System.out.println("am here");
                return a;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Users)o).getUserFullName();
    }
    
}
