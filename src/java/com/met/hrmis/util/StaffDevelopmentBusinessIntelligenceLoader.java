/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.util;

import com.met.hrmis.jpa.dao.IEntityManagerFactory;
import com.met.hrmis.jpa.dao.IHrGenericDao;
import com.met.hrmis.jpa.entities.*;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class StaffDevelopmentBusinessIntelligenceLoader implements IBusinessIntelligenceLoader {

    private IEntityManagerFactory emFactory;
    private IHrGenericDao branchDao;
    private IHrGenericDao departmentDao;
    private IHrGenericDao payGradeDao;
    private IHrGenericDao jobTitleDao;
    private IHrGenericDao BI_StaffDevelopmentDao;

    public void setBI_StaffDevelopmentDao(IHrGenericDao BI_StaffDevelopmentDao) {
        this.BI_StaffDevelopmentDao = BI_StaffDevelopmentDao;
    }

    public void setBranchDao(IHrGenericDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setDepartmentDao(IHrGenericDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setJobTitleDao(IHrGenericDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    public void setPayGradeDao(IHrGenericDao payGradeDao) {
        this.payGradeDao = payGradeDao;
    }

    public void setEmFactory(IEntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    @Transactional
    public void populate() {

        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int year = now.get(Calendar.YEAR);


        //first delete any entries for the  month and year
        Query query = emFactory.getEntityManager().
                createQuery("delete from BI_StaffDevelopment b where b.year=:year and b.month=:month");
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.executeUpdate();

        //populate with current values   
        
        //active staff only




        Query query2 = emFactory.getEntityManager().
                createQuery("select e.branch.id,e.department.id,e.jobTitle.id,e.payGrade.id,e.gender,count(e) "
                + "from Employee e where e.contractTerminationDate =null"
                + " group by e.branch.id,e.department.id,e.jobTitle.id,e.payGrade.id,e.gender");

        List<Object[]> results = query2.getResultList();
        for (Object[] object : results) {
            Branches branch = (Branches) branchDao.find(object[0]);
            Department department = (Department) departmentDao.find(object[1]);
            JobTitle jobTitle = (JobTitle) jobTitleDao.find(object[2]);
            PayGrade payGrade = (PayGrade) payGradeDao.find(object[3]);

            BI_StaffDevelopment sd = new BI_StaffDevelopment();
            sd.setBranch(branch);
            sd.setDepartment(department);
            sd.setPayGrade(payGrade);
            sd.setJobTitle(jobTitle);
            sd.setGender((String)object[4]);
            sd.setCount(((Long)object[5]).intValue());
            sd.setYear(year);
            sd.setMonth(month);
            BI_StaffDevelopmentDao.store(sd);


            // System.out.println(object[0] + " " + object[1]);
        }



    }
}
