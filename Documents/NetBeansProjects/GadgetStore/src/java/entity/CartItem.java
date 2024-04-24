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


public class CartItem implements Serializable {

    private int cartItemId;

    private int cartId;

    private int productId;

    private int quantity;

    private double subtotal;
    
    public CartItem(){
        
    }
    
    public CartItem(int cartItemId,int cartId,int productId,int quantity, double subtotal){
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    
    public int getCartItemId(){
        return cartItemId;
    }
    
    public int getCartId(){
        return cartId;
    }   
    
    public int getProductId(){
        return productId;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public double getSubtotal(){
        return subtotal;
    }
    
    public void setCartItemId(int cartItemId){
        this.cartItemId = cartItemId;
    }
    
    public void setCartId(int cartId){
        this.cartId = cartId; 
    }
    
    public void setProductId(int productId){
        this.productId = productId;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setSubtotal(double subtotal){
        this.subtotal = subtotal;
    }
}
