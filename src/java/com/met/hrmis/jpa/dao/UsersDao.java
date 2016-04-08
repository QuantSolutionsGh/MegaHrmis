/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.dao;

import com.met.hrmis.jpa.entities.Users;
import java.util.ArrayList;
import javax.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BERNARD
 */
public class UsersDao extends HrParentDao {

    public UsersDao() {
        super(Users.class);

    }

    @Override
    @Transactional
    public void update(Object entity) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users a = ((Users) entity);
        a.setPassword(passwordEncoder.encode(a.getPassword()));



        // String hashedPass = encoder.encodePassword("koala", null);

        //  assertEquals("a564de63c2d0da68cf47586ee05984d7", hashedPass);
        super.update(entity);
    }
}
