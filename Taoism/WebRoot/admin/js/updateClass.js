/**
 * 身份升级
 */
var grid;
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
			{label:'用户名', name:'username', index:'username', width:120, align:'center'},
			{label:'注册类型', name:'userType', index: 'userType', width:100, align:'center'},
			{label:'真实姓名', name:'realname', index:'realname', width:100, align:'center'},
			{label:'个人简介', name:'introduce', index:'introduce', width:300, align:'center',classes: 'ellipsis'},
			{label:'升级类型', name:'con1', index: 'updateType', width:100, align:'center'},
			{label:'发帖次数', name:'sumQuestion', index:'postTimes', width:100, align:'center'},
			{label:'回复次数', name:'replyTimes', index:'replyTimes', width:100, align:'center'},
			{label:'操作', width:120, align:'center', classess: 'ope-width', formatter: btnFormat}
		];
	grid = $("#update_tb").jqGrid({
		colModel: columns,
		width:w,
		height:h,
		datatype: 'json',
		mtype: "GET",
		url: '/Taoism/list_getUpgradeUserList.action',//'admin/pages/test.json',
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
	return '&nbsp;<input type="button" value="升级" class="update">'; //<input type="button" value="拒绝" class="ignore">
}

function initEvent(){
	$("#update_tb").on("click", ".update", function(e){
		var id = $(this).parents('tr').attr('id'),
			data = grid.getRowData(id);
		var _html = data.username + '将由<span class="old-role"> ' + data.userType + ' </span>升级为<span class="new-role"> ' + data.realname + '</span>';
		$.ligerDialog.confirm(_html, 
			function (data) {
				if(data){
					$.post('', {userId: id}, function(data){
						
					});
					grid.delRowData(id);
				}
			}
		);	
	});
}