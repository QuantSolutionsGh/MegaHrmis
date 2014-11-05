/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

/**
 *
 * @author BERNARD
 */
public class EmailConfiguration {
    private String mailSender;   //account to use to send email
    private String leaveRequestNotificationSubscribers;
    private String leaveApprovalNotificationSubscribers;
    private String leaveRejectionNotificationSubscribers;
    private String leaveCancellationNoficationSubscribers;
    private String leaveApprovalReminderNotificationSubscribers;
    private String birthdayNotificationSubscribers;
    private Boolean leaveRequestReminderActive;

    public Boolean getLeaveRequestReminderActive() {
        return leaveRequestReminderActive;
    }

    public void setLeaveRequestReminderActive(Boolean leaveRequestReminderActive) {
        this.leaveRequestReminderActive = leaveRequestReminderActive;
    }
    
    

    public String getLeaveApprovalNotificationSubscribers() {
        return leaveApprovalNotificationSubscribers;
    }

    public void setLeaveApprovalNotificationSubscribers(String leaveApprovalNotificationSubscribers) {
        this.leaveApprovalNotificationSubscribers = leaveApprovalNotificationSubscribers;
    }

    public String getLeaveApprovalReminderNotificationSubscribers() {
        return leaveApprovalReminderNotificationSubscribers;
    }

    public void setLeaveApprovalReminderNotificationSubscribers(String leaveApprovalReminderNotificationSubscribers) {
        this.leaveApprovalReminderNotificationSubscribers = leaveApprovalReminderNotificationSubscribers;
    }

    public String getLeaveCancellationNoficationSubscribers() {
        return leaveCancellationNoficationSubscribers;
    }

    public void setLeaveCancellationNoficationSubscribers(String leaveCancellationNoficationSubscribers) {
        this.leaveCancellationNoficationSubscribers = leaveCancellationNoficationSubscribers;
    }

    public String getLeaveRejectionNotificationSubscribers() {
        return leaveRejectionNotificationSubscribers;
    }

    public void setLeaveRejectionNotificationSubscribers(String leaveRejectionNotificationSubscribers) {
        this.leaveRejectionNotificationSubscribers = leaveRejectionNotificationSubscribers;
    }

    public String getLeaveRequestNotificationSubscribers() {
        return leaveRequestNotificationSubscribers;
    }

    public void setLeaveRequestNotificationSubscribers(String leaveRequestNotificationSubscribers) {
        this.leaveRequestNotificationSubscribers = leaveRequestNotificationSubscribers;
    }

    public String getBirthdayNotificationSubscribers() {
        return birthdayNotificationSubscribers;
    }

    public void setBirthdayNotificationSubscribers(String birthdayNotificationSubscribers) {
        this.birthdayNotificationSubscribers = birthdayNotificationSubscribers;
    }
    
    

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }
    
    
    
}
