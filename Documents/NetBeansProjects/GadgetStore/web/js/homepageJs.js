function changeImage(index, fileName, buyName, viewName) {
        var titleElement = document.querySelector(".promoTitle");
        let img = document.querySelector("#promoImage");
        let nextBuy = document.querySelector("#buyButton");
        let nextView = document.querySelector("#viewButton");
        
        // Update the title and image based on the index
        if (index === 1) {
            titleElement.textContent = "Logitech Keyboard";
            img.setAttribute("src", fileName);
            nextBuy.setAttribute("href", buyName);
            nextView.setAttribute("href", viewName);
            
        } 
        else if (index === 2) {
            titleElement.textContent = "Sony Headphones";
            img.setAttribute("src", fileName);
            nextBuy.setAttribute("href", buyName);
            nextView.setAttribute("href", viewName);
        }
        else if (index === 3) {
            titleElement.textContent = "Glorious Mouse";
            img.setAttribute("src", fileName);
            nextBuy.setAttribute("href", buyName);
            nextView.setAttribute("href", viewName);
        }
}

