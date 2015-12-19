package domain;

import java.util.Objects;
import net.sf.oval.constraint.*;





/**
 * Product Class for Product Domain
 * @author Ryan Campion 2343075
 */





public class Product implements Comparable<Product>{
    
    
    
    
    /**  Data Field  **/
    
    
    @NotNull(message = "ID must be provided.")
    @NotNegative(message = "ID must be a postive number.")
    @Length(min = 3, max=7, message = "ID must contain between 3 and 7 digits (inclusive).")
    private Integer id;
    
    @NotNull(message = "Name must be provided.")
     @NotBlank(message="Name must be provided.")
     @Length(min=2, message="Name must contain at least two characters.")
    private String name;
    
    private String description;
    
    @NotNull(message="Category must be provided.")
    @NotBlank(message="Category must be provided.")
    private String category;
    
    @NotNull(message="Price must be provided.")
    @NotBlank(message="Price must be provided.")
    private Double price;
      
    @NotNull(message="Quantity must be provided.")
    @NotBlank(message="Quantity must be provided.")
    private Integer quantity;

    
    
    
    
    
    
    /**  Constructor  **/
    public Product(Integer id, String name, String description, String category, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    
    
    
    
    /**  Constructor 2  **/
    public Product() {
   }
    
    
    
    
    
    
    
    
    /**  Getters and Setters  **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Product p) {
        return this.id.compareTo(p.id);
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + category + ", $" + price + ", Qty=" + quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
