/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Training;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class TrainingDao extends HrParentDao {

    public TrainingDao() {
        super(Training.class);
    }

    @Override
    @Transactional
    public Object find(Object id) {
        Training training = (Training) super.find(id);
        Hibernate.initialize(training.getParticipantCollection());
        Hibernate.initialize(training.getTrainingCostCollection());
        return training;
    }
}
