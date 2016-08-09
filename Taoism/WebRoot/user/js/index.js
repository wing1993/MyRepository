function Fold_Open(obj){
	if($(obj).text()=="-"){
		$(obj).attr("title","展开");
		//$(".master-box").css("display","none");
		$(".master-box").slideToggle("slow");
		$(obj).text("+");
	}else{
		$(obj).attr("title","折叠");
		//$(".master-box").css("display","block");
		$(".master-box").slideToggle("slow");
		$(obj).text("-");
	}
}
function show_sp(){
	$(".sico").toggle();
}

//图片切换
$(function(){
	$li=$(".master-data");
	$sub_box=$(".master-w");

	var li_w=parseInt($li.css("padding-left"))+parseInt($li.css("padding-right"))+$li.width();

	//alert(li_w);
	$sub_box.css("width",$li.length*li_w);
	var left_p=0;//向左移动的图片数量
	var right_p=$li.length-6;//被隐藏的图片，每次显示5张图

	$(".ico-left").click(function(){
		if($(".ico-right").css("display")=="none"){
			$(".ico-right").css("display","block");
		}
		if(left_p<1){
			$(".ico-left").toggle();
			//$("ico-left").css("display","none");
			return;
		}
		left_p--;
		right_p++;
		//$sub_box.animate({left:'+=185px'}, 800);
		$sub_box.animate({left:"+="+li_w+"px"}, 800);
	});

	$(".ico-right").click(function(){
		if($(".ico-left").css("display")=="none"){
			$(".ico-left").css("display","block");
		}
		if(right_p<1){
			$(".ico-right").toggle();
			//$("ico-right").css("display","none");
			return;
		}
		left_p++;
		right_p--;
		//$sub_box.animate({left:'-=185px'}, 800);
		$sub_box.animate({left:"-="+li_w+"px"}, 800);
	});
});


//大师简介
$(function(){
	//关闭大师简介
	$(".close-ico").click(function(){
		$(".popover").slideUp();
	})
	$(".master-data img").click(function(){
		$(".popover").fadeIn();
	});
});