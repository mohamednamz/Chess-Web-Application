

setInterval(refresh,10000);

async function refresh() {

   var response = await fetch("/render");

   document.getElementById("board").innerHTML = await response.text();

   console.log("boy");

}

var boxSelected;

async function myFunction(button) {

    console.log(button.getAttribute("id"));

    var xCo = button.getAttribute("data-x");
    var yCo = button.getAttribute("data-y");

    boxSelected = {x : xCo , y : yCo};
    console.log(boxSelected);

    var url = `/makeMove?x=${xCo}&y=${yCo}`

    var response = await fetch(url);

    document.getElementById("board").innerHTML = await response.text();
}






