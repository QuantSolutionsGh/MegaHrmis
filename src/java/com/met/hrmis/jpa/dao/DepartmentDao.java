/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Department;

/**
 *
 * @author BERNARD
 */
public class DepartmentDao extends HrParentDao {

    public DepartmentDao() {
        super(Department.class);
    }
}
