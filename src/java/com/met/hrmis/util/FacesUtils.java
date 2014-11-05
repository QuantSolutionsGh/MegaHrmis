/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author BERNARD
 */
public class FacesUtils {
    
   

    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

   
    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
   public static int getWorkingDaysBetweenTwoDates(Date startDate,Date endDate){
       Calendar startCal = Calendar.getInstance();
       startCal.setTime(startDate);
       
       Calendar endCal = Calendar.getInstance();
       endCal.setTime(endDate);
       
       int workDays=0;
       
       if (startCal.getTime()==endCal.getTime()){
           return 0;
       }
       
       do{
           startCal.add(Calendar.DAY_OF_MONTH,1);
           if ((startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY) && (startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)){
              ++workDays; 
           }
           
       } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
       return workDays;
   }
    
}
