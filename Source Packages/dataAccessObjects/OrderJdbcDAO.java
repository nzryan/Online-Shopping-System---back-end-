/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

import Main.JdbcConnection;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;

/**
 *
 * @author Ryan Campion 2343075
 */
public class OrderJdbcDAO {
    
    public void save(Order order) {
 

	Connection con = null;
	PreparedStatement insertOrderStmt = null;
	PreparedStatement insertOrderItemStmt = null;
	PreparedStatement updateProductStmt = null;

	try {
		con = JdbcConnection.getConnection();

		insertOrderStmt = con.prepareStatement("insert into Orders (OrderDate, CustomerUsername) values (?,?)",Statement.RETURN_GENERATED_KEYS);

		insertOrderItemStmt = con.prepareStatement("insert into OrderItems (Quantity, ProductID, OrderId) values (?,?,?)");

		updateProductStmt = con.prepareStatement("UPDATE Products SET Quantity = Quantity -? WHERE ID = ?");

		// since saving and order involves multiple statements across
		// multiple tables we need to control the transaction ourselves
		// to ensure our DB remains consistent

		// turn off auto-commit which effectively starts a new transaction
		con.setAutoCommit(false);


		// -- save the order --

		// convert the order's java.util.Date into a java.sql.Timestamp
		Timestamp timestamp = new Timestamp(order.getDate().getTime());

		// get the customer's username since it is the FK that links order and customer
		String username = order.getCustomer().getUsername();

		// ****
		// write code here that saves the timestamp and username in the order table
		// using the insertOrderStmt prepared statement
                insertOrderStmt.setTimestamp(1, timestamp);
                insertOrderStmt.setString(2, username);
                
                insertOrderStmt.executeUpdate();
		// ****


		// get the auto-generated order ID from the database
		ResultSet rs = insertOrderStmt.getGeneratedKeys();

		Integer orderId = null;

		if (rs.next()) {
			orderId = rs.getInt(1);
                        order.setOrderID(orderId);
		} else {
			throw new DAOException("Problem getting generated Order ID");
		}

		// -- save the order items --

		Collection<OrderItem> items = order.getItems();

		// ****
		// write code here that iterates through the order items and saves
		// them using the insertOrderItemStmt prepared statement.
                for (OrderItem item : items){
                    
                    insertOrderItemStmt.setInt(1,item.getQuantityPurchased());
                    
                    Product prod = item.getProduct();
                    Integer prodId = prod.getId();
                    insertOrderItemStmt.setInt(2,prodId);                                    
                    
                    insertOrderItemStmt.setInt(3,orderId);
                    
                    insertOrderItemStmt.addBatch();
                    
                }
                insertOrderItemStmt.executeBatch();
		// ****

		// -- update the product quantities --

		for (OrderItem item : items) {

			Product product = item.getProduct();

			// *******************************************************************
			// write code here that updates the product quantity using the
			Integer prodID = product.getId();
                        Integer stockDecrement = item.getQuantityPurchased();
                        
                        updateProductStmt.setInt(1,stockDecrement);
                        updateProductStmt.setInt(2,prodID);
                        
                        updateProductStmt.addBatch();
                        
			// *******************************************************************

		}
                        updateProductStmt.executeBatch();

		// -- commit and clean-up --

		// commit the transaction
		con.commit();

		// turn auto-commit back on
		con.setAutoCommit(true);

		// close the statements and connection
		insertOrderStmt.close();
		insertOrderItemStmt.close();
		updateProductStmt.close();
		con.close();

	} catch (SQLException ex) {

		// something went wrong so rollback the entire transaction
		try {
			con.rollback();
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		}

		// and throw an exception to tell the user what happened
		throw new DAOException(ex.getMessage(), ex);
	}


        
    }
    
}
