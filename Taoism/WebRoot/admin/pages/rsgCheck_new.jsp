<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>注册审核</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css?t=<%= System.currentTimeMillis() %>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/ui.jqgrid.css">
	<link href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet">
	<style>
		html{font-family:'Microsoft Yahei';font-size:15.1px;}
		.search{margin-bottom:10px;}
		.search select{width:120px;}
		.operate, .ignore{border-radius:2px;border:none;color:#6b99e4;padding:4px;cursor:pointer;background:transparent;}
		.operate:hover, .ignore:hover{text-decoration:underline;color:red;}
		.btn{margin-bottom:10px;}
	</style>
</head>
<body>
	<div class="main">
		<div class="search">
			注册类型：
			<select name="userType" id="user_type">
				<option value="所有类型">所有类型</option>
				<option value="普通">普通</option>
				<option value="学员">学员</option>
				<option value="弟子">弟子</option>
			</select>
		</div>
		<input type="button" value="批量审核" class="btn">
		<div class="tb-wrap">
			<table class="r-tb" id="r_tb">
			</table>
			<div id="pager"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/rsgCheck.js?t=<%= System.currentTimeMillis()%>"></script>
</html>