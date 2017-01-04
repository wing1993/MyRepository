<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员页面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/admin/css/admin.css?t=<%= System.currentTimeMillis()%>">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/liger-ui.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/admin.js?t=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ligerui.js"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="main">
		<div class="top">
			<div class="wel">
				<span class="ico"></span>
				<span>&nbsp;管理员，欢迎您登陆</span>
			</div>
			<div class="title">
				<span class="logo"></span><span>老先生答疑后台管理</span>
				<span class="admin_ico" title="个人中心"></span>
				<a class="logout" title="退出" href="#"></a>
			</div>
		</div>
		<div class="left">
			<div class="left-item"><span class="icon-user">&#xe915;</span>&nbsp;用户管理</div>
			<ul class="sub-item">
				<li id="rsgCheck" tabid="rsgCheck" tab_url="/Taoism/user_findUnexamined.action">
					<span class="icon-sub">&#xe91a;</span>&nbsp;&nbsp;<span>注册审核</span>
				</li>
				<li id="updateClass" tabid="updateClass"><span class="icon-sub">&#xe80c;</span>&nbsp;&nbsp;<span>身份升级</span></li>
				<li id="userInfo" tabid="userInfo"><span class="icon-sub">&#xe971;</span>&nbsp;&nbsp;<span>用户信息</span></li>
			</ul>
			<div class="left-item"><span class="icon-qz">&#xe920;</span>&nbsp;问题管理</div>
			<ul class="sub-item">
				<li tabid="typeManage">
					<span class="icon-sub">&#xe924;</span>&nbsp;<span id="ma_q_type">类型管理</span>
				</li>
				<li tabid="replyManage">
					<span class="icon-sub" style="font-weight:bold;font-size:17px !important;">&#xe909;</span>
					<span id="m_message">留言管理</span>
				</li>
			</ul>
			<div class="left-item"><span class="icon-qz">&#xe991;</span>&nbsp;数据维护</div>
			<ul class="sub-item">
				<li tabid="backups"><span class="icon-sub">&#xe92c;</span>&nbsp;<span id="">数据备份</span></li>
				<li tabid="recovery">
					<span class="icon-sub">&#xea2e;</span>
					<span id="">数据恢复</span>
				</li>
			</ul>
		</div>
		<!-- <div class="right">
			<div class="r-top">
				<span class="icon-r">&nbsp;&nbsp;&#xe911;&nbsp;当前位置&#xe903;</span>
				<span class="loc"></span>
			</div>
			<div class="r-bottom">
				<iframe frameborder="0" id="r_iframe" src=""></iframe>
			</div>
		</div> -->
		<div id="main-bd"  class="right">
			<div class="page-tab" id="page_tab">
				
			</div>
		</div>
	
	</div>
</body>
</html>