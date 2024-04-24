/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entity.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class ProductDA {
    private String host = "jdbc:derby://localhost:1527/GadgetShop";
    private String username = "nbuser";
    private String password = "nbuser";
    private String tableName = "products";
    private Connection conn;
    private PreparedStatement stmt;

    
    public ProductDA() throws SQLException{
        try{
            conn = DriverManager.getConnection(host, username, password);
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }
    
    
    public Product getSingleProduct(int productId) throws SQLException{
        String getQuery = "SELECT * FROM " + tableName + " WHERE product_id = ?";
        ResultSet rs = null;
        Product prod = new Product();
        
        try{
            stmt = conn.prepareStatement(getQuery);
            stmt.setInt(1,productId);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                prod = new Product(productId,rs.getString("product_name"),rs.getInt("category_id"),rs.getDouble("price"),rs.getString("description"));
                
            }else{
                throw new SQLException("Product Not Found");
            }
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
        
        return prod;
    }
    
    public ArrayList<Product> getAllProduct()throws SQLException{
        String getQuery = "SELECT * FROM " + tableName;
        ResultSet rs = null;
        ArrayList<Product> productList= new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(getQuery);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Product prod = new Product(rs.getInt("product_id"),rs.getString("product_name"),rs.getInt("category_id"),rs.getDouble("price"),rs.getString("description"));
                productList.add(prod);
            }
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
        
        return productList;
    
    }
    
  
    public ArrayList<Product> getProductByCategory(int category_id)throws SQLException{
        String getQuery = "SELECT * FROM " + tableName + " WHERE CATEGORY_ID = ?";
        ResultSet rs = null;
        ArrayList<Product> productList= new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(getQuery);
            stmt.setInt(1, category_id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Product prod = new Product(rs.getInt("product_id"),rs.getString("product_name"),rs.getInt("category_id"),rs.getDouble("price"),rs.getString("description"));
                productList.add(prod);
            }
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
        
        return productList;
    
    } 
    
    public void addProduct(Product prod)throws SQLException{
        String addQuery = "INSERT INTO " + tableName + " (product_name,category_id,price,description) VALUES (?,?,?,?)";
        
        try{
            stmt = conn.prepareStatement(addQuery);
            stmt.setString(1, prod.getProductName());
            stmt.setInt(2,prod.getCategoryId());
            stmt.setDouble(3, prod.getPrice());
            stmt.setString(4, prod.getDescription());
            stmt.executeUpdate(); 
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
        
    }
    
    public void deleteProduct(int productId) throws SQLException{
        String deleteQuery = "DELETE FROM " + tableName + " WHERE PRODUCT_ID = ?";
        try{
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, productId);
            stmt.executeUpdate(); 
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }
    
    public void updateProduct(Product prod) throws SQLException{
        String updateQuery = "UPDATE " + tableName + " SET PRODUCT_NAME = ? , CATEGORY_ID = ? , PRICE = ? , DESCRIPTION = ? WHERE PRODUCT_ID = ?";
        
        try{
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, prod.getProductName());
            stmt.setInt(2,prod.getCategoryId());
            stmt.setDouble(3, prod.getPrice());
            stmt.setString(4, prod.getDescription());
            stmt.setInt(5, prod.getProductId());
            stmt.executeUpdate(); 
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }
    
}
