var grid;
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
		mtype: "POST",
		url: '/Taoism/list_getInfoUserList.action?method=userInfo',//'admin/pages/test.json',
		rowNum : 10,//一页显示多少条
		rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
		pager : '#pager',//表格页脚的占位符(一般是div)的id
		sortname : 'id',//初始化的时候排序的字段
		sortorder : "desc",//排序方式,可选desc,asc
		mtype : "post",//向后台请求数据的ajax的类型。可选post,get
		rownumbers: true,
		jsonReader: {  
		    root:"gridModel",     
		    page: "page",     
		    total: "total",  
		    records: "record",  
		    repeatitems : false  
		}
	});
}

function btnFormat(cellvalue, options, rowObject){
	var ope = cellvalue == 1 ? "解除禁言" : "禁言";
	return '<input type="button" class="slience" value="' + ope + '" data-ope="' + cellvalue + '">&nbsp;';//<input type="button" class="join-black" value="拉黑">&nbsp;
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
//		jQuery("#bigset").jqGrid('setGridParam',{url:"bigset.php?nm_mask="+nm_mask+"&cd_mask="+cd_mask,page:1}).trigger("reloadGrid");
		grid.setGridParam({
			url: '/Taoism/list_getUserGridModel.action?method=userInfo&username='+ key
		}).trigger('reloadGrid');
	});
	
	$("body").on("click", ".slience", function(){
		var id = $(this).parents("tr").attr("id"),
			new_url = "/Taoism/user_shielUser.action?userId="+id;
			grid.setGridParam({
				url: new_url
			}).trigger('reloadGrid');
		dataStop(id);	
	});
	
	$("#user_type").change(function(){
		var type = $(this).val(),
			old_url = '/Taoism/list_getUserGridModel.action?method=userInfo',
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
function dataStop(id){
	var rows = grid.getRowData();
	console.log(rows);
	for(var i = 0; i < rows.length; i++){
		if(rows[i].userId == id){
			if(rows[i].con7 == 0){
				$(".stop-tip").addClass("stop-err").find("span").html("禁言失败").addClass("icon-error");
			}else{
				$(".stop-tip").addClass("stop-success").find("span").html("禁言成功").addClass("icon-success");
			}
			$(".stop-tip").fadeIn(500, function() {
				setTimeout(function() {
					$(".stop-tip").fadeOut(500, function() {
						$(".stop-tip").hide();
					});
				}, 1000);
			});
			var __id = rows[i].userId;
			$tds = grid.find('tr[id="' + __id + '"]').find('td[role="gridcell"]').addClass("td-stop-color");
			break;
		}else if(rows[i].con7 == 1){
			var __id = rows[i].userId;
			$tds = grid.find('tr[id="' + __id + '"]').find('td[role="gridcell"]').addClass("td-stop-color");
		}
	}
}