<%-- 
    Document   : header
    Created on : Apr 17, 2024, 7:17:21 PM
    Author     : user
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Categories,java.util.*"%>
<% ArrayList<Categories> categoriesList = (ArrayList<Categories>)application.getAttribute("categoriesList");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="css/header.css"  type="text/css"/>
        <link rel="stylesheet" href="css/root.css"  type="text/css">
        <style>
            <%@include file="css/header.css"%>
            <%@include file="css/root.css"%>
        </style>
        <script><%@include file="js/categoriesDropdown.js"%></script>
    </head>
    <body>
        <header class=" flex-col">
            
            <div class="top-header flex-row page-width">
                <div class="wrapper flex-row">
                    <div id="logo">
                        LOGO A
                    </div>
                    
                    <form action="">
                        <div class="search-bar flex-row">
                            <input type="text" name="" class="search-box" placeholder="Search any things">
                            <input type ="submit" value="Search" class="search-button">
                         </div>
                    </form>
                   
                </div>
                <div class="right-header flex-row">
                    <div class="user-icon flex-row">
                        <%@include file="img/user.svg" %>
                        Sign in
                    </div>
                    <div class="cart-icon flex-row">
                        <%@include file="img/shopping-cart.svg" %>
                        Cart
                    </div>  
                </div>
            </div>
            <div class="nav-bar ">
                <div class="nav-wrapper flex-row page-width">
                 <div class="nav-left flex-row">
                    <div>
                        <button onclick="dropdown()" class="flex-row categories-button">
                            Browse categories     
                            <span class="item-center flex-col"><%@include file="img/arrow-down.svg" %></span>                            
                        </button>
                        <div id="cat-dropdown" class="cat-content">
                            <%  for(int i = 0; i < categoriesList.size();i++){
                                    Categories category = categoriesList.get(i);%>
                            <a class="flex-row item-center" href="categories/<%=category.getCategoryId()%>">
                                <%=category.getCategoryName()%>
                               
                            </a>
                            <%};%>
                        </div>
                    </div>
                          
                    <div class="nav flex-row">
                        <div>
                            <a href="/GadgetShop/home">Home</a>
                        </div>
                        <div>
                            <a href="/GadgetShop/about-us">About us</a>
                        </div>
                        <div>
                            <a href="/GadgetShop/product/2">Product</a>
                        </div>
                        <div>
                            Contact Us
                        </div>
                    </div>
                </div>
                <div class=" nav-right flex-row">
                    
                    <div class="return">
                        30 days free return
                    </div>
                </div>                   
                </div>

            </div>
        </header>
    </body>
</html>
