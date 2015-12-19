package domain;

import net.sf.oval.constraint.*;


/**
 * Customer Class for Customers domain
 * @author Ryan Campion 2343075
 */


public class Customer {
    
    
    
    
    /**  Data Field  **/
    @NotNull(message = "Username must be provided.")
    @NotBlank(message="Username must be provided.")
    @Length(max=30, message = "Username cannot contain more than 30 charactors")
    private String username;
    
    @NotNull(message = "Firstname must be provided.")
    @NotBlank(message = "Firstname must be provided.")
    @Length(min = 2, max=30, message = "Firstname must contain between 2 and 30 digits (inclusive).")
    private String firstname;
    
    @NotNull(message = "Lastname must be provided.")
    @NotBlank(message = "Lastname must be provided.")
    @Length(min = 2, max=30, message = "Lastname must contain between 2 and 30 digits (inclusive).")
    private String lastname;
    
    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    @Length(min = 6, max=30, message = "Password must contain between 6 and 30 digits (inclusive).")
    //@Digits(minInteger = 2, message = "Password must contain at least 2 numebrs")
    private String password;
    
    @NotNull(message = "Lastname must be provided.")
    @NotBlank(message = "Lastname must be provided.")
    //becasue most minimal email would be "a@a.com"
    @Length(min = 7, max=50, message = "Email must contain between 7 and 50 digits (inclusive).")
    @Email(message = "This is not a valid Email")
    private String email;
    
    @NotNull(message = "CreditCard must be provided.")
    @NotBlank(message = "CreditCard must be provided.")
    //format "1111-1111-1111-1111"
    @Length(min = 19, max=19, message = "CreditCard must be in the format 1111-2222-3333-4444")
    private String creditCard;
    
    
    //Address
    @NotNull(message = "Street must be provided.")
    @NotBlank(message = "Street must be provided.")
    @Length(min = 6, max=50, message = "Street must contain between 6 and 50 digits (inclusive).")
    //@Digits(minInteger = 1, message = "No Street Number Provided")
    private String street;
    
    @NotNull(message = "Suburb must be provided.")
    @NotBlank(message = "Suburb must be provided.")
    @Length(min = 2, max=40, message = "Suburb must contain between 2 and 40 digits (inclusive).")
    private String suburb;
    
    @NotNull(message = "City must be provided.")
    @NotBlank(message = "City must be provided.")
    @Length(min = 2, max=30, message = "City must contain between 2 and 30 digits (inclusive).")
    private String city;
    
   @NotNull(message = "Postcode must be provided.")
   @NotNegative(message = "Postcode must be a postive number.")
   @Length(min = 4, max=4,message = "Postcode must be 4 Digits") 
    private int postcode;
    
    
    
    
    
    
    /**  Constructor  **/
    public Customer(String username, String firstname, String lastname, String password, String email, String creditCard, String street, String suburb, String city, int postcode) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.creditCard = creditCard;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.postcode = postcode;
    }

    
    
    public Customer(){
    }
    
    
    
    
    
    /**  Getters & Setters **/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

   
    
    
    

    
   
    
}