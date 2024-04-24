<%-- 
    Document   : product
    Created on : Apr 21, 2024, 7:27:14 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/root.css"/>
        <link rel="stylesheet" href="css/product.css"/>
        <script src="js/button.js"></script>
        <title>Gadget Shop</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="product-body page-width">
            <div class="flex-row second-nav">
                <div class="flex-row sec-nav-item item-center">
                    Home
                    <%@include file="img/arrow-right.svg" %>
                </div>
                <div class="flex-row sec-nav-item item-center">
                    All category
                    <%@include file="img/arrow-right.svg" %>
                </div>
            </div>
            <div class="product-top flex-row">
                <div class="product-left flex-col">
                    <div class="image-container flex-row" >
                        <img src="img/controller.png" alt="image1" width="400" height="100%"/>
                    </div>
                    <div class="flex-row item-center sub-image">
                        <div class="image-container">
                            <img src="img/controller.png" alt="image1" width="180" height="100%"/>
                        </div>
                        <div class="image-container">
                            <img src="img/controller.png" alt="image1" width="180" height="100%"/>
                        </div>
                    </div>
                </div>
                <div class="product-right flex-col">
                    <div class="flex-col prod-right-top">
                        <div style="color:#1B5A7D; font-size: 30px;">Product Name</div>
                        <div style="color:#4A4A4A; font-size: 30px;">Price</div>
                    </div>
                    <div class="desc-container">
                        Description
                    </div>
                    <div class="flex-col prod-right-top">
                        
                        <div class="flex-row item-center">
                            <div style="padding-right: 20px;">Quantity :</div>
                            <button class="minus-btn" id="minus" onclick="minusQty()">-</button>
                            <input class="num-field" type="number"  id="qty" value="1"/>
                            <button class="plus-btn" id="plus" onclick="plusQty()">+</button>
                        </div>
                        <div class="flex-row item-center button-wrapper">
                            <button class="main-btn">Add To Cart</button>
                            <button class="main-btn">Buy Now</button>
                        </div>
                    </div>
                    <hr>
                    <div class="flex-row">
                        <div>Category :</div>
                        <div></div>
                    </div>
                    <div class="">
                        <div>Share :</div>
                        <div></div>
                    </div>
                </div>
            </div>
            <div class="product-bot flex-col">
                <div style="color:#1B5A7D;font-size: 28px;font-weight: 600;">Other Product</div>
                <div class="flex-row item-center other-prod-wrapper">
                    <div class ="popProdContainer">
                        <div class ="popImgContainer">
                            <img src="imageWS/superlight2.jpg" class = "prodDisplayImg">
                        </div>

                        <div class = "buttonContainer flex-row item-center">
                            <button type ="button" class = "button buyButton prodBuyButton">Buy</button>
                            <button type ="button" class = "button viewButton prodViewButton" >View more</button>
                        </div>
                    </div>
                    <div class ="popProdContainer">
                        <div class ="popImgContainer">
                            <img src="imageWS/superlight2.jpg" class = "prodDisplayImg">
                        </div>

                        <div class = "buttonContainer flex-row item-center">
                            <button type ="button" class = "button buyButton prodBuyButton">Buy</button>
                            <button type ="button" class = "button viewButton prodViewButton" >View more</button>
                        </div>
                    </div>
                    <div class ="popProdContainer">
                        <div class ="popImgContainer">
                            <img src="imageWS/superlight2.jpg" class = "prodDisplayImg">
                        </div>

                        <div class = "buttonContainer flex-row item-center">
                            <button type ="button" class = "button buyButton prodBuyButton">Buy</button>
                            <button type ="button" class = "button viewButton prodViewButton" >View more</button>
                        </div>
                    </div>
                    <div class ="popProdContainer">
                        <div class ="popImgContainer">
                            <img src="imageWS/superlight2.jpg" class = "prodDisplayImg">
                        </div>

                        <div class = "buttonContainer flex-row item-center">
                            <button type ="button" class = "button buyButton prodBuyButton">Buy</button>
                            <button type ="button" class = "button viewButton prodViewButton" >View more</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
