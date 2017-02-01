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
			
			if($(".release-news a").attr("disabled") != "disabled"){
				$(".release-news a").attr({
					"href": "${pageContext.request.contextPath }/user/pages/edit_message.jsp",
					"target": "_blank"
				});
			}
		});
	</script>
  </head>
  <body>
   	<div class="bd"></div>
   	<div class="main">
   		<div class="title">最新资讯消息</div>
   		<div class="release-news"><a href="javascript:void(0);">发布消息</a></div>
   		<div class="me-box">
   			<c:forEach items="${mList}" var="messages">
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
			<c:set var="pages" value="${page }"></c:set>
			<c:if test="${pages.hasPrePage }">
				<a href="${pageContext.request.contextPath }/message_findAll.action?currentPage=${pages.currentPage-1}" class="abtn">上一页</a>
			</c:if>
			<c:forEach items="${cList }" var="cList">
				<c:choose>
				<c:when test="${cList.page==0 }">
					<a href="${pageContext.request.contextPath }/message_findAll.action?currentPage=${pages.currentPage }" onclick="aSelected(this)">${pages.currentPage }</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/message_findAll.action?currentPage=${cList.page }" onclick="aSelected(this)" onclick="aSelected(this)">${cList.page }</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<span>第&nbsp;<span id="now">${page.currentPage }</span>/<span id="total">${page.totalPage }</span>&nbsp;页</span>
			<c:if test="${pages.hasNextPage }">
				<a href="${pageContext.request.contextPath }/message_findAll.action?currentPage=${pages.currentPage+1}" class="abtn">下一页</a>
			</c:if>
		</div>
   	</div>
  </body>
</html>
