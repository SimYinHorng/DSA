<%-- 
    Document   : header
    Created on : Apr 17, 2024, 7:17:21 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/root.css">
    </head>
    <body>
        <header class=" flex-col">
            
            <div class="top-header flex-row page-width">
                <div class="wrapper flex-row">
                    <div id="logo">
                        LOGO A
                    </div>
                    
                    <form action = test()>
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
                    <div class="categories flex-row">
                            Browse categories     
                        <span class="item-center flex-col"><%@include file="img/arrow-down.svg" %></span>
                        
                    </div>

                    <div class="nav flex-row">
                        <div>
                            <a href="home">Home</a>
                        </div>
                        <div>
                            <a href="about-us">About us</a>
                        </div>
                        <div>
                            <a href="product">Product</a>
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
