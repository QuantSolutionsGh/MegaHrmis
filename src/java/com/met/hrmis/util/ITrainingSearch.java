/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.entities.Training;
import java.util.Collection;

/**
 *
 * @author BERNARD
 */
public interface ITrainingSearch {
    
    public Collection<Training> search(String queryString);
    
}
