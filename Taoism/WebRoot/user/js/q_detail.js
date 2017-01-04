$(function(){
	$("#btn").click(function(){
		$("body").scrollTop($("body").height());//滚动到最底部
		$(".ke-edit-iframe").focus();
	});
	$(".comment").click(function(){
		$(this).parent().next().find('.add-re').val('');
		$(this).parent().next().toggle();
	});
	
	$("#returntop").click(function(){
		$("body").scrollTop(0);
	});
	$(".icon-rtop").mouseover(function(){
		$("#returntop").css("display","block");
	}).mouseleave(function(){
		$("#returntop").css("display","none");
	});
	$("#returntop").hover(function(){
		$(this).css("display","block");
	},function(){
		$(this).css("display","none");
	});
	
	$(document).scroll(function(){
		if($(document).scrollTop()!=0){
			$(".icon-rtop").css("display","block");
		}else{
			$(".icon-rtop").css("display","none");
		}
	});
	
	alert($(".r-fold").length);
});

