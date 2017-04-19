$(function(){
	$(".main-box").height($(".main-box-fixed").height()+parseInt($(".main-box-fixed").css("padding-top"))*2);
	
	$("#btn").click(function(){
		$("body").scrollTop($("body").height());//滚动到最底部
		$(".ke-edit-iframe").focus();
	});
	$("body").on("click", ".comment", function(){
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
	
	$("#forward").click(function(){
		$(".share-wrap").fadeToggle(500);
	});
	
	$("#share_sure").click(function(){
		var radios = $("input[type=radio]:checked").length;
		if(radios == 0){
			$(".for-tip").show();
		}else{
			var sharezone = $("input[type=radio]:checked").val(),
				QId = $(".QId").val();
				var $ob = $(this);
			$.post('/Taoism/question_daShiForwardPost.action',{"sharezone": sharezone, "QId": QId},function(data){
				console.log(data);
				if(data == "success"){
					alert("转发成功！");
					$ob.parents(".for-wrap").hide();
//					window.close();
				}
			});
		}
	});
	
	$("input[type=radio]").click(function(){
		$(".for-tip").hide();
	});
	
	$(".back").click(function(){
		window.location.href="/Taoism/dashi_load.action";
	});
});

