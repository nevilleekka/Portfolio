function vector2(){this.x=0; this.y=0;};   // simple vector class
var currentMouse = { pos: new vector2()};
var fire=false;
var flipped_right=false;
var draggy_mouth_x=0;
var draggy_mouth_y=0;
var rnd_draggy_pos_x=0;
var rnd_draggy_pos_y=0;

var player_health=100;
var draggy_damage=0;

var game_over=false;
var game_won=false;

$(document).ready(function(){

alert('Site Loaded ');

$("iframe").animate({width:'0px', height:'0px'},"fast");
$("#result").text('I am Your Mouse');
$("iframe").hide();

$(".dragon").css({ left: '0px',top:'0px' });


p_loop(); //game heading loop

background_loop(); //background colour loop
draggy_loop(); //main dragon loop
spit_fire();  //main dragon firing loop



$(".dragon").mouseenter(function(){
$("iframe").show();
$("iframe").animate({width:'400px', height:'300px'},"fast");
$("#result").text("Health: "+player_health+" Damage: "+draggy_damage);

});

$(".dragon").mouseleave(function(){
$("iframe").hide();
$("iframe").animate({width:'0px', height:'0px'},"fast");
$("#result").text("Health: "+player_health+" Damage: "+draggy_damage);
fire=false;

}); 
 
$(".dragon").click(function(){
draggy_damage=draggy_damage+5;
if(draggy_damage>50){game_won_effect();}
$("iframe").show();
$("embed").attr('src', 'http://www.myinstants.com/media/sounds/family-guy-stewies-annoying-laugh-5.mp3');

});

$("body").click(function(){
fire=true;
 //console.log("firing..");
});


$("button").click(function(){
    $(".author").animate({height:'toggle'},"fast");
});

 
});


$(document).mousemove(function(event) {
	var label_Pos = { x: -1, y: -1 };
        currentMouse.pos.x = event.pageX+50;
        currentMouse.pos.y = event.pageY;
		label_Pos.x=event.pageX+50;
		label_Pos.y= event.pageY;
		$(".item").animate({left:label_Pos.x.toString()+'px'},0);
		$(".item").animate({top:label_Pos.y.toString()+'px'},0);
		//console.log("Mouse = "+currentMousePos.x+" || "+currentMousePos.y);
    });
	



	
	

function p_loop(){
if(!game_over || !game_won){
$("#messageBoard").animate({fontSize:'1em'},"fast");
$("#messageBoard").animate({fontSize:'3em'},"slow");
 }else{$("#messageBoard").animate({fontSize:'3em'},"slow");}
 window.setTimeout(function() { p_loop() ;}, 800)

}

function draggy_loop(){


var draggy_pos_x=$(".dragon").position().left;
var draggy_pos_y=$(".dragon").position().top;

var draggy_center_x=190+draggy_pos_x;
var draggy_center_y=100+draggy_pos_y;

if(draggy_pos_x==rnd_draggy_pos_x && draggy_pos_y==rnd_draggy_pos_y){
 rnd_draggy_pos_x=Math.round( Math.random()*400);
 rnd_draggy_pos_y=Math.round( Math.random()*400);
 $(".dragon").animate({left:rnd_draggy_pos_x.toString()+'px', top: rnd_draggy_pos_y.toString()+'px'});

}

var distance=Math.sqrt((draggy_center_x-currentMouse.pos.x)*(draggy_center_x-currentMouse.pos.x)+(draggy_pos_y-currentMouse.pos.y)*(draggy_pos_y-currentMouse.pos.y));

if(distance<450){fire=true;}else{fire=false;}

if((draggy_center_x-currentMouse.pos.x)<0){ // flip dragon horizontally
$(".dragon").addClass("img_flip_H_pos");
$(".dragon").removeClass("img_flip_H_neg");
flipped_right=true;
}else{
$(".dragon").addClass("img_flip_H_neg");
$(".dragon").removeClass("img_flip_H_pos");
flipped_right=false;
}

if(flipped_right==true){draggy_mouth_x= 210+draggy_pos_x; draggy_mouth_y=50+draggy_pos_y; }else{draggy_mouth_x= 50+draggy_pos_x; draggy_mouth_y=50+draggy_pos_y;}
 if(!game_over && !game_won){
window.setTimeout(function() { draggy_loop() ;}, 0)
}
}

function background_loop(){
var bg_colour_R=Math.floor(Math.random()*255);
var bg_colour_G=Math.floor(Math.random()*255);
var bg_colour_B=Math.floor(Math.random()*255);

$("body").animate({ backgroundColor: "rgb("+bg_colour_R.toString()+","+bg_colour_G.toString()+","+bg_colour_B.toString()+")"},"slow");
 if(!game_over && !game_won){
 window.setTimeout(function() { background_loop() ;}, 800);
 }
}

function spit_fire(){
if(fire==true){
var fire_ball=create_fire_ball();  

$(".fire_ball").css({ left: draggy_mouth_x.toString()+'px',top:draggy_mouth_y.toString()+'px' });

if(flipped_right){
$(".fire_ball").animate({left:(draggy_mouth_x+400)+'px',top:(draggy_mouth_y+400)+'px'});
}else {
$(".fire_ball").animate({left:(draggy_mouth_x-400)+'px',top:(draggy_mouth_y+400)+'px'});
}
}else{
window.setTimeout(function() {$( ".fire_ball" ).remove();},2000);
}
 if(!game_over && !game_won){
window.setTimeout(function() { spit_fire() ;}, 150);
}
}

function create_fire_ball()
{
var fire_ball="<img class=\"fire_ball\" src=\"animated_fireball.gif\"/>";
$("body").append(fire_ball);    
//$(".fire_ball").insertAfter(".fire_ball_array");

  $(".fire_ball").hover(function(){
if(player_health<=0){player_health=0;game_over_effect();}else{
player_health=player_health-5; }
console.log("health: "+player_health);
});

return fire_ball;                 
}


function game_over_effect(){
game_over=true;
$("#messageBoard").text("DRAGGY HAS CLAIMED YOUR LIFE");
$("#messageBoard").addClass("game_over")
}

function game_won_effect(){
game_won=true;
$("#messageBoard").text("You have killed the DRAGGY..GG");
$("#messageBoard").addClass("game_won")
}

