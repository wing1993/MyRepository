$(function(){
	$("#btn").click(function(){
		$("body").scrollTop($("body").height());//滚动到最底部
		$(".ke-edit-iframe").focus();
	});
	$(".comment").click(function(){
		$(this).parent().next().toggle();
	});
	
	
});

