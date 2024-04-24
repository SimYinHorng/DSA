/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import entity.*;
/**
 *
 * @author user
 */
public class CartDA {
    private String host = "jdbc:derby://localhost:1527/GadgetShop";
    private String username = "nbuser";
    private String password = "nbuser";
    private String tableName = "cart";
    private Connection conn;
    private PreparedStatement stmt;
    
    
    public CartDA() throws SQLException{
        try{
            conn = DriverManager.getConnection(host, username, password);
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }
    
    public void createCart(int userId)throws SQLException{
        
    }
    
    public Cart getCart(int userId)throws SQLException{
        String getCartQuery = "SELECT * FROM " + tableName + " WHERE USER_ID = ?";
        ResultSet rs = null;
        Cart cart = new Cart();
        try{
            stmt = conn.prepareStatement(getCartQuery);
            stmt.setInt(1, userId);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                cart = new Cart(rs.getInt("cart_id"), userId, rs.getString("created_at") , rs.getDouble("amount"));
            }
        }catch(SQLException ex){
            throw ex;
        }
        
        return cart;
    }
    
    

    
}
