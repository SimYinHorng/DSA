<%-- 
    Document   : Cart
    Created on : Apr 18, 2024, 8:29:16 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/root.css"/>
        <link rel="stylesheet" href="css/cart.css"/>
        <script src="js/button.js"></script>
        <title>Gadget Shop</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="cart-body">
            <div class="flex-row second-nav page-width">
                <div class="flex-row sec-nav-item item-center">
                    Home
                    <%@include file="img/arrow-right.svg" %>
                </div>
                <div class="flex-row sec-nav-item item-center">
                    All category
                    <%@include file="img/arrow-right.svg" %>
                </div>
            </div>
            <div class="flex-row cart-container page-width">
                <div class="flex-col cart-left">
                    <table class="cart-table">
                            <tr class="table-header">
                                <th class="header-item">Product</th>
                                <th  class="header-item">Price</th>
                                <th class="header-item">Quantity</th>
                                <th class="header-item subtotal">Subtotal</th>
                                <th class="header-item"></th>
                            </tr>
                            <tr>
                                <td>
                                    <div class="flex-row item-container">
                                      <img src="img/sample1.png" width="140" height="120">
                                      <div class="flex-col item-desc">
                                          <div>Product Name</div>
                                          <div>Product Tag1</div>
                                          <div>Product Tag2</div>
                                      </div>
                                    </div>  
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                    price
                                     </div>
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                        <button class="minus-btn" id="minus" onclick="minusQty()">-</button>
                                        <input class="num-field" type="number"  id="qty" value="1"/>
                                        <button class="plus-btn" id="plus" onclick="plusQty()">+</button>
                                    </div>
                                </td>
                                <td>
                                    <div class="item-center subtotal">
                                        1999999.00
                                    </div>
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                    <button class="del-btn" ><%@include file="img/close-circle.svg" %></button>
                                    </div>
                                    
                                </td>
                            </tr>
                            
                            
                            <tr>
                                <td>
                                    <div class="flex-row item-container">
                                      <img src="img/sample1.png" width="140" height="120">
                                      <div class="flex-col item-desc">
                                          <div>Product Name</div>
                                          <div>Product Tag1</div>
                                          <div>Product Tag2</div>
                                      </div>
                                    </div>  
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                    price
                                     </div>
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                        <button class="minus-btn" id="minus" onclick="minusQty()">-</button>
                                        <input class="num-field" type="number"  id="qty" value="1"/>
                                        <button class="plus-btn" id="plus" onclick="plusQty()">+</button>
                                    </div>
                                </td>
                                <td>
                                    <div class="item-center subtotal">
                                        100.00
                                    </div>
                                </td>
                                <td>
                                    <div class="flex-row item-center content-center">
                                    <button class="del-btn" ><%@include file="img/close-circle.svg" %></button>
                                    </div>
                                    
                                </td>
                            </tr>
                        </table>
                    <hr>
                    <div class="flex-row item-center btn-container">
                        <div class="main-btn">
                            Continue shopping
                        </div>
                        <div class="update-btn">
                            Update cart
                        </div>
                        <div class="clear-btn">
                            Clear cart
                        </div>
                    </div>
                </div>
                <div class=" cart-right item-center">
                    <div class="cart-right-header">Cart Total</div>
                    <div class="cart-right-content flex-col">
                        <div class="flex-row item-center price-container">
                            <div>
                                Subtotal
                            </div>
                            <div>
                                RM 24.00
                            </div>    
                        </div>
                        <hr>
                        <div class="coupon-container flex-row content-center">
                            <input type="text" placeholder="Enter coupon code" class="coupon-field" style="outline: none;">
                            <button style="color: #003f62; font-weight: 500; font-size: 15px; background: transparent; border:none; cursor: pointer;">Apply</button>
                        </div>
                        <hr>
                        <div class="flex-row item-center price-container">
                            <div>
                                Total
                            </div>
                            <div>
                                RM 24.00
                            </div>  
                        </div>
                        <div class="main-btn">
                            Proceed To Checkout
                        </div>       
                    </div>     
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
