/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.io.Serializable;
/**
 *
 * @author user
 */


public class Address implements Serializable {

    private int addressId;

    private int orderId;

    private String address;

    private String postalCode;

    private String city;

    private String country;
    
    public Address(){
        
    }
    
}
