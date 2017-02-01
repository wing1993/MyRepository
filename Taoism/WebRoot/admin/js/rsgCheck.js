/**
 * 注册审核
 */
 $(function(){
	$(".main").height(parent.iframe_h - 16);
	createGrid();
});

function createGrid(){
	var	w = $(".main").width();
	var h = $(".main").height() - $(".search").height() - 90;
	var columns = [
			{label:'用户名', name:'username', index:'username', width:80, align:'center'},
			{label:'注册类型', name:'userType', index: 'userType', width:60, align:'center'},
			{label:'性别', name:'gender', index:'gender', width:40, align:'center'},
			{label:'居住城市', name:'city', index:'city',width:80, align:'center'},
			{label:'手机', name:'phone', index:'phone', width:80, align:'center'},
			{label:'真实姓名', name:'realname', index:'realname', width:100, align:'center'},
			{label:'生日', name:'birthday', index:'birthday', width:100, align:'center'},
			{label:'qq', name:'qq', index:'qq', width:120, align:'center'},
			{label:'微信', name:'weixin', index:'weixin', width:120, align:'center'},
			{label:'邮箱', name:'mail', index:'mail', width:120, align:'center'},
			{label:'个人简介', name:'introduce', index:'introduce', width:500, align:'center'}
		];
	$("#r_tb").jqGrid({
		colModel: columns,
		width:w,
		height:200,
		datatype: 'json',
		url: '/Taoism/user_findUnexamined.action',//'admin/pages/test.json',
		rowNum : 6,//一页显示多少条
		rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
		pager : '#pager',//表格页脚的占位符(一般是div)的id
		sortname : 'id',//初始化的时候排序的字段
		sortorder : "desc",//排序方式,可选desc,asc
		mtype : "post",//向后台请求数据的ajax的类型。可选post,get
		rownumbers: true
	});
}
 