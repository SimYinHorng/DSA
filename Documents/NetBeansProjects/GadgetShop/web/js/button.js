
function plusQty() {
    const plusButton = document.getElementById("plus");
    const inputField = document.getElementById("qty");
    let currentValue = parseInt(inputField.value);
    console.log(currentValue);
    inputField.value = currentValue + 1;
}

function minusQty() {
    const minusButton = document.getElementById("minus");
    const inputField = document.getElementById("qty");

    let currentValue = parseInt(inputField.value);
    
    if(currentValue >1){
      
    inputField.value = currentValue - 1;      
    }

}