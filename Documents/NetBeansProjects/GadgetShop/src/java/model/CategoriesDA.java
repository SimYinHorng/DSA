/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entity.Categories;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class CategoriesDA {
    private String host = "jdbc:derby://localhost:1527/GadgetShop ";
    private String username = "nbuser";
    private String password = "nbuser";
    private String tableName = "categories";
    public Connection conn;
    private PreparedStatement stmt;
    
    public CategoriesDA() throws SQLException{
        try{
            conn = DriverManager.getConnection(host, username, password);

        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }
    
    public void closeConnection() throws SQLException{
        if(conn!=null){
            conn.close();
        }
    }
    
    public Categories getCategoriesById(int categoryId) throws SQLException{
        String getCategory = "SELECT * FROM " + tableName + " WHERE CATEGORY_ID = ?";
        Categories category = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(getCategory);
            rs = stmt.executeQuery();
            if(rs.next()){
                category = new Categories(rs.getInt("category_id"),rs.getString("category_name"));
            }
        }catch(SQLException ex){
            throw ex;
        }
        
        return category;
    }
    
    public ArrayList<Categories> getAllCategories()throws SQLException{
        
        String getAllQuery = "SELECT * FROM " + tableName;
        ArrayList<Categories> categoriesList = new ArrayList<>();
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(getAllQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                Categories category = new Categories(rs.getInt("CATEGORY_ID"),rs.getString("CATEGORY_NAME"));
                categoriesList.add(category);
            }
        }catch(SQLException ex){
            throw ex;
        }
        
        return categoriesList;
        
    }
   
    
}
