var grid;
/**
 * 注册审核
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
			{label:'用户名', name:'username', index:'username', width:120, align:'center'},
			{label:'注册类型', name:'userType', index: 'userType', width:80, align:'center'},
			{label:'性别', name:'gender', index:'gender', width:60, align:'center'},
			{label:'居住城市', name:'city', index:'city',width:80, align:'center'},
			{label:'手机', name:'phone', index:'phone', width:130, align:'center'},
			{label:'真实姓名', name:'realname', index:'realname', width:100, align:'center'},
			{label:'生日', name:'birthday', index:'birthday', width:120, align:'center'},
			{label:'qq', name:'qq', index:'qq', width:150, align:'center'},
			{label:'微信', name:'weixin', index:'weixin', width:150, align:'center'},
			{label:'邮箱', name:'mail', index:'mail', width:250, align:'center'},
			{label:'个人简介', name:'introduce', index:'introduce', width:300, align:'center',classes: 'ellipsis'},
			{label:'操作', width:120,formatter: btnFormat}
		];
	grid = $("#r_tb").jqGrid({
		colModel: columns,
		width:w,
		height:h,
		datatype: 'json',
		mtype: "GET",
		url: '/Taoism/list_getUserGridModel.action',//'admin/pages/test.json',
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
	return '&nbsp;<input type="button" value="通过" class="operate">&nbsp;<input type="button" value="忽略" class="ignore">';
}

function initEvent(){
	$("#r_tb").on("click", ".operate", function(){
		var id = $(this).parents('tr').attr('id'),
			data = grid.getRowData(id);
		console.log(data);		
	});
}
 