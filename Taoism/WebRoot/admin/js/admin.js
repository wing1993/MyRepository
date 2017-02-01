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
		dragToMove: true,
		onBeforeAddTabItem: function(a) { //	增加前事件
//			setCurrentNav(a);
		},
		onAfterSelectTabItem: function(a) { //选择后事件
//			setCurrentNav(a);
		}
	});
}

function initEvent(){
	$(".left-item").click(function(){
		$(this).next().slideToggle("slow");
		//$(this).next().children("li").removeClass("selected");
	});

	$(".sub-item li").click(function(){
		$(this).siblings().removeClass("selected");
		$(this).parent().siblings().find("li").removeClass("selected");
		$(this).addClass("selected");
		var loc=$(($(this).find("span"))[1]).text();
		$(".loc").text(loc);//显示当前位置
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
}

$(function(){
	initDom();
	initEvent();	
	iframe_h = $("#main_bd").height() - $(".l-tab-links").height();
});