var tab, h, w,
	iframe_h;

function initDom(){	
	h = $(window).height() - $(".top").height();
	w = $(window).width();
	$(".main").width(w - 30);
	$(".left").height(h);
	$("#main_bd").height(h);
	$(".right").width(w -30 - $(".left").width());
	tab = $("#page_tab").ligerTab({
		height: 200, //高度
		changeHeightOnResize: !0, //自适应高度
		dragToMove: true
	});
}

function initEvent(){
	$(".page-tab").on("mouseover", "li", function(){
		$(this).find(".l-tab-links-item-close").addClass("hover-close");
	}).on("mouseout", "li", function(){
		$(this).find(".l-tab-links-item-close").removeClass("hover-close");
	});
	
	$(".page-tab").on("click", "li", function(){
		var id = $(this).attr("tabid");
		$(".sub-item li[tabid="+ id +"]").addClass("selected").siblings().removeClass("selected");
		$(".sub-item li[tabid="+ id +"]").parent().siblings().find("li").removeClass("selected");
	});
	$(".left-item").click(function(){
		$(this).next().slideToggle("slow");
		//$(this).next().children("li").removeClass("selected");
	});

	$(".sub-item li").click(function(){
		$(this).siblings().removeClass("selected");
		$(this).parent().siblings().find("li").removeClass("selected");
		$(this).addClass("selected");
		var loc=$(($(this).find("span"))[1]).text();
		var tid = $(this).attr("tabid"),
			_url = $(this).attr("tab_url");
			
		tab.isTabItemExist(tid) ? (tab.selectTabItem(tid), tab.reload(tid)) : tab.addTabItem({ //动态添加标签页
			tabid: tid,
			text: loc,
			url: _url,
			showClose: 1
		});
		$("#page_tab").find("iframe").height(h - $(".l-tab-links").height());
	});
	
	//管理员退出
	$(".logout").click(function(){
		$.post('',function(data){
		
		});
	});
}

$(function(){
	initDom();
	initEvent();	
	iframe_h = $("#main_bd").height() - $(".l-tab-links").height();
});