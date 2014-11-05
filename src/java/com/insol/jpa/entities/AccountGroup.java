/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author BERNARD
 */
@Entity
@Table(name = "ACCOUNTGROUP")
public class AccountGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name = "GROUP_CODE", unique = true)
    private String groupCode;
    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountGroup")
    private Collection<GLAccount> glAccountCollection;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Collection<GLAccount> getGlAccountCollection() {
        return glAccountCollection;
    }

    public void setGlAccountCollection(Collection<GLAccount> glAccountCollection) {
        this.glAccountCollection = glAccountCollection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountGroup other = (AccountGroup) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return groupDescription;
    }
}
