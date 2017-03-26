<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addAdmin</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/common.css?t=<%=System.currentTimeMillis()%>">
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
	.btn-format{text-align:center;}
	.btn-format span{font-size:20px;}
	/* .btn-format span:nth-child(1){text-align:left;}
	.btn-format span:nth-child(2){text-align:right;margin-right:20px;} */
	.ui-jqgrid tr.jqgrow td{height:30px;}
	.l-dialog-winbtn{height:26px;}
	.l-dialog-winbtn:before{content:'×';font-weight:bold;font-size:20px;margin-bottom:5px;}
	.ui-jqgrid .ui-jqgrid-htable .ui-th-div{height:20px;}
	.stop-tip{margin-left:200px;border-width:2px;}
	.check{background-image:url(../../images/right.png);}
	.cross{background-image:url(../../images/cross.png);}
	.right{display:block;width:16px;height:16px;color:transparent;background-repeat:no-repeat;
		background-size:cover;}
	.readonly{background:#ccc;color:#fff;}
	.td-stop-color{margin:30px 15px;}
	</style>
  </head>
  <body>
		<input type="hidden" id="adminId" value="${admin.adminId }">
		<div class="newAdmin">
			<input type="button" class="add-admin <c:if test="${admin.addAdmin == 0 }">readonly</c:if>" value="新增管理员" id="add_admin">
			<div class="stop-tip"><span class="tip"></span></div>
			<c:if test="${admin.addAdmin == 0 }"><h2 class="td-stop-color">您没有添加管理员的权限</h2></c:if>
		</div>
		<div class="tb-wrap">
			<table class="r-tb" id="r_tb">
			</table>
		</div>
  </body>
  <c:if test="${admin.addAdmin == 0 }">
  	<script>
  		document.getElementById("add_admin").setAttribute('readonly', true);
  	</script>
  </c:if>
  <c:if test="${admin.addAdmin == 1 }">
	  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.jqGrid.js?t=<%=System.currentTimeMillis()%>"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/js/ligerui.js?t=<%=System.currentTimeMillis()%>"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/addAdmin.js?t=<%=System.currentTimeMillis()%>"></script>
	  <script>
	  	var url = '<%=path %>';
	  </script>
  </c:if>
</html>
