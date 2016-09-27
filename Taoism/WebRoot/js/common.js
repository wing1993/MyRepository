function aSelected(obj){
	$(obj).addClass("selected").siblings().removeClass("selected");
}

function cancel(){
	window.close();
}