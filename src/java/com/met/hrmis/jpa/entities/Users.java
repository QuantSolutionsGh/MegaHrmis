/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.hrmis.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;

/**
 *
 * @author BERNARD
 */
@Entity

@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"USERNAME"}))
public class Users implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
   
    
    
    private static final long serialVersionUID = 1L;
    
    

    @Column(name="USERNAME")
    private String userName;
    @Column(name="USER_FULL_NAME")
    private String userFullName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="ENABLED")
    private int enabled;
    @OneToMany(cascade=CascadeType.ALL,mappedBy="appUser")
    private Collection<Authorities> userAuthorities;

    public Collection<Authorities> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Collection<Authorities> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }
    
    

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return userFullName;
    }

    
    
    

   

    

  
    
}
