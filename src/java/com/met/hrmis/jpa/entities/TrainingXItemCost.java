/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"TRAINING", "TRAININGCOSTITEM"}))
public class TrainingXItemCost implements Serializable {
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @JoinColumn(name="TRAININGCOSTITEM",referencedColumnName="ID")
    @ManyToOne
    private TrainingCostItem trainingCostItem;
    @Column(name="ITEM_DESCRIPTION")
    private String itemDescription;
    @Column(name="ITEM_CURRENCY")
    private String currency;
    @Column(name="ITEM_AMOUNT")
    private Double itemAmount;
    @JoinColumn(name="TRAINING",referencedColumnName="ID")
    @ManyToOne
    private Training training;
    

    public TrainingCostItem getTrainingCostItem() {
        return trainingCostItem;
    }

    public void setTrainingCostItem(TrainingCostItem trainingCostItem) {
        this.trainingCostItem = trainingCostItem;
    }
    
    

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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
        if (!(object instanceof TrainingXItemCost)) {
            return false;
        }
        TrainingXItemCost other = (TrainingXItemCost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.met.hrmis.jpa.entities.TrainingXItemCost[ id=" + id + " ]";
    }
    
}
