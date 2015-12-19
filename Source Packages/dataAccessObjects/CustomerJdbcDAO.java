/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

import Main.JdbcConnection;
import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ryan Campion 2343075
 */
public class CustomerJdbcDAO {
    
    
    
    
    
    
    
    //@Override
    public void save(Customer aCustomer) {
        
        String sql = "merge into CUSTOMERS (username, firstname, lastname, password, email,  creditcard, street, suburb, city, postcode) values (?,?,?,?,?,?,?,?,?,?)";
        
        try (
                //get connection to datbase
                Connection dbCon = JdbcConnection.getConnection();
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            // copy the data from the customer domain object into the SQL parameters
            stmt.setString(1, aCustomer.getUsername());
            stmt.setString(2, aCustomer.getFirstname());
            stmt.setString(3, aCustomer.getLastname());
            stmt.setString(4, aCustomer.getPassword());
            stmt.setString(5, aCustomer.getEmail());
            stmt.setString(6, aCustomer.getCreditCard());
            
            stmt.setString(7, aCustomer.getStreet());
            stmt.setString(8, aCustomer.getSuburb());
            stmt.setString(9, aCustomer.getCity());
            stmt.setInt(10, aCustomer.getPostcode());
           
            

            // execute the statement
            stmt.executeUpdate();

        } catch (SQLException ex) { // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
        
    }
    
    
    
    
    
    public Customer logIn (String usernameInput, String passwordInput){
        
        String sql = "select * from CUSTOMERS where username = ? and password = ? ";
        
         try (
                //get connection to datbase
                Connection dbCon = JdbcConnection.getConnection();
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            // copy the data from the customer domain object into the SQL parameters
            stmt.setString(1, usernameInput);
            stmt.setString(2, passwordInput);
         
           // execute the statement
            ResultSet rs = stmt.executeQuery();
            
            Customer c = new Customer();
            
            // iterate through the query results
            if (rs.next()) {
            // get the data out of the query
            String username  = rs.getString("username");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String creditcard = rs.getString("creditcard");
            String street = rs.getString("street");
            String suburb = rs.getString("suburb");
            String city = rs.getString("city");
            int postcode = rs.getInt("postcode");
            
           
            // use the data to create a customer object          
            c.setUsername(username);
            c.setFirstname(firstname);
            c.setLastname(lastname);
            c.setPassword(password);
            c.setEmail(email);
            c.setCreditCard(creditcard);
            c.setStreet(street);
            c.setSuburb(suburb);
            c.setCity(city);
            c.setPostcode(postcode);
            
            return c;
            }
             
        return null;

        } catch (SQLException ex) { // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
}
