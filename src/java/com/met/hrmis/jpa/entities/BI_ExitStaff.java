/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author BERNARD
 */
@Entity
public class BI_ExitStaff implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name="BI_YEAR")
    private int year;
    @Column(name="BI_MONTH")
    private int month;
    @Column(name="BI_COUNT")
    private int count;
    @JoinColumn(name="JOBTITLE",referencedColumnName="ID")
    
    @ManyToOne()
    private JobTitle jobTitle;
    @Column(name="GENDER")
    private String gender;
    
    @JoinColumn(name="DEPARTMENT",referencedColumnName="ID")
    
    @ManyToOne()  //meaning many entity from here can map only to one entity from department
    private Department department;
    @JoinColumn(name="PAYGRADE",referencedColumnName="ID")
   
    @ManyToOne()
    private PayGrade payGrade;
    @JoinColumn(name="EMPLOYMENT_STATUS",referencedColumnName="ID")
    
    @ManyToOne()
    private EmploymentStatus empStatus;
   
    @JoinColumn(name="BRANCH",referencedColumnName="ID")
    @ManyToOne()
    private Branches branch;
    @JoinColumn(name="TERMINATION_REASON", referencedColumnName="ID")
    @ManyToOne()
    private TerminationReason terminationReason;
    @JoinColumn(name="JOB_CATEGORY",referencedColumnName="ID")
    @ManyToOne()
    private JobCategories jobCategory;
    @ManyToOne(optional=true)
    @JoinColumn(name="SUPERVISOR",referencedColumnName="ID")
    
    private Employee supervisor;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmploymentStatus getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(EmploymentStatus empStatus) {
        this.empStatus = empStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobCategories getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategories jobCategory) {
        this.jobCategory = jobCategory;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public PayGrade getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(PayGrade payGrade) {
        this.payGrade = payGrade;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public TerminationReason getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(TerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.BI_StaffDevelopment[ id=" + id + " ]";
    }
    
}
