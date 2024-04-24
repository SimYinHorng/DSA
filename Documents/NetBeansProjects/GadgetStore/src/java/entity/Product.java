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

public class Product implements Serializable {

    private int productId; 

    private String productName;

    private int categoryId;

    private double price;

    private String description;
    private List<ProductImage> imageList;
    
    
    public Product(){};
    
    public Product(int productId,String productName, int categoryId, double price, String description){
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
    }
    
    public Product(String productName, int categoryId, double price, String description){
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public int getCategoryId(){
        return categoryId;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getDescription(){
        return description;
    }
    
    public List<ProductImage> getImageList(){
        return imageList;
    }
    
    public void setProductId(int productId){
        this.productId = productId;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setImageList(List<ProductImage> imageList){
        this.imageList = imageList;
    }
    
}
