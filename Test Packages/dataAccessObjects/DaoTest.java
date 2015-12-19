/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

import domain.Product;
import java.util.Collection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ryan Campion 2343075
 */
public class DaoTest {
    
    
    private ProductDAO dao = new ProductCollectionsDAO();
    private int silviaID = 11111;
    private Product silvia;
    private int s2000ID = 22222;
    private Product s2000;
    
    
    
    
    
    
    public DaoTest() {
    }
    
    
    
    
    
    
    @Before
    public void setUp() {

        silvia = new Product(silviaID, "Silvia", "FR turbo RWD", "Nissan", 8000.00, 3);
        s2000 = new Product(s2000ID, "S2000", "FR Vtec RWD", "Honda", 18000.50, 20);
        dao.save(silvia);
        dao.save(s2000);

    }
    
    
    
    @After
    public void tearDown() {

        dao.delete(silvia);
        dao.delete(s2000);

    }
    
    
    
    
    @Test
    public void testDaoSaveAndDelete() {
        
        // create product for testing
        Product rx7 = new Product(33333, "Rx7", "Rotary, 50/50 weight distribution", "Mazda", 18000.00, 10); 

        // save the product using DAO
        dao.save(rx7);
        
         // retrieve the same product via DAO
        Product retrieved = dao.getById(33333);
        
        // ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same as the saved one", rx7, retrieved);
        
        // delete the product via the DAO
        dao.delete(rx7);
        
        // try to retrieve the deleted product
        retrieved = dao.getById(33333);
        
        // ensure that the product was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
        
    }
    
    
    
    
    
    
    
    
     @Test
   public void testDaoGetAll() {
      
      // call getAll
      Collection<Product> products = dao.getAll();
      
      // ensure the result includes the test products
      assertTrue("Silvia should exist in the result", products.contains(silvia));
      assertTrue("s2000 should exist in the result", products.contains(s2000));
       
     // find FatBob − result is not a map, so we have to sequentially search 
      for (Product p : products) {
      if (p.equals(silvia)) {
       // ensure that FatBobs's details were correctly retrieved 
          assertEquals(silvia.getId(), p.getId()); 
          assertEquals(silvia.getName(), p.getName()); 
          assertEquals(silvia.getDescription(), p.getDescription());
          assertEquals(silvia.getCategory(), p.getCategory());
          assertEquals(silvia.getPrice(), p.getPrice());
          assertEquals(silvia.getQuantity(), p.getQuantity());
} }
}
   
   
   
   
   
   @Test
   public void testDaoGetById() {
// get FatBob using getById method
       Product isItSilvia = dao.getById(silviaID);
       
// ensure that we got back Fatbob and not another product
       assertEquals("Retrieved student be Silvia", silvia, isItSilvia);
              
// ensure that FatBob's details were properly retrieved (which // indirectly tests that the details were properly saved).
        assertEquals(silvia.getId(), isItSilvia.getId()); 
        assertEquals(silvia.getName(), isItSilvia.getName()); 
        assertEquals(silvia.getDescription(), isItSilvia.getDescription());
        assertEquals(silvia.getCategory(), isItSilvia.getCategory());
        assertEquals(silvia.getPrice(), isItSilvia.getPrice());
        assertEquals(silvia.getQuantity(), isItSilvia.getQuantity());
       
// call getById using a non−existent ID // ensure that the result is null
        Integer fakeId = 1234;
        assertEquals("Making sure a non existant ID returns a NULL", dao.getById(fakeId), null);
}
   
   
   
   
   
   
   
   @Test
   public void testDaoGetCatagories() {
      
      // call getAll
      Collection<String> categories = dao.getCategories();
      
      // ensure the result includes the test students
      assertTrue("Nissan Category should be in the Collection", categories.contains(silvia.getCategory()));
      assertTrue("s2000 Category should be in the Collection", categories.contains(s2000.getCategory()));
  
}
   
   
   
   
   
   
   @Test
   public void testDaoGetByCategory() {
// get a collection of products belonging to FatBobs Category using getByCategory method
       Collection<Product> products = dao.getByCategory(silvia.getCategory());
       
// ensure that FatBob is in the list
       assertTrue("Is Silvias in the list of Categories/Nissan", products.contains(silvia));
              
       
// should not return products that dont belong to the Category
      for (Product p : products) {
          assertEquals("Collection should only contains products with given Category", p.getCategory(), silvia.getCategory());
       }
      
}
   
   
   
   
   
   
   
    @Test
   public void testDaoEditAStudent() {
       
       //modify FatBob name
       silvia.setName("Silvia s15");
       
       //save modified FatBob
       dao.save(silvia);
       
       // Retrieve the modified FatBob from the DAO(using getById).
       Product modifiedSilvia = dao.getById(silviaID);
       
       //ensure the name was changed
       assertEquals("ensure the name was changed", modifiedSilvia.getName(), "Silvia s15");
   
   }
   
   
   
    
}
