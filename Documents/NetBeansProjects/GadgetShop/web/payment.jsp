<%-- 
    Document   : payment
    Created on : Apr 20, 2024, 1:57:11 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/root.css"/>
        <link rel="stylesheet" href="css/payment.css"/>
        <title>Gadget Shop</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="page-width">
            <div class="flex-row payment-top">
                <div class="flex-row item-center" style="color:black;">
                    Payment Method
                </div>
                <div class="flex-row item-center">
                    <%@include file="img/bold-arrow-right.svg" %>          
                </div>
                <div class="flex-row item-center">
                    Payment Verification
                </div>
            </div>
            <div class="payment-hr">
                <hr>
            </div>
            <form action="">
                <div class="flex-row payment-bot">
                    <div class="payment-left">
                        <div class="address-container">
                            <div class="long-container flex-col">
                                <label for="shipping-address">Shipping Address</label>
                                <input type="text" class="" id="shipping-address" required>                                
                            </div>
                            <div class="grid-container flex-col">
                                <label for="city">City</label>
                                <select id="city" required>
                                    <option value=''>City</option>
                                    <option value="Batu Ferringhi">Batu Ferringhi</option>
                                    <option value="Butterworth">Butterworth</option>
                                    <option value="GeorgeTown">GeorgeTown</option>
                                </select>
                            </div>
                            <div class="grid-container flex-col">
                                <label for="state">State</label>
                                <select id="state" required>
                                    <option value=''>State</option>
                                    <option value="Penang">Penang</option>
                                    <option value="Kedah">Kedah</option>
                                    <option value="Perak">Perak</option>
                                </select>                               
                            </div>
                            <div class="grid-container flex-col">
                                <label for="postal-code">Postal Code</label>
                                <input type="text" disabled id="postal-code">                                 
                            </div>
                            <div class="grid-container flex-col">
                                <label for="country">Country</label>
                                <select id="country" required>
                                    <option value=''>Country</option>
                                    <option value="Malaysia">Malaysia</option>
                                    <option value="Singapore">Singapore</option>
                                    <option value="Indonesia">Indonesia</option>
                                </select>  
                            </div>
                        </div>
                        <hr style="margin-right: 31px">
                        <div class="card-info">
                            <div class="long-container flex-col">
                                <label for="ccn">Card Number</label>
                                <input id="ccn" type="tel" pattern="[0-9\s]{13,19}" 
                                       placeholder="xxxx xxxx xxxx xxxx" required minlength="13" maxlength="19">                                
                            </div>
                            <div class="grid-container flex-col">
                                <label for="exp">Expiry Date</label>
                                <div class="flex-row exp-container">
                                    <input class="exp" id="month" max="99" placeholder="MM" type="number"/>
                                    <input class="exp" id="year" max="99" placeholder="YY" type="number" />
                                </div>                    
                            </div>
                            <div class="grid-container flex-col">
                                <label for="cvc">CVC</label>
                                <input type="text"  name="cvc" maxlength="3"  id="cvc" pattern="[1-9][0-9]{3}" required/>                                
                            </div>
                        </div>
                    </div>
                    <div class="flex-col payment-right">
                        <div style="font-weight: 500; font-size: 20px;">Order Summary</div>
                        <div class="flex-col ship-option">
                            <label for="shipping">Shipping</label>
                            <select id="shipping" required>
                                <option value=''>Shipping</option>
                                <option value="10">Road - RM10.00</option>
                                <option value="20">Sea - RM20.00</option>
                                <option value="40">Air - RM40.00</option>
                            </select>  
                        </div>
                        <hr>
                        <div>
                            <div class="flex-row item-center cash-container">
                                <div>Subtotal</div>
                                <div>RM 100.00</div>
                            </div>
                            <div class="flex-row item-center cash-container">
                                <div>Shipping</div>
                                <div>RM 20.00</div>
                            </div>
                            <div class="flex-row item-center cash-container">
                                <div>Promo</div>
                                <div>-</div>
                            </div>
                        </div>
                        <div class="flex-row item-center cash-container">
                            <div>Total</div>
                            <div>RM 120.00</div>
                        </div>
                        <div class="main-btn">Check Out</div>
                    </div>
                </div>  
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
