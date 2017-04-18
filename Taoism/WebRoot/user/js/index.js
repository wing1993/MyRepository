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
	var li_w=parseInt($li.css("padding-left"))+parseInt($li.css("padding-right"))+$li.width()+25;

	$sub_box.css("width",$li.length*li_w);
	var left_p=0;//向左移动的图片数量
	var right_p=$li.length-5;//被隐藏的图片，每次显示5张图

	$(".ico-left").click(function(){
		if($(".ico-right").css("display")=="none"){
			$(".ico-right").css("display","block");
		}
		if(left_p<1){
			$(".ico-left").toggle();
			return;
		}
		left_p--;
		right_p++;
		$sub_box.animate({left:"+="+li_w+"px"}, 800);
	});

	$(".ico-right").click(function(){
		if($(".ico-left").css("display")=="none"){
			$(".ico-left").css("display","block");
		}
		if(right_p<1){
			$(".ico-right").toggle();
			return;
		}
		left_p++;
		right_p--;
		$sub_box.animate({left:"-="+li_w+"px"}, 800);
	});
});


//大师简介
$(function(){
	//关闭大师简介
	$(".close-ico").click(function(){
		$(".popover").slideUp();
	});
	/*$(".master-data img").click(function(){
		$(".popover").fadeIn();
	});*/
	$(".master-sub-box").mouseover(function(){
		$(".master-sub-box span").css("display","block");
	}).mouseout(function(){
		$(".master-sub-box span").css("display","none");
	});
});

function showDetail(obj){
	$(".hdashi_name").text($(obj).parent().next().children().text());
	$(".living_city").text($(obj).parent().next().next().children().text());
	$(".gender").text($(obj).parent().parent().children(".h_gender").val());
	$(".birthday").text($(obj).parent().parent().children(".h_birth").val());
	$(".phone").text($(obj).parent().parent().children(".h_phone").val());
	$(".qq").text($(obj).parent().parent().children(".h_qq").val());
	$(".mail").text($(obj).parent().parent().children(".h_mail").val());
	$(".wexin").text($(obj).parent().parent().children(".h_weixin").val());
	$(".po-introduce").text($(obj).parent().parent().children(".h_introduce").val());
	$(".po-pic-box img").attr("src",$(obj).attr("src"));
	$(".popover").fadeIn();
}

//显示最近的信息
$(function(){
	$(".r-box div[class='r-message']").remove();
	$.post("/Taoism/message_findLatest.action",function(data){
		if(null!=data.messages){
			$(".r-box").css("display","block");
		}
		$.each(data.messages,function(i,value){
			
				var str="<div class='r-message'><a target='_blank' href='user/pages/message_content.jsp?author="+value.author
					+"&publish_time="+value.publishTime.split('.')[0]+"&message_content="+value.messageContent+
					"&message_title="+value.con1+"' title='"+value.con1+"'>"+value.con1+"</a></div>";
				$(".r-more").before(str);
			
		});
	});
	
	$(".jump-in").keyup(function(e){
		if(e.keyCode == 13){
			$(".btn-sub").trigger("onclick");
//			$(".sure").click();
		}
	});
});

function Jump(obj){
	var now_page = parseInt($(".jump-in").val()),//输入值
		max_page = parseInt($("#total").text()); //最大页数
	if($(".jump-in").val() == ""){
		$(".jump-in").focus();
	}else if(now_page > max_page){
		$(".jump-in").select();
	}else{
//		$(obj).attr("href", url+"/listquestion_findByQTime.action?currentPage=" + $(".jump-in").val() + "&rows=10");
		findData(obj);
	}
}