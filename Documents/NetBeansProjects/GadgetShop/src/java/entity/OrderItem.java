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

public class OrderItem implements Serializable{

    private int orderItemId;

    private int orderId;

    private int productId;

    private int quantity;

    private double subtotal;
    
    public OrderItem(){
    }
    
    public OrderItem(int orderItemId,int orderId,int productId, int quantity, double subtotal){
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    
    
    public int getOrderItemId(){
        return orderItemId;
    }
    
    public int getOrderId(){
        return orderId;
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
    
    public void setOrderItemId(int orderItemId){
        this.orderItemId = orderItemId;
    }
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
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
