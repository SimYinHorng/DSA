/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

import java.util.List;
/**
 *
 * @author user
*/
public class Cart implements Serializable {


    private int cartId;

    private int userId;

    private String createdTime;

    private double amount;
    private List<CartItem> cartItemList;
    
    public Cart(){
        
    }

     public Cart(int cartId,int userId, String createdTime,double amount){
        this.cartId = cartId;
        this.userId = userId;
        this.createdTime = createdTime;
        this.amount = amount;
    }
    
    public Cart(int cartId,int userId, String createdTime,double amount,List<CartItem> cartItemList){
        this.cartId = cartId;
        this.userId = userId;
        this.createdTime = createdTime;
        this.amount = amount;
        this.cartItemList = cartItemList;
    }
    
    public int getCartId(){
        return cartId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public String getCreatedTime(){
        return createdTime;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setCartId(int cartId){
        this.cartId = cartId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public void setCreatedTime(String createdTime){
        this.createdTime = createdTime;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
}
