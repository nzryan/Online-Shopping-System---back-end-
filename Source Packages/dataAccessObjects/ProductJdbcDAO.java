/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

import Main.JdbcConnection;
import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Ryan Campion 2343075
 */
public class ProductJdbcDAO implements ProductDAO{
    
    
    

    @Override
    public void save(Product aProduct) {
        
        String sql = "merge into products (id, name, description, category, price, quantity) values (?,?,?,?,?,?)";
        
        try (
                //get connection to datbase
                Connection dbCon = JdbcConnection.getConnection();
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            // copy the data from the student domain object into the SQL parameters
            stmt.setInt(1, aProduct.getId());
            stmt.setString(2, aProduct.getName());
            stmt.setString(3, aProduct.getDescription());
            stmt.setString(4, aProduct.getCategory());
            stmt.setDouble(5, aProduct.getPrice());
            stmt.setInt(6, aProduct.getQuantity());
           
            

            // execute the statement
            stmt.executeUpdate();

        } catch (SQLException ex) { // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
        
    }

    
    
    
    
    @Override
    public void delete(Product aProduct) {
        
        String sql = "delete from products where id = ?;";
        
         try (
                //get connection to datbase
                Connection dbCon = JdbcConnection.getConnection();
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            
                //put id into the statement
                stmt.setInt(1, aProduct.getId());  
                
                // execute the statement
                stmt.executeUpdate();

        } catch (SQLException ex) { // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    
    
    
    
    @Override
    public Collection<Product> getAll() {
        
         String sql = "select * from products order by id";
    
    try (   // get a connection to the database
            Connection dbCon = JdbcConnection.getConnection();
            // create the statement
             PreparedStatement stmt = dbCon.prepareStatement(sql);
            )
             {
             // execute the query
             ResultSet rs = stmt.executeQuery();
             // Create a collection for holding the products we get from the query. 
             // We are using a List in order to preserve the order in which
             // the data was returned from the query.
             List<Product> products = new ArrayList<>();
             

            // iterate through the query results
            while (rs.next()) {
            // get the data out of the query
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            // use the data to create a student object
            Product p = new Product(id, name, description, category, price, quantity); // and put it in the collection
            products.add(p);
            }
             
        return products;
    }
    
    catch (SQLException ex) {      
         throw new DAOException(ex.getMessage(), ex);
    }
    }

    
    
    
    @Override
    public Collection<String> getCategories() {
        
        String sql = "select distinct category from products order by category";
    
    try (   // get a connection to the database
            Connection dbCon = JdbcConnection.getConnection();
            // create the statement
             PreparedStatement stmt = dbCon.prepareStatement(sql);
            )
       
             {
             // execute the query
             ResultSet rs = stmt.executeQuery();
             
             // Create a collection for holding the product we get from the query. 
             // We are using a List in order to preserve the order in which
             // the data was returned from the query.
             List<String> categories = new ArrayList<>();
             

            // iterate through the query results
            while (rs.next()) {                
            // get the data out of the query
            String category = rs.getString("category");
            // use the data to create a product object
            categories.add(category);
            }
             
        return categories;
       }
    
        catch (SQLException ex) {      
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    
    
    @Override
    public Product getById(Integer id) {
        
         String sql = "select * from products where id = ?;";
    
    try (   // get a connection to the database
            Connection dbCon = JdbcConnection.getConnection();
            // create the statement
             PreparedStatement stmt = dbCon.prepareStatement(sql);
            )
       
             {
                 
            //put id into the statement
            stmt.setInt(1, id);     
                 
             // execute the query
             ResultSet rs = stmt.executeQuery();
             
             //make product object to fill
             Product p = new Product();
         
            
           
                 
            // iterate through the query results
            if (rs.next()) {
            // get the data out of the query
            String name = rs.getString("name");
            String desciption = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            
            // use the data to create a product object
            p.setId(id);
            p.setName(name);
            p.setDescription(desciption);
            p.setCategory(category);
            p.setPrice(price);
            p.setQuantity(quantity);
            
            return p;
            }
             
        return null;
       }
             
    
        catch (SQLException ex) {      
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    
    
    
    
    
    
    
    
    
    @Override
    public Collection<Product> getByCategory(String catagoryInput) {
        
        
        String sql = "select * from products where category = ? order by id;";
        
        
        try (   // get a connection to the database
            Connection dbCon = JdbcConnection.getConnection();
            // create the statement
             PreparedStatement stmt = dbCon.prepareStatement(sql);
            )
       
             {
                 
             //put id into the statement
             stmt.setString(1, catagoryInput);     
                 
             // execute the query
             ResultSet rs = stmt.executeQuery();
             
             // Create a collection for holding the product we get from the query. 
             // We are using a List in order to preserve the order in which
             // the data was returned from the query.
             List<Product> productsUnderCatagory = new ArrayList<>();
             
            // iterate through the query results
            while (rs.next()) {
            // get the data out of the query
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            // use the data to create a student object
            Product p = new Product(id, name, description, category, price, quantity); // and put it in the collection
            productsUnderCatagory.add(p);
            }
            
            return productsUnderCatagory;
    }
    
    catch (SQLException ex) {      
         throw new DAOException(ex.getMessage(), ex);
    }
    }
    
}
