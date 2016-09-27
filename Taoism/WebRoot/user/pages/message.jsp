<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>最新招生传法信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/message.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
	<script>
		$(function(){
			if(${sessionScope.UsersfromActions.userType!="弟子" }){
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
   		<div class="release-news"><a href="${pageContext.request.contextPath }/user/pages/edit_message.jsp" target="_blank">发布消息</a></div>
   		<div class="me-box">
   			<c:forEach items="${messages}" var="messages">
   			<div class="me-main">
   				<div class="me-main-a"><a href="${pageContext.request.contextPath }/user/pages/message_content.jsp?message_title=${messages.con1 }
   				&author=${messages.author}&publish_time=${messages.publishTime}&message_content=${messages.messageContent}" 
   				class="me-title" target="_blank" title="${messages.con1 }">${messages.con1 }</a></div>
   				<div class="releaser">&nbsp;&nbsp;&nbsp;&nbsp;发布者：<span>${messages.author }</span></div>
   				<c:set var="pTime" value="${messages.publishTime }"></c:set>
   				<div class="me-time">${fn:substring(pTime,0,10)}</div><!-- 截取时间字符串 -->
   			</div>
   			</c:forEach>
   		</div>
		<div class="page-div">
			<input type="button" value="上一页" id="pre">
			<a href="javascript:;" onclick="aSelected(this)">1</a>
			<a href="javascript:;" onclick="aSelected(this)">2</a>
			<span>第&nbsp;<span id="now">1</span>/<span id="total">2</span>&nbsp;页</span>
			<input type="button" value="下一页" id="next">
		</div>
   	</div>
  </body>
</html>
