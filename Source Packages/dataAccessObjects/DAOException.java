/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataAccessObjects;

/**
 *
 * @author Ryan Campion 2343075
 */
public class DAOException extends RuntimeException{
    
    public DAOException(String reason) {
        super (reason);
    }
    
    
    
    
    
    public DAOException(String reason, Exception cause) {
        super(reason, cause);
    }
    
}
