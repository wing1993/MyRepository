<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户信息</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/common.css?<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/ui.jqgrid.css">
	<link href="<%=path %>/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet">
	<style>
		html{font-family:'Microsoft Yahei';font-size:15.1px;}
		.search{margin-bottom:10px;}
		.search select{width:120px;display:inline-block;height:30px;}
		.search-wrap{position:relative;display:inline-block;border-radius:4px;border:1px solid #a9a9a9;}
		.search-wrap input{border:none;outline:none;height:30px;width:80%;margin-left:5px;}
		.search-wrap .search-btn{position:absolute;right:4px;width:25px;height:25px;background-position:0 3px;;}
		.join-black, .slience{border-radius:2px;border:none;color:#6b99e4;padding:4px;cursor:pointer;background:transparent;}
		.join-black:hover, .slience:hover{text-decoration:underline;color:red;}
		
	</style>
</head>
<body>
	<div class="main">
		<div class="search">
			<div class="search-wrap">
				<input type="text" placeholder="请输入关键字搜索" id="search_val">
				<span class="search-btn" title="搜索"></span>
			</div>
			&nbsp;&nbsp;注册类型：
			<select name="userType" id="user_type">
				<option value="所有类型">所有类型</option>
				<option value="普通">普通</option>
				<option value="学员">学员</option>
				<option value="弟子">弟子</option>
			</select>
			<div class="stop-tip"><span class="tip"></span></div>
		</div>
		<div class="tb-wrap">
			<table class="r-tb" id="r_tb">
			</table>
			<div id="pager"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.jqGrid.js?t=<%= System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=path %>/admin/js/userInfo.js?t=<%= System.currentTimeMillis()%>"></script>
<script>
var url = '<%=path%>';
</script>
</html>