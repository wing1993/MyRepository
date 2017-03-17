<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String path = request.getContextPath(); %>   
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>帖子管理</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/liger-ui.css?t=<%= System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/common.css?t=<%= System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/postsManage.css?t=<%= System.currentTimeMillis()%>">
</head>
<body style="margin:8px">
	<div class="main">
		<div class="top">
		<form action="${pageContext.request.contextPath }/listquestion_findByQTime.action?currentPage=1&rows=10" method="post">
			<div class="time">
				<span>发帖日期：</span><input type="text" id="from_date" name="startTime"> &macr; <input type="text" id="to_date" name="endTime"> 
			</div>
			<input type="text" placeholder="发帖人" id="poster" name="username">
			<input type="button" class="query" value="查询" id="query" onclick='queryQuestion()'>
		</form>
		</div>
		<c:out value="${qList}"></c:out>
		<c:out value="${cList}"></c:out>
		<c:out value="${page}"></c:out>
		<div class="post">
			<div class="post-wrap">
   				<div class="rep-num" title="回复数">5000</div>
   				<div class="post-content">
   					<div class="post-title">
   						<span class="post-top" title="置顶">置顶</span>  <span class="post-best" title="精华帖">精</span>
   						<a href="javascript:void(0);" title="" class="title" id="">三生三世十里桃花</a>&nbsp;
   						<span class="post-time">发帖时间：2017-09-5 15:20</span>
   						<div class="post-operate">
   							<input type="button" class="reply-ope unset-top" value="取消置顶">&nbsp;
   							<input type="button" class="reply-ope unset-best" value="取消精华帖">&nbsp;
   							<input type="button" class="reply-ope delete" value="删除">&nbsp;
   						</div>
   					</div>
   					<div class="post-rep">
    					<div class="first-rep">那年的七月底，天君令我下界降服从大荒中长起来的一头赤炎金猊，
    					我与那赤炎金猊兽在中容国国境大战七日，天地失色之际，虽将这凶兽斩于剑下，却也因力竭被逼出了原身。
    					我的原身本是威风凛凛的一条黑龙，但觉得招摇，便缩得只同条小蛇一般大小，
    					在旁边的俊疾山上找了个不大起眼的山洞，便一闭眼睡了。</div>
    					<div class="post-right">
    						<span class="icon-message">&#xe929; </span><span class="last-replyer" title="最后回复人">bujifeiyu</span>
    						<span class="last-rep-time">16:50</span>
    					</div>
   					</div>
   				</div>
   			</div>
   			
   			<div class="post-wrap">
   				<div class="rep-num" title="回复数">0</div>
   				<div class="post-content">
   					<div class="post-title">
   						<a href="javascript:void(0);" title="" class="title" id="">百年孤独</a>&nbsp;
   						<span class="post-time">发帖时间：2017-09-5 15:20</span>
   						<div class="post-operate">
   							<input type="button" class="reply-ope set-top" value="置顶">&nbsp;
   							<input type="button" class="reply-ope set-best" value="设置为精华帖">&nbsp;
   							<input type="button" class="reply-ope delete" value="删除">&nbsp;
   						</div>
   					</div>
   					<div class="post-rep">
    					<div class="first-rep">过去都是假的，回忆是一条没有归途的路，以往的一切春天都无法复原，
    					即使最狂热最坚贞的爱情，归根结底也不过是一种瞬息即逝的现实，唯有孤独永恒。</div>
    					<div class="post-right">
    						<span class="icon-message">&#xe929; </span><span class="last-replyer" title="最后回复人">马尔克斯</span>
    						<span class="last-rep-time">16:50</span>
    					</div>
   					</div>
   				</div>
   			</div>
		</div>
		
		<div class="page-div">
			<a href="#" class="abtn">上一页</a>
			<a href="#" data-pagenum="">1</a>
			<span>第&nbsp;<span id="now">1</span>/<span id="total">4</span>&nbsp;页</span>
			
			<a href="#" class="abtn">下一页</a>
		</div>
	</div>
</body>
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/ligerui.js?t=<%= System.currentTimeMillis()%>"></script>
	<script src="<%=path %>/admin/js/postsManage.js?t=<%= System.currentTimeMillis()%>"></script>
</html>