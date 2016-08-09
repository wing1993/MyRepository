$(function(){
	$(".left-item").click(function(){
		$(this).next().slideToggle("slow");
	});

	$(".sub-item li").click(function(){
		$(this).siblings().removeClass("selected");
		$(this).parent().siblings().find("li").removeClass("selected");
		$(this).addClass("selected");
		var loc=$(($(this).find("span"))[1]).text();
		$(".loc").text(loc);//显示当前位置
		
		if(loc=="注册审核"){			
			$("#r_iframe").attr("src","../pages/rsgCheck.jsp");
		}else if(loc=="身份升级"){
			$("#r_iframe").attr("src","../pages/updateClass.jsp");
		}else if(loc=="用户信息"){
			$("#r_iframe").attr("src","../pages/userInfo.jsp");
		}
	});

});