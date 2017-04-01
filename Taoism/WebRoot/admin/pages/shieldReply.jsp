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
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/shieldReply.css?t=<%=System.currentTimeMillis()%>">
</head>
<body>
	<div class="main">
		<div class="top">
			<span>帖子：</span><div class="title">${requestScope.question.QTitle }</div>
			<a href="javascript:void(0);" class="close">关闭</a>
		</div>
		<div class="reply-content">
		<c:forEach items="${replysfromAction }" var="reply">
			<div class="content-box <c:if test="${reply.con1 == 1 }">shield-color</c:if>">
    			<input type="hidden" value="${reply.replyId }" class="reply-id">   			
    			<div class="w-r-main">
    				<a href="#" class="re-name">${reply.respondent }</a>:
						<span class="main-content">${reply.replyContent }</span>							
    			</div>
    			<div class="w-reply">
    				<div class="r-top">
    					<c:if test="${reply.con1 ==null || reply.con1 == 0 }"><a href="javascript:;" class="shield-com">屏蔽评论</a>&nbsp;</c:if>
    					<c:if test="${reply.con1 == 1 }"><a href="javascript:;" class="un-shield-com">取消屏蔽</a>&nbsp;</c:if>
    					<span class="r-time">${reply.replyTime }</span>&nbsp;
    					<div class="r-fold">
    						<c:if test="${requestScope.question.sharezone == '弟子区'&&null==requestScope.question.askWho }"><c:set var="times" value="${reply.discipleReplies }"></c:set></c:if>
	    					<c:if test="${requestScope.question.sharezone == '公开区'&&null==requestScope.question.askWho }"><c:set var="times" value="${reply.publicReplies }"></c:set></c:if>
	    					<c:if test="${requestScope.question.sharezone == '学员区'&&null==requestScope.question.askWho }"><c:set var="times" value="${reply.studentReplies }"></c:set></c:if>
	    					<c:if test="${null!=requestScope.question.askWho }"><c:set var="times" value="${reply.myquestionReplies }"></c:set></c:if>
    					
    						<span class="r">回复</span>(<span class="t">${fn:length(times)}</span>)
						</div>
    				</div>
    				<div class="sub-reply">
    				<c:if test="${requestScope.question.sharezone == '弟子区'&&null==requestScope.question.askWho }">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   						<c:forEach items="${reply.discipleReplies }" var="dReply">
   						<div class="ans-content <c:if test="${dReply.con1 == 1}">shield-color</c:if>">
   							<input type="hidden" value="${dReply.replyId}" class="sub-replyid">
   							<a href="#" class="re-name">${dReply.respondent }</a>:
   							<span class="main-content">${dReply.replyContent }</span>
   							<div class="ans-co-bottom">
   								<c:if test="${dReply.con1 == null || dReply.con1 == 0}">
   								<a href="javascript:void(0);" class="shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">屏蔽</a></c:if>
   								<c:if test="${dReply.con1 == 1}">
   								<a href="javascript:void(0);" class="un-shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">取消屏蔽</a></c:if>
   								<span class="ans-time">${dReply.replyTime }</span>
   							</div>
   						</div>
   						</c:forEach>
   					</c:if>
   					
   					<c:if test="${requestScope.question.sharezone == '公开区'&&null==requestScope.question.askWho }">
   						<c:forEach items="${reply.publicReplies }" var="pReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content <c:if test="${pReply.con1 == 1}">shield-color</c:if>">
   								<input type="hidden" value="${pReply.replyId}" class="sub-replyid">
	   							<a href="#" class="re-name">${pReply.respondent }</a>:
	   							<span class="main-content">${pReply.replyContent }</span>
	   							<div class="ans-co-bottom">
   								<c:if test="${pReply.con1 == null || pReply.con1 == 0}">
   								<a href="javascript:void(0);" class="shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">屏蔽</a></c:if>
   								<c:if test="${pReply.con1 == 1}">
   								<a href="javascript:void(0);" class="un-shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">取消屏蔽</a></c:if>
	   								<span class="ans-time">${fn:substring(pReply.replyTime,0,19) }</span>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
   					<c:if test="${requestScope.question.sharezone == '学员区'&&null==requestScope.question.askWho }">
   						<c:forEach items="${reply.studentReplies }" var="sReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content <c:if test="${sReply.con1 == 1}">shield-color</c:if>">
   								<input type="hidden" value="${sReply.replyId}" class="sub-replyid">
	   							<a href="#" class="re-name">${sReply.respondent }</a>:
	   							<span class="main-content">${sReply.replyContent }</span>
	   							<div class="ans-co-bottom">
   								<c:if test="${sReply.con1 == null || sReply.con1 == 0}">
   								<a href="javascript:void(0);" class="shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">屏蔽</a></c:if>
   								<c:if test="${sReply.con1 == 1}">
   								<a href="javascript:void(0);" class="un-shield-sub <c:if test="${reply.con1 == 1 }">shide</c:if>">取消屏蔽</a></c:if>
	   								<span class="ans-time">${fn:substring(sReply.replyTime,0,19) }</span>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
    				</div>
    			</div>
    		</div>
    	</c:forEach>
    		
		</div>
	</div>
</body>

<script src="<%=path %>/js/jquery.min.js"></script>
<script>
	$(function(){
		var h = parent.dialog._height - 25;
		$(".main").height(h);
		$(".reply-content").height($(".main").height() - $(".top").height());
		$(".close").click(function(){
			parent.dialog.close();
		});
		
		$(".reply-content").on("click", ".r-fold", function(){
			fold(this);
		});
		
		$("body").on("click", ".shield-com", function(){//屏蔽一级评论
			Shield(this, "1");
		});
		
		$("body").on("click", ".un-shield-com", function(){//取消屏蔽
			Shield(this, "0");
		});
		
		$("body").on("click", ".shield-sub", function(){//屏蔽二级评论
			subShield(this, "1");
		});
		
		$("body").on("click", ".un-shield-sub", function(){//取消二级评论屏蔽
			subShield(this, "0");
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
	
	function Shield(obj, str){//屏蔽一级回复
		var id = $(obj).parents(".content-box").find(".reply-id").val(),
			sharezone = '${requestScope.question.sharezone}';
		$.post('<%=path%>/reply_shieldReply.action', {"replyId":id, "sharezone":sharezone, "con1": str}, function(data){
			if(data == "success"){
				var _ua = '<a href="javascript:;" class="un-shield-com">取消屏蔽</a>',
					_a = '<a href="javascript:;" class="shield-com">屏蔽评论</a>';
				if(str == "1"){
					$(obj).after(_ua);
					$(obj).parents(".content-box").addClass("shield-color");
					$(obj).parents(".content-box").find(".sub-reply .ans-co-bottom a").hide();
				}else{
					$(obj).after(_a);
					$(obj).parents(".content-box").removeClass("shield-color");
					$(obj).parents(".content-box").find(".sub-reply .ans-co-bottom a").show();
				}
				$(obj).hide();
				
			}else{
				alert("屏蔽失败");
			}
		});
	}
	
	function subShield(obj, str){//屏蔽二级回复
		var sub_id = $(obj).parents(".ans-content").find(".sub-replyid").val(),
			sharezone = '${requestScope.question.sharezone}';
		$.post('<%=path%>/reply_shieldReply.action', {"replyId":sub_id, "sharezone":sharezone, "con1": str}, function(data){
			if(data == "success"){
				var _ua = '<a href="javascript:;" class="un-shield-sub">取消屏蔽</a>',
					_a = '<a href="javascript:;" class="shield-sub">屏蔽</a>';
				if(str == "1"){
					$(obj).after(_ua);
					$(obj).parents(".ans-content").addClass("shield-color");
// 					$(obj).parents(".ans-content").find(".sub-reply .ans-co-bottom a").hide();
				}else{
					$(obj).after(_a);
					$(obj).parents(".ans-content").removeClass("shield-color");
// 					$(obj).parents(".ans-content").find(".sub-reply .ans-co-bottom a").show();
				}
				$(obj).hide();
				
			}else{
				alert("屏蔽失败");
			}
		});
	}
</script>
</html>