/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
//import net.sf.ehcache.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//import javax.persistence.*;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;

/**
 *
 * @Table(
    name="ACTIVE_BAND", 
    uniqueConstraints=
        @UniqueConstraint(columnNames={"active_band_user", "active_band_date"})
)
 * @author BERNARD
 */
@Entity
@Indexed

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"LASTNAME","FIRSTNAME","MIDDLENAME"}))
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name="EMPID")
    private String empId;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name="LASTNAME")
    private String lastName;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="MIDDLENAME")
    private String middleName;
    @Column(name="PHONE_NO")
    private String telephone;
    @Column(name="MARITAL_STATUS")
    private String maritalStatus;
    @Column(name="GENDER")
    private String gender;
    @Column(name="EMAIL")
    private String email;
    @Column(name="CONTRACT_STATUS")   //active or terminated
    private Boolean contractStatus;
    @Temporal(TemporalType.DATE)
    @Column(name="CT_TERMINATION_DT")
    private Date contractTerminationDate;
    @Temporal( TemporalType.DATE)
    @Column(name="EMP_DATE")
    private Date empDate;
    
    @Temporal( TemporalType.DATE )
    @Column(name="DOB")
    private Date dob;
    @JoinColumn(name="JOBTITLE",referencedColumnName="ID")
    @IndexedEmbedded
    @ManyToOne()
    private JobTitle jobTitle;
    @JoinColumn(name="DEPARTMENT",referencedColumnName="ID")
    @IndexedEmbedded
    @ManyToOne()  //meaning many entity from here can map only to one entity from department
    private Department department;
    @JoinColumn(name="PAYGRADE",referencedColumnName="ID")
    @IndexedEmbedded
    @ManyToOne()
    private PayGrade payGrade;
    @JoinColumn(name="EMPLOYMENT_STATUS",referencedColumnName="ID")
    @IndexedEmbedded
    @ManyToOne()
    private EmploymentStatus empStatus;
    @IndexedEmbedded
    @JoinColumn(name="BRANCH",referencedColumnName="ID")
    @ManyToOne()
    private Branches branch;
    @JoinColumn(name="JOB_CATEGORY",referencedColumnName="ID")
    @ManyToOne()
    private JobCategories jobCategory;
    @ManyToOne(optional=true)
    @JoinColumn(name="SUPERVISOR",referencedColumnName="ID")
    
    private Employee supervisor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<EmployeeXEducation> educationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<EmployeeXMembership> membershipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<EmployeeXRelationship> relationshipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<EmployeeXSkills> skillsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<EmployeeHistory> empHistoryCollection;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="employee")
    private Collection<TrainingXEmployees> empTrainingCollection;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="employee")
    private Collection<EmployeeXEntitlementXDetails> empLeaveCollection;

    public Collection<EmployeeXEntitlementXDetails> getEmpLeaveCollection() {
        return empLeaveCollection;
    }

    public void setEmpLeaveCollection(Collection<EmployeeXEntitlementXDetails> empLeaveCollection) {
        this.empLeaveCollection = empLeaveCollection;
    }
    
    

    public Collection<TrainingXEmployees> getEmpTrainingCollection() {
        return empTrainingCollection;
    }

    public void setEmpTrainingCollection(Collection<TrainingXEmployees> empTrainingCollection) {
        this.empTrainingCollection = empTrainingCollection;
    }
    
    

    public Collection<EmployeeHistory> getEmpHistoryCollection() {
        return empHistoryCollection;
    }

    public void setEmpHistoryCollection(Collection<EmployeeHistory> empHistoryCollection) {
        this.empHistoryCollection = empHistoryCollection;
    }
    
    

    public Collection<EmployeeXEducation> getEducationCollection() {
        return educationCollection;
    }

    public void setEducationCollection(Collection<EmployeeXEducation> educationCollection) {
        this.educationCollection = educationCollection;
    }

    public Collection<EmployeeXMembership> getMembershipCollection() {
        return membershipCollection;
    }

    public void setMembershipCollection(Collection<EmployeeXMembership> membershipCollection) {
        this.membershipCollection = membershipCollection;
    }

    public Collection<EmployeeXRelationship> getRelationshipCollection() {
        return relationshipCollection;
    }

    public void setRelationshipCollection(Collection<EmployeeXRelationship> relationshipCollection) {
        this.relationshipCollection = relationshipCollection;
    }

    public Collection<EmployeeXSkills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<EmployeeXSkills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
    

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getEmpDate() {
        return empDate;
    }

    public void setEmpDate(Date empDate) {
        this.empDate = empDate;
    }
    
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
    
    

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public EmploymentStatus getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(EmploymentStatus empStatus) {
        this.empStatus = empStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public PayGrade getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(PayGrade payGrade) {
        this.payGrade = payGrade;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Boolean contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getContractTerminationDate() {
        return contractTerminationDate;
    }

    public void setContractTerminationDate(Date contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
    }
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.firstName+" "+this.lastName;
    }
}
