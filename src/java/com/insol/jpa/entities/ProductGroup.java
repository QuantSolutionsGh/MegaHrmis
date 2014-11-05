/*
 * To change this template, choose Tools | Templates
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
 * 
 * can be L = Loans  or S=Savings
 */
@Entity
@Table(name = "PRODUCTGROUP")
public class ProductGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String Id;
    @Column(name = "PRODUCT_GROUP_CODE", unique = true)
    private String prodGroupCode;
    @Column(name = "PRODUCT_GROUP_DESCRIPTION")
    private String prodGroupDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productGroup")
    private Collection<Product> productCollection;

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }
    
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getProdGroupCode() {
        return prodGroupCode;
    }

    public void setProdGroupCode(String prodGroupCode) {
        this.prodGroupCode = prodGroupCode;
    }

    public String getProdGroupDescription() {
        return prodGroupDescription;
    }

    public void setProdGroupDescription(String prodGroupDescription) {
        this.prodGroupDescription = prodGroupDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductGroup other = (ProductGroup) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    @Override
    public String toString() {
        return prodGroupDescription;
    }
    
    
    
}
