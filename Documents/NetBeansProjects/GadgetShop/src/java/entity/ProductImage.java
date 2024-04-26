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

public class ProductImage implements Serializable {

    private int imageId;

    private int productId;

    private byte[] imageData;
    
    public ProductImage(){
    }
    
    public ProductImage(int imageId, int productId , byte[] imageData){
        this.imageId = imageId;
        this.productId = productId;
        this.imageData = imageData;
    }
    
    public int getImageId(){
        return imageId;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public byte[] getImageData(){
        return imageData;
    }
    
    public void setImageId(int imageId){
        this.imageId = imageId;
    }
    
    public void setProductId(int productId){
        this.productId = productId;
    }
    
    public void setImageData(byte[] imageData){
        this.imageData = imageData;
    }
    
}
