<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<title></title>	
	<c:if test="${admin.updateClass == 1 }">
	<link rel="stylesheet" type="text/css" href="<%=path%>/user/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/liger-ui.css?<%= System.currentTimeMillis()%>">
	<link href="<%=path%>/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet">
	<style>
		.update, .ignore{border-radius:2px;border:none;color:#6b99e4;padding:4px;cursor:pointer;background:transparent;}
		.update:hover, .ignore:hover{text-decoration:underline;color:red;}
		.old-role, .new-role{font-weight:bold;font-size:14px;}
		.new-role{color:red;}
		.l-dialog-content{padding-right:0 !important;}
	</style>
	</c:if>
</head>
<c:if test="${admin.updateClass == 0 }">
<body><h2 style="color:red;">您没有升级用户的权限</h2></body>
</c:if>
<c:if test="${admin.updateClass == 1 }">
<body style="margin:8px;">
	<div class="main">
		<table id="update_tb"></table>
		<div id="pager"></div>
	</div>
</body>
<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/jquery.jqGrid.js"></script>
<script src="<%=path%>/js/ligerui.js"></script>
<script src="<%=path%>/admin/js/updateClass.js?<%= System.currentTimeMillis()%>"></script>
</c:if>
</html>