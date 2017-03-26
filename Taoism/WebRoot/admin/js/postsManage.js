/**
 * 帖子管理
 */
 
 var dialog;
 $(function(){
 	$(".main").height(parent.iframe_h - 16);
 	$(".post").height($(".main").height() - $(".top").height() - 40);
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
		
		
//    	<action name="shield_*" class="questionAction" method="{1}">
//    		<result name="replys" type="dispatcher">/admin/pages/shieldReply.jsp</result>
//    	</action>
		
	});
	
	$(".post").on("click", "input.delete", function(){
		if(confirm("确定要删除该帖子吗？")){
			
			window.location.href = '';
		}
	});
}

function queryQuestion(){
	alert("1");
	$("form").submit();
}