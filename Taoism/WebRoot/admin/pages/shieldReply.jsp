<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
   <% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>屏蔽回复</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=path%>/user/css/q_detail.css"> --%>
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/shieldReply.css?t=<%=System.currentTimeMillis()%>">
</head>
<body>
	<div class="main">
		<div class="top">
			<span>帖子：</span><div class="title">${requestScope.question.QTitle }</div>
			<a href="javascript:void(0);" class="close">关闭</a>
		</div>
		<div class="reply-content">
<!-- 		<c:out value="${replysfromAction }"></c:out> -->
		<c:forEach items="${replysfromAction }" var="reply">
			<div class="content-box">   			
    			<div class="w-r-main">
    				<a href="#" class="re-name">${reply.respondent }</a>:
						<span class="main-content">${reply.replyContent }</span>							
    			</div>
    			<div class="w-reply">
    				<div class="r-top">
    					<a href="javascript:;" class="shield-com">屏蔽评论</a>&nbsp;
    					<span class="r-time">${reply.replyTime }</span>&nbsp;
    					<div class="r-fold">
    						<c:set var="times" value="${reply.discipleReplies }"></c:set>
    						<span class="r">回复</span>(<span class="t">${fn:length(times)}</span>)
						</div>
    				</div>
    				<div class="sub-reply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   						<c:forEach items="${reply.discipleReplies }" var="dReply">
   						<div class="ans-content">
   							<input type="hidden" value="${dReply.replyId}" id="${dReply.replyId}">
   							<a href="#" class="re-name">${dReply.respondent }</a>:
   							<span class="main-content">${dReply.replyContent }</span>
   							<div class="ans-co-bottom">
   								<a href="javascript:void(0);" class="shield-sub">屏蔽</a>
   								<span class="ans-time">${dReply.replyTime }</span>
   							</div>
   						</div>
   						</c:forEach>
    				</div>
    			</div>
    		</div>
    	</c:forEach>
    	
<!--     		<div class="content-box">   			 -->
<!--     			<div class="w-r-main"> -->
<!--     				<a href="#" class="re-name">一笑奈何</a>: -->
<!-- 						<span class="main-content">微微忽然就觉得，自己纠结了半天的问题一点都不重要了。 就算哪天服务器真的关闭了也没关系。  -->
<!--    							只要她记得他在何时何地跟她说了第一句话。 记得他们去哪里看了风景。 记得他们共乘白雕掠过了山山水水……  -->
<!--    							那些回忆并不会因为数据的消失而消失。 所以，就算将来这个游戏关闭了，这个世界上也永远会有一处地方——也许我心, -->
<!--    							也许彼心，白衣红影并肩而立。 看落霞峰上，永不落霞。陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span>							 -->
<!--     			</div> -->
<!--     			<div class="w-reply"> -->
<!--     				<div class="r-top"> -->
<!--     					<a href="javascript:;" class="shield-com">屏蔽评论</a>&nbsp; -->
<!--     					<span class="r-time">2016-8-18 22:15</span>&nbsp; -->
<!--     					<div class="r-fold"> -->
<!--     						<span class="r">回复</span>(<span class="t"></span>) -->
<!-- 						</div> -->
<!--     				</div> -->
<!--     				<div class="sub-reply"> -->
   						<!-- <div class="answerer-img"><img src=""/></div> -->
<!--    						<div class="ans-content"> -->
<!--    							<a href="#" class="re-name">芦苇微微</a>: -->
<!--    							<span class="main-content">陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span> -->
<!--    							<div class="ans-co-bottom"> -->
<!--    								<a href="javascript:void(0);" class="shield-sub">屏蔽</a> -->
<!--    								<span class="ans-time">2016-8-18 22:15</span> -->
<!--    							</div> -->
<!--    						</div> -->
<!--     				</div> -->
<!--     			</div> -->
<!--     		</div> -->
    		
<!--     		<div class="content-box">   			 -->
<!--     			<div class="w-r-main"> -->
<!--     				<a href="#" class="re-name">一笑奈何</a>: -->
<!-- 						<span class="main-content">微微忽然就觉得，自己纠结了半天的问题一点都不重要了。 就算哪天服务器真的关闭了也没关系。  -->
<!--    							只要她记得他在何时何地跟她说了第一句话。 记得他们去哪里看了风景。 记得他们共乘白雕掠过了山山水水……  -->
<!--    							那些回忆并不会因为数据的消失而消失。 所以，就算将来这个游戏关闭了，这个世界上也永远会有一处地方——也许我心, -->
<!--    							也许彼心，白衣红影并肩而立。 看落霞峰上，永不落霞。陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span>							 -->
<!--     			</div> -->
<!--     			<div class="w-reply"> -->
<!--     				<div class="r-top"> -->
<!--     					<a href="javascript:;" class="shield-com">屏蔽评论</a>&nbsp; -->
<!--     					<span class="r-time">2016-8-18 22:15</span>&nbsp; -->
<!--     					<div class="r-fold"> -->
<!--     						<span class="r">回复</span>(<span class="t"></span>) -->
<!-- 						</div> -->
<!--     				</div> -->
<!--     				<div class="sub-reply"> -->
   						<!-- <div class="answerer-img"><img src=""/></div> -->
<!--    						<div class="ans-content"> -->
<!--    							<a href="#" class="re-name">芦苇微微</a>: -->
<!--    							<span class="main-content">陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span> -->
<!--    							<div class="ans-co-bottom"> -->
<!--    								<a href="javascript:void(0);" class="shield-sub">屏蔽</a> -->
<!--    								<span class="ans-time">2016-8-18 22:15</span> -->
<!--    							</div> -->
<!--    						</div> -->
<!--     				</div> -->
<!--     			</div> -->
<!--     		</div> -->
    		
		</div>
	</div>

</body>
<script src="<%=path %>/js/jquery.min.js"></script>
<script>
	$(function(){
	console.log(parent.dialog);
		var h = parent.dialog._height - 25;
		$(".main").height(h);
		$(".reply-content").height($(".main").height() - $(".top").height());
		$(".close").click(function(){
			parent.dialog.close();
		});
		
		$(".reply-content").on("click", ".r-fold", function(){
			fold(this);
		});
	});
	
	function fold(obj){
		if($(obj).find(".r").text()=="收起回复"){
			$(obj).find(".r").text("回复");
			$(obj).parent().next().css("display","none");
		}else{
			$(obj).find(".r").text("收起回复");
			$(obj).parent().next().css("display","block");
		}
	}
</script>
</html>