/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */
public class PaymentMethod {
    private int methodId;
    private String methodName;
    
    public PaymentMethod(){
        
    }
    
    public PaymentMethod(int methodId,String methodName){
        this.methodId = methodId;
        this.methodName = methodName;
    }
    
    public int getMethodId(){
        return methodId;
    }
    
    public String getMethodName(){
        return methodName;
    }
    
    public void setMethodId(int methodId){
        this.methodId = methodId;
    }
    public void setMethodName(String methodName){
        this.methodName = methodName;
    }
}
