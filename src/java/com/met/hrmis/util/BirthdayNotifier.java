/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.entities.EmailConfiguration;
import com.met.hrmis.jpa.entities.Employee;
import com.met.hrmis.jpa.entities.Mail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author BERNARD
 */
public class BirthdayNotifier implements IBirthdayNotifier {

    private IEntityManagerFactory emFactory;

    public void setEmFactory(IEntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    @Scheduled(cron = "0 0 8 * * ?")
    public void notifyHrOfBirthdayCelebrants() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Query query = emFactory.getEntityManager().createQuery("select e from Employee e where "
                + "e.dob = :dob");
        query.setParameter("dob", dateFormat.format(new Date()));  //we always compare with the current date

        ArrayList<Employee> qResult = (ArrayList<Employee>) query.getResultList();

        EmailConfiguration config = (EmailConfiguration) FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("emailConfiguration");
        ISendMail mailer = (ISendMail) FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean("mailer");

        Map model = new HashMap();
        model.put("staffList", qResult.toString());

        Mail mail = new Mail();
        mail.setMailFrom(config.getMailSender());
        mail.setMailTo(config.getBirthdayNotificationSubscribers());
        mail.setMailSubject("Birthday celebrants for today");
        mail.setTemplateName("/velocity/birthdayCelebrants.vm");
        
        mail.setMailSendDate(new Date());
        //  mailer.sendMail(empLeaveDetails, mail);
        try {
            mailer.sendMail(model, mail);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
