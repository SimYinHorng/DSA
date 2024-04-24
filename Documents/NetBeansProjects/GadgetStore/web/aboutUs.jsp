<%-- 
    Document   : aboutUs
    Created on : Apr 22, 2024, 7:11:10 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/root.css"/>
        <link rel="stylesheet" href="css/aboutUs.css"/>
        <title>Gadget Shop</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="page-width">
            <div class="flex-row item-center story-section">
                <div class="sec-1-left flex-col">
                    <div class="story-title">
                        Our Story
                    </div>
                    <div class="story-desc flex-col">
                        <div>
                            Since its inception in 2015, Exclusive has emerged as the premier online shopping destination in South East Asia. Our platform boasts an extensive network of 10,500 sellers and proudly showcases products from over 100 esteemed brands, catering to the discerning needs of a vast customer base exceeding 3 million across the region. With an ever-expanding inventory exceeding 1 million products, Exclusive offers a diverse assortment across a plethora of categories, ensuring that every shopper finds exactly what they're looking for.
                        </div>
                        <div>
                            At Exclusive, we're committed to revolutionizing the online shopping experience through tailored marketing solutions and unparalleled customer service. Our dedication to quality and customer satisfaction permeates every facet of our operation, ensuring that each interaction with our platform is met with seamless efficiency and utmost satisfaction. Join us on our journey as we continue to redefine the boundaries of online shopping and provide a platform where convenience, reliability, and excellence converge.
                        </div>
                    </div>                    
                </div>
                <div class="sec-1-right">
                    <img src="img/about-us.jpg" width="width" height="400" alt="alt"/>
                </div>
            </div>
            <div class="sales-section flex-row item-center">
                <div class="sales-container flex-col item-center">
                    <%@include file="img/store.svg" %>
                    <div class="sales-text flex-col item-center">
                        <div class="sales-title">10.5k </div>
                        <div class="sales-desc">Sellers active our site</div>
                    </div>
                </div>
                <div class="sales-container-unique flex-col item-center">
                    <%@include file="img/dollar.svg" %>
                    <div class="sales-text flex-col item-center">
                        <div class="sales-title">33k </div>
                        <div class="sales-desc">Monthly Products Sale</div>
                    </div>
                </div>
                <div class="sales-container flex-col item-center">
                    <%@include file="img/bag.svg" %>
                    <div class="sales-text flex-col item-center">
                        <div class="sales-title">45.5k </div>
                        <div class="sales-desc">Customer active in our site</div>
                    </div>
                </div>
                <div class="sales-container flex-col item-center">
                    <%@include file="img/loot.svg" %>
                    <div class="sales-text flex-col item-center">
                        <div class="sales-title">25k </div>
                        <div class="sales-desc">Annual sales in our site</div>
                    </div>
                </div>
            </div>
            <div class="pos-container flex-row item-center">
                
                <div class="pos flex-col">
                    <img src="img/cofounder.png" alt="cofounder"/>
                    <div class="pos-text flex-col">
                        <div class="pos-name">
                            Maria Watson
                        </div>
                        <div class="pos-title">
                            Co-founder
                        </div>
                    </div>
                </div>
                <div class="pos flex-col">
                    <img src="img/founder.png" alt="founder"/>
                    <div class="pos-text flex-col">
                        <div class="pos-name">
                            Tom Cruise
                        </div>
                        <div class="pos-title">
                            Founder
                        </div>
                    </div>
                </div>
                <div class="pos flex-col">
                    <img src="img/cofounder2.png" alt="cofounder2"/>
                    <div class="pos-text flex-col">
                        <div class="pos-name">
                            John Hamson
                        </div>
                        <div class="pos-title">
                            Co-founder
                        </div>
                    </div>
                </div>
            </div>
            <div class="flex-row service-section item-center">
                <div class="service-container flex-col item-center "> 
                    <%@include file="img/truck.svg" %>
                    <div class="service-text flex-col item-center">
                        <div class="service-title">
                            FAST DELIVERY
                        </div>
                        <div class="service-desc">
                            Delivery Arrived within 14 days
                        </div>
                    </div>
                </div>
                <div class="service-container flex-col item-center"> 
                    <%@include file="img/headset.svg" %>
                    <div class="service-text flex-col item-center">
                        <div class="service-title">
                            24/7 CUSTOMER SERVICE
                        </div>
                        <div class="service-desc">
                            Friendly 24/7 customer support
                        </div>
                    </div>
                </div>
                <div class="service-container flex-col item-center"> 
                    <%@include file="img/guarantee.svg" %>
                    <div class="service-text flex-col item-center">
                        <div class="service-title">
                            MONEY BACK GUARANTEE
                        </div>
                        <div class="service-desc">
                            We return money within 30 days
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
