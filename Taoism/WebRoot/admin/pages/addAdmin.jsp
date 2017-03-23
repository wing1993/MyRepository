<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addAdmin</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/liger-ui.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/ui.jqgrid.css">
	<link href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet">
	<style type="text/css">
	label{font-size:18px;}
	input[type=checkbox]{zoom:140%;}
	.newAdmin{position:relative;margin-bottom:15px;}
	.existed{width:300px;}
	.existed li{padding:5px;background:#ddd;margin-top:6px;}
	.add-admin{background-color:#28A6FF;border:none;color:#fff;cursor:pointer;padding:6px 4px;border-radius:4px;
		position:absolute;top:0;margin-left:15px;
	}
	.add-admin:hover{box-shadow:0px 0px 3px #2999e8;}
	.add-input{height:27px;}
	.newAdmin{height:20px;}
	.l-text-wrapper{display:inline-block;}
	.btn-format span{display:inline-block;width:45%;font-size:20px;}
	.btn-format span:nth-child(1){text-align:left;}
	.btn-format span:nth-child(2){text-align:right;margin-right:20px;}
	.ui-jqgrid tr.jqgrow td{height:30px;}
	.l-dialog-winbtn{height:26px;}
	.l-dialog-winbtn:before{content:'×';font-weight:bold;font-size:20px;margin-bottom:5px;}
	</style>
  </head>
  <body>
<!--     <span style="margin-left:40px;">已存在的系统管理员:</span> -->
<!-- 	<ul class="existed"> -->
<!-- 		<li><span id="1">root1</span><span class="icon-d" title="删除">&#xe15c;</span></li> -->
<!-- 		<li><span id="2">root2</span><span class="icon-d" title="删除">&#xe15c;</span></li> -->
<!-- 		<li><span id="3">root3</span><span class="icon-d" title="删除">&#xe15c;</span></li> -->
<!-- 	</ul> -->
<!-- 	<div class="newAdmin"> -->
<!-- 		<input type="text" placeholder="请输入弟子的法号" class="add-input">  -->
<!-- 		<input type="button" class="add-admin" value="新增管理员"> -->
<!-- 	</div> -->
		<div class="newAdmin">
			<input type="button" class="add-admin" value="新增管理员">
		</div>
		<div class="tb-wrap">
			<table class="r-tb" id="r_tb">
			</table>
		</div>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.jqGrid.js?t=<%=System.currentTimeMillis()%>"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/ligerui.js?t=<%=System.currentTimeMillis()%>"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/addAdmin.js?t=<%=System.currentTimeMillis()%>"></script>
</html>
