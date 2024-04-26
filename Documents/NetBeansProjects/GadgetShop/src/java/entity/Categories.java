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

public class Categories implements Serializable {

    private int categoryId;

    private String categoryName;
    
    public Categories(){
        
    }
    
    public Categories(int categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    
    public int getCategoryId(){
        return categoryId;   
    }
    
    public String getCategoryName(){
        return categoryName;
    }
    
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
    
}
