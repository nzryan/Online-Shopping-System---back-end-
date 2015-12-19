/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import dataAccessObjects.ProductDAO;
import domain.Product;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ryan Campion 2343075
 */
public class ProductDetailsTest {
    
    
    private ProductDAO dao;
    private DialogFixture fest;
    
    
    
    
    public ProductDetailsTest() {
    }
    
    
    
    
    @Before
    public void setUp() {
        
        // add some categories for testing with
         Collection<String> categories = new TreeSet<>();
         
        categories.add("Toyota");
        categories.add("BMW");
        categories.add("Mitsubishi");
            
       // create a mock for the DAO
       dao = mock(ProductDAO.class);
       
       // stub the getMajors method to return the test majors
       when(dao.getCategories()).thenReturn(categories);
    }
    
    
    
    
    
    
     @After
    public void tearDown() {
        
        // clean up FEST so that it is ready for the next test
        fest.cleanUp();
    }
    
    
    
    
    
    
    
    @Test
  public void testEdit() {
      
// a product to edit
Product garrett = new Product(1234, "Trueno", "4age", "Toyota", 6000.50, 30); 

// create dialog passing in product and mocked DAO
ProductDetails dialog = new ProductDetails(null, true, garrett, dao);

     
     
// use FEST to control the dialog
     fest = new DialogFixture(dialog);
     fest.show();
     
// slow down the robot a bit − default is 30 millis 
     fest.robot.settings().delayBetweenEvents(300);

// ensure that the UI componenents contains the products details
     fest.textBox("txtId").requireText("1234");
     fest.textBox("txtName").requireText("Trueno");
     fest.textBox("txtAreaDescription").requireText("4age");
     fest.comboBox("cmbCategory").requireSelection("Toyota");
     fest.textBox("txtPrice").requireText("6000.50");
     fest.textBox("txtQuantity").requireText("30");
     
// edit everything but ID
     fest.textBox("txtName").selectAll().enterText("Evo4");
     fest.textBox("txtAreaDescription").selectAll().enterText("4wd");
     fest.comboBox("cmbCategory").selectItem("Mitsubishi");
     fest.textBox("txtPrice").enterText("0.00").selectAll().enterText("1500.00");
     fest.textBox("txtQuantity").enterText("0").selectAll().enterText("50");
     
// click the save button
     fest.button("btnSave").click();
     
// create a captor to use to retrieve the passed product from the mocked DAO
     ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
     
// ensure that the DAO save method was called, and capture the passed product
     verify(dao).save(argument.capture());
     
// retrieve the passed product from the captor
     Product edited = argument.getValue();
     
// ensure that the changes were saved
     assertEquals("Ensure the name changed", "Evo4", edited.getName());
     assertEquals("Ensure the description changed", "4wd", edited.getDescription());
     assertEquals("Ensure the category changed", "Mitsubishi", edited.getCategory());
     assertEquals("Ensure the price changed", new Double(1500.00), edited.getPrice());
     assertEquals("Ensure the quantity changed", new Integer(50), edited.getQuantity());
  }
    
    
  
  
  
  
  @Test
  public void testSave() {

// create the dialog passing in the mocked DAO
     ProductDetails dialog = new ProductDetails(null, true, dao);
     
// use FEST to control the dialog
     fest = new DialogFixture(dialog);
     fest.show();
     
// slow down the FEST robot a bit − default is 30 millis 
     fest.robot.settings().delayBetweenEvents(70);
     
// TODO: enter some details into the UI components (use enterText and selectItem) 
     fest.textBox("txtId").enterText("325325");
     fest.textBox("txtName").enterText("M3");
     fest.textBox("txtAreaDescription").enterText("inline 4");
     fest.comboBox("cmbCategory").selectItem("BMW");
     fest.textBox("txtPrice").enterText("8000.00");
     fest.textBox("txtQuantity").enterText("34");

// TODO: click the save button
     fest.button("btnSave").click();
     
// create a captor to use to retrieve the passed product from the mocked DAO
     ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

// TODO: verify that the DAO save method was called, and capture the passed student
     verify(dao).save(argument.capture());

// TODO: retrieve the passed product from the captor
     Product edited = argument.getValue();

// TODO: ensure that the product were properly saved
     assertEquals("Ensure the id added", new Integer(325325), edited.getId());
     assertEquals("Ensure the name added", "M3", edited.getName());
     assertEquals("Ensure the description added", "inline 4", edited.getDescription());
     assertEquals("Ensure the category added", "BMW", edited.getCategory());
     assertEquals("Ensure the price added", new Double(8000.00), edited.getPrice());
     assertEquals("Ensure the quantity added", new Integer(34), edited.getQuantity());


}
  
  
  
    
    
    
}
