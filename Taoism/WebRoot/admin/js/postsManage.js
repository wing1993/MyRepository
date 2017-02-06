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
		dialog = $.ligerDialog.open({
			width:900,
			height:500,
			title: '',
			isDrag: true,
			url: '/Taoism/admin/pages/shieldReply.jsp'
		});
		$(".l-dialog-tc-inner").parents("tr").hide();
	});
	
	$(".post").on("click", "input.delete", function(){
		if(confirm("确定要删除该帖子吗？")){
			
			window.location.href = '';
		}
	});
}