/**
 * 帖子管理
 */
 
 var dialog;
 $(function(){
 	$(".main").height(parent.iframe_h - 16);
 	$(".post").height($(".main").height() - $(".top").height() - 20);
	initDom();	
	initEvent();
});

function initDom(){
	$("#from_date").ligerDateEditor({
		format: "yyyy-MM-dd",
		width: 110,
		height:25
	});
	$("#to_date").ligerDateEditor({
		format: "yyyy-MM-dd",
		width:110,
		height:25
	});
}

function initEvent(){
	$(".post-wrap").mouseover(function(){
		$(this).addClass('select');
	}).mouseout(function(){
		$(this).removeClass('select');
	});
	
	$(".post").on("click", ".title", function(){
		var id = $(this).attr("id"),
			sharezone = $(this).data("sharezone");
		dialog = $.ligerDialog.open({
			width:900,
			height:500,
			title: '',
			isDrag: true,
			url: url + '/shield_findReplyByQId.action?QId=' + id + '&sharezone=' + sharezone
		});
		$(".l-dialog-tc-inner").parents("tr").hide();
		
	});
}

$(function(){
	var pNum = $("#now").html();
	$(".jump-in").val(parseInt(pNum) + 1);
	$(".page-div").find('a[data-pagenum="'+pNum+'"]').addClass('now-page');
	$(".jump-in").keyup(function(e){
		if(e.keyCode == 13){
			$(".btn-sub").trigger("onclick");
			$(".sure").click();
		}
	});
	
	$(".set-top").click(function(){//置顶
		SetTop(this, "1");
	});
	
	$(".unset-top").click(function(){//取消置顶
		SetTop(this, "0");
	});		
	
	$(".set-best").click(function(){ //设置精华帖
		SetBest(this, "1");
	});
	
	$(".unset-best").click(function(){ //取消精华帖
		SetBest(this, "0");
	});		
	
	$(".delete").click(function(){ //删除
		if(confirm("确定要删除该帖子吗？")){
			Delete(this, "1");
		}
	});
	
	$(".un-delete").click(function(){ //恢复
		Delete(this, "0");
	});
});

function SetTop(obj, str){
	var id = $(obj).parents(".post-operate").find(".qid").val();
	$.post(url + '/question_setTop.action',{"QId": id, "con4": str}, function(data){
		var tip;
		if(data == "success"){
			if(str == "1"){//置顶
				$(obj).parents(".post-operate").find(".unset-top").show();
				$(obj).parents(".post-title").find(".post-top").removeClass("phide");
				tip = "置顶成功";
			}else{//取消置顶
				$(obj).parents(".post-operate").find(".set-top").show();
				$(obj).parents(".post-title").find(".post-top").addClass("phide");
				tip = "取消置顶成功";
			}
			$(obj).hide();
			FadeTip(tip);
		}else{
			alert("操作失败");
		}
	});
}

function SetBest(obj, str){
	var id = $(obj).parents(".post-operate").find(".qid").val();
	$.post(url+'/question_setNice.action',{"QId":id,"con5":str}, function(data){
		var tip = "设置成功";
		if(data == "success"){
			if(str == "1"){
				$(obj).parents(".post-operate").find(".unset-best").show();
				$(obj).parents(".post-title").find(".post-best").removeClass("phide");
			}else{
				$(obj).parents(".post-operate").find(".set-best").show();
				$(obj).parents(".post-title").find(".post-best").addClass("phide");
				tip = "成功取消精华帖";
			}
			$(obj).hide();
			FadeTip(tip);
		}else{
			alert("操作失败");
		}
	});
}

function Delete(obj, str){//删帖
	var id = $(obj).parents(".post-operate").find(".qid").val();
	$.post(url+'/question_delete.action',{"QId":id, "con6": str}, function(data){
		var tip = "删帖成功";
		if(data == "success"){
			if(str == "1"){
				$(obj).parents(".post-operate").find(".un-delete").show();
				$(obj).parents(".post-content").addClass("del-color");
			}else{
				$(obj).parents(".post-operate").find(".delete").show();
				$(obj).parents(".post-content").removeClass("del-color");
				tip = "成功恢复帖子";
			}
			$(obj).hide();
			FadeTip(tip);
		}else{
			alert("操作失败");
		}
	});
}

function FadeTip(str){//操作提示，500毫秒后消失
	$(".tip-wrap").find("span").html(str).addClass("icon-success");
	$(".tip-wrap").fadeIn(500, function() {
		setTimeout(function() {
			$(".tip-wrap").fadeOut(500, function() {
				$(".tip-wrap").hide();
			});
		}, 1000);
	});
}

function Jump(obj){
	var now_page = parseInt($(".jump-in").val()),//输入值
		max_page = parseInt($("#total").text()); //最大页数
	if($(".jump-in").val() == ""){
		$(".jump-in").focus();
	}else if(now_page > max_page){
		$(".jump-in").select();
	}else{
		$(obj).attr("href", url+"/listquestion_findByQTime.action?currentPage=" + $(".jump-in").val() + "&rows=10");
	}
}
function queryQuestion(){
	if($("#poster").val() == "" && $("#from_date").val() == "" && $("#to_date").val() == ""){
		$("#poster").focus();
	}else if($("#poster").val() == "" && ($("#from_date").val() == "" || $("#to_date").val() == "")){
		$("#from_date").focus();
		$("#to_date").focus();
	}else{
		alert($("input[name=username]").val());
		$("form").submit();
	}	
}