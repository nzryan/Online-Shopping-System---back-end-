/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author Ryan Campion 2343075
 */
public interface ProductDAO {
    
    
     /**
    * Adds a product to the DAO.
    *
    * @param aProduct The product to add.
    */
   void save(Product aProduct);
   
   
    /**
    * removes a product from the DAO.
    *
    * @param aProduct The product to remove.
    */
   void delete(Product aProduct);


   /**
    * Returns all products that have been added to the DAO.
    *
    * @return The collection of products.
    */
   Collection<Product> getAll();
   
   
   
    /**
    * Returns all categories that have been added to the DAO.
    *
    * @return The collection of categories.
    */
   Collection<String> getCategories();
   
   
   /**
    * 
    * 
    * @return Product with following ID
    */
   Product getById (Integer id);
   
   
   /**
    * 
    *
    * @return A collection of products the belong to a certain category
    */
   Collection<Product> getByCategory(String category);
}
