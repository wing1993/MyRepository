<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'myPost.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/personal_center.css?t=<%=System.currentTimeMillis()%>">
	
  </head>
  
  <body>
  <c:out value="${qList }"></c:out>
  <c:forEach items="${qList }" var="question">
  	 <%-- <div class="post-wrap">
		<div class="rep-num" title="回复数">${question.con3 }</div>
		<div class="post-content">
			<div class="post-title">
				<a href="${pageContext.request.contextPath }/question_find_findReplyByQId.action?QId=&sharezone=" title="">${question.title }</a>
			</div>
			<div class="post-rep">
				<div class="first-rep">${question.QContent }</div>
				<div class="post-right">
					<span class="icon-message">&#xe929; </span><span class="last-replyer" title="最后回复人">${question.con1 }</span>
					<span class="last-rep-time">${question.con2 }</span>
				</div>
			</div>
		</div>
	</div> --%>
 </c:forEach>
    <div class="post-wrap">
		<div class="rep-num" title="回复数">5000</div>
		<div class="post-content">
			<div class="post-title">
				<a href="${pageContext.request.contextPath }/question_find_findReplyByQId.action?QId=&sharezone=" title="">三生三世十里桃花</a>
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
  </body>
</html>
