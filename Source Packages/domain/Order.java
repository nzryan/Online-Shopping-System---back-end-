
package domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Order Class for Order Domain
 * @author Ryan Campion 2343075
 */




public class Order {
    
    
    
    
    
    
    /**  Data Field  **/
    private Integer orderID;
    private Date date;
    private Customer customer;
    private List <OrderItem> items = new ArrayList<>();
    
    
    
    
    
    
    
    /*  Constructor  */
  
     public Order(Customer customer) {
        this.customer = customer;
        this.date = new Date();
    }
    
    
    
    
    
    
    
    
    
    /**  Getters and Setters  **/
    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    
    
    
    
    
    
    
    
    
    /**  Additional Methods  **/
    
    
     //The getTotal method in the Order class should sum the item totals (which it gets by calling getItemTotal) 
   //for all of the items in the order and return the result.
    public Double getTotal(){
      
        Double result = 0.00;
        
        for(OrderItem item : items){
            result += item.getItemTotal();
            
        }
        return result;
    }
    
    
    
   //should take the passed OrderItem and add it to the items collection.
    public void addItem(OrderItem item){
      items.add(item);
    }
   
}
