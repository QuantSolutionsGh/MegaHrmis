/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.entities.EmailConfig;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.Mail;
import java.util.*;
import javax.persistence.Query;

/**
 *
 * @author BERNARD
 */

public class BirthdayNotifier implements INotifier {

    private IEntityManagerFactory emFactory;
    
    private ISendMail mailer;
    
    private EmailConfig emailConfig;
    
       
    

   

    public void setMailer(ISendMail mailer) {
        this.mailer = mailer;
    }
    
    

    public void setEmFactory(IEntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
  //  @Scheduled(cron = "0 0 8 * * ?")
    
   // @Scheduled(cron="0 */10 * * * ?")
    public void alert() {
        
        //ok get email config details
        this.emailConfig = (EmailConfig) emFactory.getEntityManager().
                    createQuery("select e from EmailConfig e").getResultList().get(0);
        

        Calendar now = Calendar.getInstance();
        
        int month = now.get(Calendar.MONTH)+1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        
        Query query = emFactory.getEntityManager().createQuery("select e from Employee e where "
                + "month(e.dob) = :month and day(e.dob)= :day");
        query.setParameter("month", month);
        query.setParameter("day", day);

        ArrayList<Employee> qResult = (ArrayList<Employee>) query.getResultList();
        

       
        Map model = new HashMap();
        model.put("staffList", qResult.toString());

        Mail mail = new Mail();
        mail.setMailFrom(emailConfig.getMailSender());
        mail.setMailTo(emailConfig.getBirthdaySubscribers());
        mail.setMailSubject("Birthday celebrants for today");
        mail.setTemplateName("/velocity/birthday_celebrants.vm");

        mail.setMailSendDate(new Date());
        //  mailer.sendMail(empLeaveDetails, mail);
        try {
            mailer.sendMail(model, mail);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
