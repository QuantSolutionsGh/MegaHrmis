/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
public class EmailConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name="MAIL_SENDER")
    private String mailSender;
    @Column(name="EMAILHOST")    
    private String emailHost ;
    @Column(name="EMAILPORT")
    private int emailPort;
    @Column(name="USERNAME")
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="LEAVE_SUBSCRIBERS")
    private String leaveApprovalNotificationSubscribers;
    @Column(name="REMIND_SUBSCRIBERS")
    private String leaveApprovalReminderSubscribers;
    @Column(name="REMINDER_STATUS")
    private Boolean leaveRequestReminderStatus;
    @Column(name="BIRTHDAY_SUBSCRIBERS")
    private String birthdaySubscribers;
    @Column(name="CANCELLATON_SUBSCRIBERS")
    private String leaveCancellationSubscribers;
    @Column(name="APPLICATION_CONTEXT")
    private String appContext;
    @Column(name="COMPANY")
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    

    public String getAppContext() {
        return appContext;
    }

    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }
    
    

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public int getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(int emailPort) {
        this.emailPort = emailPort;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    

    public String getBirthdaySubscribers() {
        return birthdaySubscribers;
    }

    public void setBirthdaySubscribers(String birthdaySubscribers) {
        this.birthdaySubscribers = birthdaySubscribers;
    }

    public String getLeaveCancellationSubscribers() {
        return leaveCancellationSubscribers;
    }

    public void setLeaveCancellationSubscribers(String leaveCancellationSubscribers) {
        this.leaveCancellationSubscribers = leaveCancellationSubscribers;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeaveApprovalNotificationSubscribers() {
        return leaveApprovalNotificationSubscribers;
    }

    public void setLeaveApprovalNotificationSubscribers(String leaveApprovalNotificationSubscribers) {
        this.leaveApprovalNotificationSubscribers = leaveApprovalNotificationSubscribers;
    }

    public String getLeaveApprovalReminderSubscribers() {
        return leaveApprovalReminderSubscribers;
    }

    public void setLeaveApprovalReminderSubscribers(String leaveApprovalReminderSubscribers) {
        this.leaveApprovalReminderSubscribers = leaveApprovalReminderSubscribers;
    }

    public Boolean getLeaveRequestReminderStatus() {
        return leaveRequestReminderStatus;
    }

    public void setLeaveRequestReminderStatus(Boolean leaveRequestReminderStatus) {
        this.leaveRequestReminderStatus = leaveRequestReminderStatus;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmailConfig other = (EmailConfig) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "EmailConfig{" + "id=" + id + ", mailSender=" + mailSender + ", leaveApprovalNotificationSubscribers=" + leaveApprovalNotificationSubscribers + ", leaveApprovalReminderSubscribers=" + leaveApprovalReminderSubscribers + ", leaveRequestReminderStatus=" + leaveRequestReminderStatus + ", birthdaySubscribers=" + birthdaySubscribers + '}';
    }
    
    
    
}
