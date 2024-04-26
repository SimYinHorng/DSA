/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */
public class Payment {
    private int paymentId;
    private int orderId;
    private int methodId;
    private double amount;
    private String paymentDate;
    
    public Payment(){
        
    }
    
    public Payment(int paymentId,int orderId, int methodId,double amount, String paymentDate){
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.methodId = methodId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        
    }
    
    public int getPaymentId(){
        return paymentId;
    }
    
    public int getOrderId(){
        return orderId;
    }
    
    public int getMethodId(){
        return methodId;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public String getPaymentDate(){
        return paymentDate;
    }
    
    public void setPaymentId(int paymentId){
        this.paymentId = paymentId;
    }
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    
    public void setMethodId(int methodId){
        this.methodId = methodId;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public void setPaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }          
            
}
