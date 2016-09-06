<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>最新招生传法信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../css/message.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script>
		$(function(){
			if(${sessionScope.UsersfromActions[0].userType!="弟子" }){
				$(".release-news a").addClass("unuse");
				$(".release-news a").attr("disabled",true);
				$(".release-news a").attr("href","#");
			}
			
		});
	</script>
  </head>
  <body>
   	<div class="bd"></div>
   	<div class="main">
   		<div class="title">最新资讯消息</div>
   		<div class="release-news"><a href="edit_message.jsp" >发布消息</a></div>
   		<div class="me-box">
   			<div class="me-main">
   				<a href="#" class="me-title" title="">被抛弃了</a>
   				<div class="releaser">发布者：<span>微微一笑很倾城</span></div>
   				<div class="me-time">2016-07-21</div>
   			</div>
   			<div class="me-main">
   				<a href="#" class="me-title">微微抢亲</a>
   				<div class="releaser">发布者：<span>微微一笑很倾城</span></div>
   				<div class="me-time">2016-08-24</div>
   			</div>
   		</div>
		<div class="page-div">
			<input type="button" value="上一页" id="pre">
			<a href="javascript:;">1</a>
			<a href="javascript:;">2</a>
			<span>第&nbsp;<span id="now">1</span>/<span id="total">2</span>&nbsp;页</span>
			<input type="button" value="下一页" id="next">
		</div>
   	</div>
  </body>
</html>
