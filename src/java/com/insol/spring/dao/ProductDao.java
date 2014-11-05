/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insol.spring.dao;

import com.insol.jpa.entities.Product;

/**
 *
 * @author BERNARD
 */
public class ProductDao extends JpaParentDao{
     public ProductDao() {
        super(Product.class);
    }
}
