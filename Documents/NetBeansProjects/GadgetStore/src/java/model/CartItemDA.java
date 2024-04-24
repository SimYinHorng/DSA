/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CartItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CartItemDA {
    
    private String host = "jdbc:derby://localhost:1527/GadgetShop";
    private String username = "nbuser";
    private String password = "nbuser";
    private String tableName = "cart_item";
    private Connection conn;
    private PreparedStatement stmt;
    
    public CartItemDA() throws SQLException{
        try{
            conn = DriverManager.getConnection(host, username, password);
            
        }catch(SQLException ex){
            //Logger.getLogger(BookDA.class.getName()).log(Logger.Level.ERROR, ex);
            throw ex;
        } 
    }

    public ArrayList<CartItem> getAllCartItem(int cartId)throws SQLException{
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        String getQuery = "SELECT * FROM " + tableName + " WHERE CART_ID = ?";
        ResultSet rs = null;

        try{
            stmt = conn.prepareStatement(getQuery);
            stmt.setInt(1, cartId);

            rs = stmt.executeQuery();
            while(rs.next()){
                CartItem cartItem = new CartItem(rs.getInt("cart_item_id"), cartId, rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("subtotal"));
                cartItemList.add(cartItem);
            }
        }catch(SQLException ex){
            throw ex;
        }
        return cartItemList;
    }   
    
    
    public void clearCart(int cartId) throws SQLException{
        String clearQuery = "DELETE * FROM "+ tableName + " WHERE CART_ID = ?";
        try{
            stmt = conn.prepareStatement(clearQuery);
            stmt.setInt(1, cartId);
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            throw ex;
        }
    }
}
