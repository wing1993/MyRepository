var grid, red_ids = [];
/**
 * 用户信息
 */
$(function(){
	$(".main").height(parent.iframe_h - 16);
	createGrid();
	initEvent();
});

function createGrid(){
	var	w = $(".main").width();
	var h = $(".main").height() - $(".search").height() - 65;
	var columns = [
			{label:'userId', name:'userId', index:'userId', hidden: true, key: true},
			{label:'用户名', name:'username', index:'username', width:80, align:'center'},
			{label:'注册类型', name:'userType', index: 'userType', width:80, align:'center'},
			{label:'性别', name:'gender', index:'gender', width:40, align:'center'},
			{label:'居住城市', name:'city', index:'city',width:80, align:'center'},
			{label:'手机', name:'phone', index:'phone', width:100, align:'center'},
			{label:'真实姓名', name:'realname', index:'realname', width:80, align:'center'},
			{label:'生日', name:'birthday', index:'birthday', width:100, align:'center'},
			{label:'qq', name:'qq', index:'qq', width:100, align:'center'},
			{label:'微信', name:'weixin', index:'weixin', width:100, align:'center'},
			{label:'邮箱', name:'mail', index:'mail', width:120, align:'center'},
			{label:'个人简介', name:'introduce', index:'introduce', width:180, align:'center',classes: 'ellipsis'},
			{label:'操作', name:'con7', index:'con7', width:80,align:'center',formatter: btnFormat}
		];
	grid = $("#r_tb").jqGrid({
		colModel: columns,
		width:w,
		height:h,
		datatype: 'json',
		mtype: "GET",
		url: url + '/list_getInfoUserList.action?method=userInfo',//'admin/pages/test.json',
		rowNum : 10,//一页显示多少条
		rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
		pager : '#pager',//表格页脚的占位符(一般是div)的id
		sortname : 'id',//初始化的时候排序的字段
		sortorder : "desc",//排序方式,可选desc,asc
		rownumbers: true,
		jsonReader: {  
		    root:"gridModel",     
		    page: "page",     
		    total: "total",  
		    records: "record",  
		    repeatitems : false  
		},
		gridComplete:function(){
			addStopCls();
		}
	});
}

function btnFormat(cellvalue, options, rowObject){
	var _class = cellvalue == 1 ? "show" : "hide",
		_class1 = _class == "show" ? "hide" : "show";
	rowObject.con7 == "1" && red_ids.push(rowObject.userId);
	return '<input type="button" class="slience '+ _class1 +'" value="禁言" data-ope="0">&nbsp;'+
			'<input type="button" class="slience '+ _class +'" value="解除禁言" data-ope="1">';
//	return '<a href="javascript:;" class="slience" data-ope="' + cellvalue + '">'+ope+'</a>&nbsp;';
}

function initEvent(){
	$("#search_val").keyup(function(e){
		if(e.keyCode == 13){
			$(".search-btn").click();
		}
	});
	$(".search-btn").click(function(){
		var key = $(".search-wrap input").val();
		console.log(key);
		grid.setGridParam({
			url: url + '/list_getUserGridModel.action?method=userInfo&username='+ key
		}).trigger('reloadGrid');
	});
	
	$("body").on("click", ".slience", function(){
		var $obj = $(this),
			id = $(this).parents("tr").attr("id");
			ope = $(this).data("ope"),
			state = (ope == null || ope == "0") ? "1" : "0",
			$.post(url + "/user_shielUser.action?userId="+id+"&con7="+state,{},function(str){
				if(str == "success"){
			 		$obj.parents("tr").toggleClass("td-stop-color");
					$obj.hide().siblings().show();
				 	dataStop(str, state);
			 	}
			});
	});

	
	$("#user_type").change(function(){
		var type = $(this).val(),
			old_url = url + '/list_getUserGridModel.action?method=userInfo',
			new_url = type == '所有类型' ? old_url : old_url + '&userType=' + type;
		grid.setGridParam({
			url: new_url
		}).trigger('reloadGrid');
	});
}

/**
 * 将禁言色用户设置为红色，id不为空时执行禁言操作，为空则遍历表格
 * @param {} id
 */
function dataStop(str,ope){
	var tip = "";
	if(str == "success"){
		tip = ope == "0" ? "解除禁言成功" : "禁言成功";
		$(".stop-tip").addClass("stop-success").find("span").html(tip).addClass("icon-success");
	}else if(str == "error"){
		tip = ope == "0" ? "解除禁言失败" : "禁言失败";
		$(".stop-tip").addClass("stop-err").find("span").html(tip).addClass("icon-error");
	}
	
	typeof(str) != "undefined" && $(".stop-tip").fadeIn(500, function() {
		setTimeout(function() {
			$(".stop-tip").fadeOut(500, function() {
				$(".stop-tip").hide();
			});
		}, 1000);
	});
	
}

/**
 * 禁言添加背景色
 * @param {} $grid
 */
function addStopCls() {
	var rows = grid.getRowData();
	for(var i = 0; i < rows.length; i++){		
		for(var j = 0; j < red_ids.length; j++){
			if(rows[i].userId == red_ids[j]){
				var __id = rows[i].userId;
				$tds = grid.find('tr[id="' + __id + '"]').addClass("td-stop-color");
				break;
			}
		}		
	}
}