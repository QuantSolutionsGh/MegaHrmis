/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.ProductGroup;

/**
 *
 * @author BERNARD
 */
public class ProductGroupDao extends JpaParentDao{

    public ProductGroupDao() {
        super(ProductGroup.class);
    }

    
    
}
