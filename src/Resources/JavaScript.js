
async function myFunction(button) {


console.log(button.getAttribute("id"));

var xCo = button.getAttribute("data-x");
var yCo = button.getAttribute("data-y");

pieceSelected = {x : xCo , y : yCo};
console.log(pieceSelected);




}




