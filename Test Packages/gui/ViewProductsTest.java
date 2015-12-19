/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import dataAccessObjects.ProductDAO;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.util.Collection;
import java.util.TreeSet;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ryan Campion 2343075
 */
public class ViewProductsTest {
    
    
    
   private Collection<Product> products;
   private Product wrx;
   private Product supra;
   private ProductDAO dao;
   private ViewProducts dialog;
   private DialogFixture fest;
   
   
   
   
   public ViewProductsTest() {
    }
   
   
   
   
    @Before
    public void setUp() {
        
        // create some products for testing with
   wrx = new Product(64363, "WRX", "grippy", "Subaru", 10000.00, 150);
   supra = new Product(9898, "Supra", "high tourqe", "Toyota", 20000.00, 44);
   
// add the products to a collection for testing with
   products = new TreeSet<>();
   products.add(wrx);
   products.add(supra);
   
// create a mock for the DAO
dao = mock(ProductDAO.class);

// stub the getAll method to return the test products collection
   when(dao.getAll()).thenReturn(products);
   
// stub the getById method to return the appropriate test product based on the passed ID
   when(dao.getById(64363)).thenReturn(wrx);
   when(dao.getById(9898)).thenReturn(supra);
   
// create dialog passing mocked DAO
   dialog = new ViewProducts(null, true, dao);
   
    }
    
    
    
    
    
    
    
    
     @After
    public void tearDown() {
        // clean up FEST so that it is ready for the next test
        fest.cleanUp();
    }
    
    
    
    
    @Test
   public void testReportView() {
       
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
// slow down the FEST robot a bit − default is 30 millis 
      fest.robot.settings().delayBetweenEvents(30);
      
      // ensure getAll was called
      verify(dao).getAll();
      
// get the JList's model
      SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
      
// check that the model actually contains the products
      assertTrue("Ensure list contain the WRX", model.contains(wrx));
      assertTrue("Ensure list contain Supra", model.contains(supra));
      assertTrue("Ensure list contains the correct number of products", model.getSize() == products.size());
    
   }
   
   
   
   
   
   
   
   @Test
   public void testReportDelete() {

// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      
// slow down the FEST robot a bit − default is 30 millis 
      fest.robot.settings().delayBetweenEvents(30);
      
// select tires in the list
      fest.list().selectItem(wrx.toString());
      
// click the delete button
      fest.button("btnDelete").click();
      
// ensure a confirmation dialog is displayed
      DialogFixture confirmDialog = fest.dialog(withTitle("Select an Option").andShowing()).requireVisible();
      
// click the No button on the confirmation dialog
confirmDialog.button(withText("No")).click();

// verify that the delete method did NOT get called on the mock DAO
verify(dao, never()).delete(null); 

// select tires again
      fest.list().selectItem(wrx.toString());
      
// click the delete button again
      fest.button("btnDelete").click();
      
// ensure a confirmation dialog is displayed again
      confirmDialog = fest.dialog(withTitle("Select an Option").andShowing()).requireVisible();
      
// click the Yes button on the confirmation dialog
      confirmDialog.button(withText("Yes")).click();
      
// create a captor to use to retrieve the passed product from the mocked DAO
      ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
      
// verify that the DAO delete method was called, and capture the passed product
      verify(dao).delete(argument.capture());
   
   }
   
   
   
   
   
   
   
   
   @Test
   public void testReportSearch() {
       
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit − default is 30 millis 
      fest.robot.settings().delayBetweenEvents(70);
      
// TODO: enter The Intercoolers's ID into the search box
      fest.textBox("txtSearchID").enterText("9898");
      
// TODO: click the search button
      fest.button("btnSearch").click();
      
// ensure that getById was called and was passed The Intercoolers's ID
      verify(dao).getById(supra.getId());
      
// TODO: get the JList's model
      SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
      
// TODO: ensure that the list is displaying ONLY The Intercooler
      assertTrue("Ensure list contain Supra", model.contains(supra));
      assertTrue("List only contains Supra", model.getSize() == 1);
}
   
   
   
   
   
   
      @Test
   public void testReportEdit() {
       
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
// slow down the FEST robot a bit − default is 30 millis 
      fest.robot.settings().delayBetweenEvents(70);
      
// TODO: select tires in the list 
fest.list().selectItem(wrx.toString());

// TODO: click the edit button
fest.button("btnEdit").click();
      
// ensure that the PRoduct Details appears
DialogFixture editDialog = fest.dialog("ProductDetails").requireVisible();

// TODO: ensure the PRoduct Details contains the correct product
     editDialog.textBox("txtId").requireText("64363");
     editDialog.textBox("txtName").requireText("WRX");
     editDialog.textBox("txtAreaDescription").requireText("grippy");
     editDialog.comboBox("cmbCategory").requireSelection("Subaru");
     editDialog.textBox("txtPrice").requireText("10000.00");
     editDialog.textBox("txtQuantity").requireText("150");

   }
    
}
