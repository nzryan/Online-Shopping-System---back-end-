
package domain;

/**
 * OrderItem Class for OrderItem Domain
 * @author Ryan Campion 2343075
 */





public class OrderItem {
    
    
    
    
    
    
    
    /**  Data Field  **/
    private Integer quantityPurchased;
    private Product product;
    private Order order;
    
    
    
    
    
    
    /**  Constructors  **/
    public OrderItem(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }
    
    public OrderItem() {        
    }

    
    
    
    
    
    
    /**  Getter and Setter  **/
    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
    
    
    
    
    
    
    /**  Additional Methods  **/
    
    //should multiply the products price by the quantity purchased and return the result.
    public Double getItemTotal(){
        Double result = quantityPurchased * product.getPrice();
        return result;
    }
}
