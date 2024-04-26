/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import entity.Product;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriesDA;
import model.ProductDA;

/**
 *
 * @author user
 */
public class HomeRedirectServlet extends HttpServlet {
    
    @Override 
    public void init(){
        try {
            CategoriesDA categoriesDA = new CategoriesDA();
            ProductDA prodDA = new ProductDA();
            ArrayList<Categories> categoriesList = categoriesDA.getAllCategories();
            ArrayList<Product> productList = prodDA.getAllProduct();
            getServletContext().setAttribute("categoriesList", categoriesList);
            getServletContext().setAttribute("productList", productList);
        } catch (SQLException ex) {
            Logger.getLogger(HomeRedirectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
