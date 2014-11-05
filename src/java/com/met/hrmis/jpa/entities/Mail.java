/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.util.Date;

/**
 *
 * @author BERNARD
 */
public class Mail {

    private String mailFrom;
    private Date mailSendDate;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String templateName;
    private String contentType;

    public Mail() {
        contentType = "text/html";
    }

    public Date getMailSendDate() {
        return mailSendDate;
    }

    public void setMailSendDate(Date mailSendDate) {
        this.mailSendDate = mailSendDate;
    }
    
    

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        StringBuilder lBuilder = new StringBuilder();
        lBuilder.append("Mail From:- ").append(getMailFrom());
        lBuilder.append("Mail To:- ").append(getMailTo());
        lBuilder.append("Mail Cc:- ").append(getMailCc());
        lBuilder.append("Mail Bcc:- ").append(getMailBcc());
        lBuilder.append("Mail Subject:- ").append(getMailSubject());
        lBuilder.append("Mail Send Date:- ").append(getMailSendDate());
        lBuilder.append("Mail Content:- ").append(getMailContent());
        return lBuilder.toString();
    }
}
