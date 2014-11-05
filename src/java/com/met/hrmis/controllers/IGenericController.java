/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.controllers;

import java.util.Collection;

/**
 *
 * @author BERNARD
 */
public interface IGenericController {
    
    void doNew();
    
    void doSave();
    
    void doEdit(Object obj);
    
    void doCancel();
    
    void doDelete(Object obj);
    
    Collection<? extends Object> getObjList();
    
}
