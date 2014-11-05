/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author BERNARD
 */
public class UserStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        return null;  //dummy
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {


        if (o.toString().contains("1")) {
            return "Active";
        } else {
            return "Inactive";
        }

    }
}
