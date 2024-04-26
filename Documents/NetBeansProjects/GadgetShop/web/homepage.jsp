
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GadgetStore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/homepageCSS.css" rel="stylesheet" type="text/css"/>
        <link href="css/root.css" rel="stylesheet" type="text/css"/>
        <script src="js/homepageJs.js"></script>
        <script src="js/sliderJs.js"></script>
    </head>
    <body>  
        <jsp:include page="header.jsp" />
        <div class = "promoClass flex-col item-center page-width" >
            <div class = "promoBanner flex-row fade" >
                <div>
                    <div id = "bannerText">
                        <p class = promoTitle>Logitech Keyboard</p>
                        <div class = "buttonDiv flex-row">
                            <div id ="buyDiv">
                                <a href="pg1.jsp" id = "buyButton">
                                    <button type ="button" class = "button buyButton">Buy</button>
                                </a> 
                            </div>
                                
                            <div id ="viewDiv">
                                <a href="pg2.jsp" id = "viewButton">
                                    <button type ="button" class = "button viewButton">View more</button>
                                </a>
                            </div>
                            
                        </div> 
                                                
                                                                             
                        <div style="text-align:center">
                            <span class="dot" name= "radio1" onclick="changeImage(1, 'imageWS/logitech-g-pro-keyboard.jpg', 'pg1.jsp', 'pg3.jsp')"></span>
                            <span class="dot" name= "radio1" onclick="changeImage(2, 'imageWS/sony_WH-1000XM4.jpg', 'pg2.jsp', 'pg2.jsp')"></span>
                            <span class="dot" name= "radio1" onclick="changeImage(3, 'imageWS/glorious_model_o.jpg','pg3.jsp', 'pg1.jsp')"></span>
                        </div>
                    </div>


                </div>
            
                <div>
                    <img src="imageWS/logitech-g-pro-keyboard.jpg" id = "promoImage">
                </div>

            </div>

        </div>    
        
        <hr class ="center-bar item-center page-width">
        
        <div class = "flex-col item-center page-width" id ="catContainer"> 
            <div id = "catTitle"> 
                <h1>Categories</h1>
            </div>

            <div class="catSlider">
                    <div class="slider-wrapper">
                        <button id="prev-slide" class="slide-button material-symbols-rounded">
                            <
                        </button>
                        <ul class="image-list">
                          <a href =""><img class="image-item" src="imageWS/1.png" id="img-1" /></a>
                          <a href =""><img class="image-item" src="imageWS/2.png" id="img-2" /></a>
                          <a href =""><img class="image-item" src="imageWS/3.png" id="img-3" /></a>
                          <a href =""><img class="image-item" src="imageWS/4.png" id="img-4" /></a>
                          <a href =""><img class="image-item" src="imageWS/5.png" id="img-5" /></a>
                          <a href =""><img class="image-item" src="imageWS/6.png" id="img-6" /></a>
                          <a href =""><img class="image-item" src="imageWS/7.png" id="img-7" /></a>
                        </ul>
                        <button id="next-slide" class="slide-button material-symbols-rounded">
                            >
                        </button>
                  </div>
                    <div class="slider-scrollbar">
                        <div class="scrollbar-track">
                          <div class="scrollbar-thumb"></div>
                        </div>
                    </div>
            </div>
        </div>
        
        <div class = "flex-col page-width" id ="popularContainer">
              
            <div class = "popProdTitle flex-row" id = "popProducts">
                <h1>For Gaming</h1>     
            </div>
            
            <div class = "bigProdContainer flex-row item-center">
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
        
        
        
        
        
        <%@include file="footer.jsp" %>
        
        
        
        
        
    </body>
</html>


