
var spinner_1=new spin_display("#slot_1",upon_win);
var spinner_2=new spin_display("#slot_2",upon_win);
var spinner_3=new spin_display("#slot_3",upon_win);

var win=false;

$(document).ready(function(){	

$(".display").removeClass("win");
	
//alert('BE_loaded '); 
spinner_1.auto_spin();
spinner_2.auto_spin();
spinner_3.auto_spin();


$(".spin_pannel").click(function() {

});
		
});

function upon_win(){
if(spinner_1.spin_interrupt && spinner_2.spin_interrupt && spinner_3.spin_interrupt){
	if(spinner_1.item==spinner_2.item && spinner_1.item==spinner_3.item){
		$(".display").addClass("win");
		$("input.status").val("win");
	}else{ 
        $("input.status").val("lose");
	}
}

}


function spin_display(target,win_event){
var _this=this;
this.spin_interrupt=false; this.spin=_spin; this.stop_spin=_stop_spin; this.target=target; this.auto_spin=_auto_spin; this.win=win_event
this.item=0;
this.max_spin_time=5000; this.min_spin_time=2000; 


function _auto_spin(){
_this.spin();
_this.stop_spin(Math.floor((Math.random()*_this.max_spin_time)+_this.min_spin_time));
}

function _spin(){
if(!_this.spin_interrupt){
$(_this.target).stop();
$(_this.target).animate({scrollLeft: 0+"px",scrollTop:0+"px"},0);
$(_this.target).animate({scrollLeft: 0+"px",scrollTop:800+"px"},900);
//console.log("spinning: "+_this.spin);
window.setTimeout(_this.spin, 450);
}
}


function _stop_spin(at_time){

window.setTimeout(function(){console.log("stop initiated");_this.spin_interrupt=true;$(_this.target).stop();$(_this.target).scrollTop($(_this.target).scrollTop()-($(_this.target).scrollTop()%75)); _this.item=$(_this.target).scrollTop();_this.win();},at_time);
}


}
