/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.Mail;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author BERNARD
 */
public class Mailer implements ISendMail {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    
    

    @Override
    public void sendMail(final Map model, final Mail mail) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mail.getMailTo());
                message.setFrom(mail.getMailFrom()); // could be parameterized...
                message.setSubject(mail.getMailSubject());
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
