/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name="COURSE_TITLE")
    private String courseTitle;
    
    @Column(name="START_DATE")
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name="END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name="VENUE")
    private String venue;
    
    @Column(name="FACILITATOR")
    private String facilitator;
    
    @Column(name="TRAINING_TYPE")
    private String trainingType;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "training")
    private Collection<TrainingXEmployees> participantCollection;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="training")
    private Collection<TrainingXItemCost> trainingCostCollection;

    public Collection<TrainingXItemCost> getTrainingCostCollection() {
        return trainingCostCollection;
    }

    public void setTrainingCostCollection(Collection<TrainingXItemCost> trainingCostCollection) {
        this.trainingCostCollection = trainingCostCollection;
    }
    
    

    public Collection<TrainingXEmployees> getParticipantCollection() {
        return participantCollection;
    }

    public void setParticipantCollection(Collection<TrainingXEmployees> participantCollection) {
        this.participantCollection = participantCollection;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }    
    
     
    

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
        if (!(object instanceof Training)) {
            return false;
        }
        Training other = (Training) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.Training[ id=" + id + " ]";
    }
    
}
