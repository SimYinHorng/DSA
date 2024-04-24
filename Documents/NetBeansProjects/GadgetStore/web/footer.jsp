<%-- 
    Document   : footer
    Created on : Apr 17, 2024, 7:17:25 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Footer</title>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/root.css"/>
    </head>
    <body>
        <footer class="flex-col item-center">
            <div class=" page-width">
                <div class="top-footer flex-row item-center">
                    <div class="top-footer-left">
                        Subscribe newsletter
                    </div>
                    <div class="flex-row item-center top-footer-right">
                        <form >
                            <div class="email flex-row">
                                <input type="email" name="" class="email-box" placeholder="Email Address">
                                <button class="email-button" ><%@include file="img/aero.svg" %></button>
                             </div>
                        </form>
                        <div class="flex-row item-center call">
                            <%@include file="img/headphone.svg" %>
                            <div class="flex-col">
                                <div class="call-text">
                                    Call us 24/7;
                                </div>
                                <div class="call-text">
                                    (+62) 012 3456 7890
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>

            <div class="flex-row bot-footer page-width">
                <div class="flex-col">
                    <div class="footer-logo">
                        Logo
                    </div>
                    <div class="footer-address">
                        64 st james boulevard hoswick , ze2 7zj
                    </div>
                    <hr>
                    <div class="flex-row media">
                        <%@include file="img/google.svg" %>
                        <%@include file="img/facebook.svg" %>
                        <%@include file="img/whatsapp.svg" %>
                    </div>
                </div>

                <div class="flex-row bot-right-footer">
                    <div class="flex-col">
                        <div class="list-title">
                            Find Product
                        </div>
                        <ul class="footer-list">
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                        </ul>
                    </div>
                    <div  class="flex-col">
                        <div class="list-title">
                            Get Help
                        </div>
                        <ul class="footer-list">
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                        </ul>
                    </div>
                    <div class="flex-col">
                        <div class="list-title">
                            About us
                        </div>
                        <ul class="footer-list">
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                            <li>item</li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
