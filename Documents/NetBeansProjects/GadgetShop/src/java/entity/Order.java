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

public class Order implements Serializable {


    private int orderId;

    private int userId;

    private double totalAmt;

    private String orderTime;
    private List<OrderItem> orderItemList;
    
    public Order(){
        
    }
    
    public Order(int orderId,int userId,double totalAmt, String orderTime){
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmt = totalAmt;
        this.orderTime = orderTime;
    }
    
    public int getOrderId(){
        return orderId;
    }
    public int getUserId(){
        return userId;
    }
    public double getTotalAmt(){
        return totalAmt;
    }
    public String getOrderTime(){
        return orderTime;
    }
    
    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public void setTotalAmt(double totalAmt){
        this.totalAmt = totalAmt;
    }
    
    public void setCreatedTime(String orderTime){
        this.orderTime= orderTime;
    }
    
    public void setOrderItemList(List<OrderItem> orderItemList){
        this.orderItemList = orderItemList;
    }
}
