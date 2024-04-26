<%-- 
    Document   : successful
    Created on : Apr 20, 2024, 8:35:04 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/root.css"/>
        <link rel="stylesheet" href="css/successful.css"/>
        <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
        <title>Gadget Shop</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="success-body page-width">
            <div class="flex-row success-top">
                <div class="flex-row item-center">
                    Payment Method
                </div>
                <div class="flex-row item-center">
                    <%@include file="img/bold-arrow-right.svg" %>          
                </div>
                <div class="flex-row item-center"  style="color:black;">
                    Payment Verification
                </div>
            </div>
            <div class="flex-col item-center">
                <lottie-player src="animation/successful-payment.json" background="transparent"  speed="1"  style="width: 600px; height: 400px;" autoplay></lottie-player>
                <button class="main-btn">Back To Shopping</button>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
