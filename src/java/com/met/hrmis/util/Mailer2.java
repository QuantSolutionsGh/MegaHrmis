/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.entities.EmailConfig;
import com.met.hrmis.jpa.entities.Mail;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author BERNARD
 */
public class Mailer2 implements ISendMail {

    private org.springframework.mail.javamail.JavaMailSenderImpl mailSender;
    private VelocityEngine velocityEngine;
    private IEntityManagerFactory emFactory;

    public void setEmFactory(IEntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public org.springframework.mail.javamail.JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(org.springframework.mail.javamail.JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public synchronized void sendMail(final Map model, final Mail mail) {

        final EmailConfig emailConfig = (EmailConfig) emFactory.getEntityManager().
                createQuery("select e from EmailConfig e").getResultList().get(0);
        this.mailSender.setHost(emailConfig.getEmailHost());
        this.mailSender.setUsername(emailConfig.getUserName());
        this.mailSender.setPassword(emailConfig.getPassword());
        this.mailSender.setPort(emailConfig.getEmailPort());

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mail.getMailTo());
                message.setFrom(mail.getMailFrom()); // could be parameterized...
                message.setSubject(mail.getMailSubject());

                List<String> addresses = Arrays.asList(emailConfig.getLeaveApprovalNotificationSubscribers().split("\\s*,\\s*"));

                for (String address : addresses) {
                    message.addCc(address);
                }

               

                message.setSentDate(mail.getMailSendDate());
                //  Map model = new HashMap();
                // model.put("parameter", parameter);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, mail.getTemplateName(), model);
                message.setText(text, true);
            }
        };







        this.mailSender.send(preparator);


    }
}
