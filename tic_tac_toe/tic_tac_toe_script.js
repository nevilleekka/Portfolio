$(document).ready(function(){

alert('Site Loaded ');

background_loop();

var entry=new Boolean(0);
var token='.';

var cell_array= 

$(".square").click(function(event){
entry=!entry;


if(entry){token='X';} else{token='O';}

try{
if(entry){
$("#"+event.target.id+" p").addClass("x");
$("#"+event.target.id+" p").removeClass("o");
} else{
$("#"+event.target.id+" p").addClass("o");
$("#"+event.target.id+" p").removeClass("x");
}

console.log("clicked "+event.target.id);
$("#"+event.target.id+" p").text(token);

}
catch(err)
  {
  if(entry){
  $("#"+$( event.target ).parent().get(0).id+" p").addClass("x");
  $("#"+$( event.target ).parent().get(0).id+" p").removeClass("o");
  } else{
  $("#"+$( event.target ).parent().get(0).id+" p").addClass("o");
  $("#"+$( event.target ).parent().get(0).id+" p").removeClass("x");
  
  node1=( event.target ).style["background"].color="red";
console.log(event.target);

  }
  
console.log("p element = "+$( event.target ).parent().get(0).id);
$("#"+$( event.target ).parent().get(0).id+" p").text(token);
  }

  
  
});


  
  
});


function background_loop(){
var bg_colour_R=Math.floor(Math.random()*255);
var bg_colour_G=Math.floor(Math.random()*255);
var bg_colour_B=Math.floor(Math.random()*255);

$("body").animate({ backgroundColor: "rgb("+bg_colour_R.toString()+","+bg_colour_G.toString()+","+bg_colour_B.toString()+")"},"slow");
 window.setTimeout(function() { background_loop() ;}, 800)
}
