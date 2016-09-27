<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人中心</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/personal_center.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
	<script>
		$(function(){
			$(".nav-wrap ul li").click(function(){
				$(this).addClass("select").siblings().removeClass("select");
				if($(this).text()=="消息管理"){
					$(".me").show();
				}else if($(this).text()=="向老先生求助"){
					$(".me").hide();
				}
				
			});
			$(".me").hide();
		});
		//删除消息
		function delete_message(obj){
			if(confirm("确定要删除该消息吗？")){
				$.post("/Taoism/message_delete.action",{messageId:$(this).next().val()},function(data){
					if(data=="success"){
						alert("删除成功！");
						$(this).parent().remove();
					}else{
						alert("删除失败！请稍候再试");
					}
				});
			}
		}
	</script>
  </head>
  
  <body>
    <div class="bd"></div>
    <div class="main-box">
    	<div class="top-bd"></div>
    	<div class="userinfo-wrap">
    		<div class="userinfo-left">
    			<div class="img-wrap"><img alt="" src="<c:url value='/images/register_bmg.jpg'/>"></div>
    		</div>
    		<div class="userinfo-middle">
    			<div class="middle-top">
    				<div class="edit-wrap">
    					<a href="${pageContext.request.contextPath }/user/pages/edit.jsp" target="_blank"><span class="icon-e">&#xe90e;</span>编辑资料</a>
    				</div>
    			</div>
    			<div class="middle-m">
    				<div class="username">${sessionScope.UsersfromActions[0].username }</div>
    			</div>
    		</div>
    	</div>
    	<div class="nav-wrap">
    		<ul>
    			<li>消息管理</li>
    			<li>向老先生求助</li>
    		</ul>
    	</div>
    	<div class="content">
    		<div class="me">
	    		<c:forEach items="${messages }" var="messages">
	    		<div class="me-main">
	   				<div class="me-main-a"><a href="${pageContext.request.contextPath }/user/pages/message_content.jsp?message_title=${messages.con1 }
	   				&author=${messages.author}&publish_time=${messages.publishTime}&message_content=${messages.messageContent}&messageId=${messages.messageId}" 
	   				class="me-title" target="_blank" title="${messages.con1 }">${messages.con1 }</a></div>
	   				<c:set var="pTime" value="${messages.publishTime }"></c:set>
	   				<div class="me-time">${fn:substring(pTime,0,10)}</div><!-- 截取时间字符串 -->
	   				<div class="operate">
	   				<a href="${pageContext.request.contextPath }/user/pages/update_message.jsp?message_title=${messages.con1 }
	   				&author=${messages.author}&publish_time=${messages.publishTime}&message_content=${messages.messageContent}&messageId=${messages.messageId}"
	   				 target="_blank">修改</a>
					 <a href="javascript:;" onclick="delete_message(this)">删除</a>
					 <input type="hidden" value="${messgaes.messageId }">
	   				</div>
	   			</div>
	    		</c:forEach>
	    		<a href="${pageContext.request.contextPath }/user/pages/edit_message.jsp" target="_blank" class="btn-gradient">发布消息</a>
    		</div>
    		<c:if test="${messages }==null">
    			<div class="no-data">暂无数据</div>
    		</c:if>
    	</div>
    </div>
  </body>
</html>
