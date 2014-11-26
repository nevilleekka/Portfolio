
$(document).ready(function(){		
send_spin();
		
});

function send_spin(){
console.log("sending self: "+parent.screen_1);
parent.screen_1=$("body");
if(parent.screen_1==null){
window.setTimeout(send_spin, 100);}
}