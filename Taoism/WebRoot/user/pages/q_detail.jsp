<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>问题详情</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/q_detail.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/q_detail.js?t=<%=System.currentTimeMillis()%>"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath }/user/pages/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath }/user/pages/kindeditor/lang/zh_CN.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="card"]', {
				cssPath : '${pageContext.request.contextPath }/user/pages/kindeditor/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath }/user/pages/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '${pageContext.request.contextPath }/user/pages/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				},

				//自定义工具栏选项，详细见kindeditor.js
				items:['undo', 'redo', '|', 'cut', 'copy', 'paste','|', 'justifyleft', 
					'justifycenter', 'justifyright','justifyfull', 'selectall', '|', 
					'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image', 'multiimage',
					'media',  'emoticons', 'link', 'unlink', ]
			});			
		});
		function checkLogin_Reply(){
			if(${sessionScope.UsersfromActions==null}){
				alert("您还没有登录，不能回复！");
				return false;
			}
		}
		
		function subPost(obj){
			var t=null;
			if($(obj).parent().find(".add-re").val()==""){
				alert("请输入内容");
				return;
			}
			$.post("${pageContext.request.contextPath }/reply_saveReply.action",
					{sharezone:$(".sharezone").val(),replyId:$(obj).prev().val(),
					replyContent:$(obj).prev().prev().val()},function(data){
					if(null!=data){
						alert(data.replyTime+"成功");
						var str="<div class='ans-content'>"+
		   							"<a href='#' class='re-name'>${sessionScope.UsersfromActions.username }</a>:"+
		   							"<span class='main-content'>"+$(obj).prev().prev().val()+"</span>"+
		   							"<div class='ans-co-bottom'>"+
		   								"<span class='ans-time'>"+data.replyTime+"</span>&nbsp;"+
		   								"<a href='javascript:;' onclick='reply(this)'>回复</a></div></div>";
		   							
						$(obj).parent().parent().parent().find(".sub-add").before(str); 
						$(obj).parent().find(".add-re").val("");
						t=parseInt($(obj).parent().parent().prev().find(".t").text())+1;
						$(obj).parent().parent().prev().find(".t").text(t);
					}else{
						alert(data.replyTime+"评论失败");
					}
			}); 
		}
		
		function reply(obj){
			var $edit=$(obj).parent().parent().parent().find(".sub-edit");
			var name=$(obj).parent().parent().find(".re-name").text();
			$edit.find(".add-re").val(" 回复 "+name+"：");
			$edit.show(200);
			$edit.find(".add-re").focus();
		}
		
		$(function(){
			$(".sub-reply").css("display","none");
			//$(".r-top").find("div").next();
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
		
		function Post(){
			checkLogin_Reply();
			if(editor.html()==""){
				alert("请输入内容");
			}else{
				alert(editor.html());
				var str=null;
				$.post("${pageContext.request.contextPath }/reply_saveReply.action",
					{sharezone:$(".sharezone").val(),QId:$(".QId").val(),
					replyContent:editor.html()},function(data){
					if(null!=data){
						alert(data);
						str="<div class='w-content-box'><div class='w-right'><div class='w-r-main'>"+
							"<a href='#' class='re-name'>${sessionScope.UsersfromActions.username }</a>："+
							"<span class='main-content'>"+editor.html()+"</span></div><div class='w-reply'><div class='r-top'>"+
			    			"<span class='r-time'>"+data.replyTime+"</span>&nbsp;<div class='r-fold'>回复</div></div><div class='sub-reply'>"+
			   				"<div class='sub-add'><span class='comment'>我要评论</span></div><div class='sub-edit'>"+
			    			"<textarea class='add-re'></textarea><input type='button' value='发表' class='sub-post' onclick='subPost(this)'>"+
			    			"</div></div></div></div></div>";
					
						$(".edit-div").before(str);
						editor.html("");
					}else{
						alert(data.replyTime+"评论失败");
					}
				}); 
			}
		}
	</script>
  </head>
  <body>
    <div class="bd"></div>
    <div class="main">
    	<div class="main-box"></div>
    	<div class="main-box-fixed">
    		<div class="q_title">${requestScope.question.QTitle }</div>
    		<c:if test="${sessionScope.UsersfromActions.userType=='弟子' }"><a class="a-btn" id="forward">转发</a></c:if>
    		<a class="a-btn" id="btn" onclick="checkLogin_Reply()">回复</a>
    	</div>
    	<input type="hidden" value="${requestScope.question.sharezone }" class="sharezone">
    	<input type="hidden" value="${requestScope.question.QId }" class="QId"> 
    	<div class="w-content-box">
    		<div class="w-right">
    			<div class="w-r-main">
    				<a href="#" class="re-name">${requestScope.question.username }</a>（发帖作者）:
						<span class="main-content">${requestScope.question.QContent }</span>							
    			</div>
    		</div>
    	</div>
    	<%-- <c:out value="${replysfromAction }"></c:out> --%>
    	<c:forEach items="${replysfromAction }" var="reply">
    	<div class="w-content-box">
    		<!-- <div class="w-left">
    			<div class="ans-intro"><img src=""></div>
    			<div class="respondent">*****</div>
    		</div> -->
    		<div class="w-right">
    			<div class="w-r-main">
    				<a href="#" class="re-name">${reply.respondent }</a>:
						<span class="main-content">${reply.replyContent }</span>							
    			</div>
    			<div class="w-reply">
    				<div class="r-top">
    					<span class="r-time">${fn:substring(reply.replyTime,0,19) }</span>&nbsp;
    					<c:if test="${requestScope.question.sharezone == '弟子区' }"><c:set var="times" value="${reply.discipleReplies }"></c:set></c:if>
    					<c:if test="${requestScope.question.sharezone == '公开区' }"><c:set var="times" value="${reply.publicReplies }"></c:set></c:if>
    					<c:if test="${requestScope.question.sharezone == '学员区' }"><c:set var="times" value="${reply.studentReplies }"></c:set></c:if>
    					<c:if test="${requestScope.question.sharezone == '我的问题' }"><c:set var="times" value="${reply.myquestionReplies }"></c:set></c:if>
    					
    					<div class="r-fold" onclick="fold(this)"><span class="r">回复</span>(<span class="t">${fn:length(times)}</span>)</div>
    				</div>    				
    				<div class="sub-reply">
    				<c:if test="${requestScope.question.sharezone == '弟子区' }">
   						<c:forEach items="${reply.discipleReplies }" var="dReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content">
   								<input type="hidden" value="${dReply.replyId}" id="${dReply.replyId}">
	   							<a href="#" class="re-name">${dReply.respondent }</a>:
	   							<span class="main-content">${dReply.replyContent }</span>
	   							<div class="ans-co-bottom">
	   								<span class="ans-time">${fn:substring(dReply.replyTime,0,19) }</span>
	   								<a href="javascript:;" onclick="reply(this)">回复</a>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
   					<c:if test="${requestScope.question.sharezone == '公开区' }">
   						<c:forEach items="${reply.publicReplies }" var="pReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content">
   								<input type="hidden" value="${pReply.replyId}" id="${pReply.replyId}">
	   							<a href="#" class="re-name">${pReply.respondent }</a>:
	   							<span class="main-content">${pReply.replyContent }</span>
	   							<div class="ans-co-bottom">
	   								<span class="ans-time">${fn:substring(pReply.replyTime,0,19) }</span>
	   								<a href="javascript:;" onclick="reply(this)">回复</a>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
   					<c:if test="${requestScope.question.sharezone == '学员区' }">
   						<c:forEach items="${reply.studentReplies }" var="sReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content">
   								<input type="hidden" value="${sReply.replyId}" id="${sReply.replyId}">
	   							<a href="#" class="re-name">${sReply.respondent }</a>:
	   							<span class="main-content">${sReply.replyContent }</span>
	   							<div class="ans-co-bottom">
	   								<span class="ans-time">${fn:substring(sReply.replyTime,0,19) }</span>
	   								<a href="javascript:;" onclick="reply(this)">回复</a>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
   					<c:if test="${requestScope.question.sharezone == '我的问题' }">
   						<c:forEach items="${reply.myquestionReplies }" var="mReply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   							<input type="hidden" value="${fn:length(items)}" class="rTimes">
   							<div class="ans-content">
   								<input type="hidden" value="${mReply.replyId}" id="${mReply.replyId}">
	   							<a href="#" class="re-name">${mReply.respondent }</a>:
	   							<span class="main-content">${mReply.replyContent }</span>
	   							<div class="ans-co-bottom">
	   								<span class="ans-time">${fn:substring(mReply.replyTime,0,19) }</span>
	   								<a href="javascript:;" onclick="reply(this)">回复</a>
	   							</div>
	   						</div>
   						</c:forEach>
   					</c:if>
    					<div class="sub-add"><span class="comment">我要评论</span></div>
    					<div class="sub-edit">
    						<textarea class="add-re"></textarea>
    						<input type="hidden" value="${reply.replyId}">
    						<input type="button" value="发表" class="sub-post" onclick="subPost(this)">
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    	</c:forEach>
    	<!-- <div class="w-content-box">
    		<div class="w-left">
    			<div class="ans-intro"><img src=""></div>
    			<div class="respondent">*****</div>
    		</div>
    		<div class="w-right">
    			<div class="w-r-main">
    				<a href="#" class="re-name">一笑奈何</a>:
						<span class="main-content">微微忽然就觉得，自己纠结了半天的问题一点都不重要了。 就算哪天服务器真的关闭了也没关系。 
   							只要她记得他在何时何地跟她说了第一句话。 记得他们去哪里看了风景。 记得他们共乘白雕掠过了山山水水…… 
   							那些回忆并不会因为数据的消失而消失。 所以，就算将来这个游戏关闭了，这个世界上也永远会有一处地方——也许我心,
   							也许彼心，白衣红影并肩而立。 看落霞峰上，永不落霞。陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span>							
    			</div>
    			<div class="w-reply">
    				<div class="r-top">
    					<span class="r-time">2016-8-18 22:15</span>&nbsp;
    					<div class="r-fold">评论</div>
    				</div>
    				<div class="sub-reply">
   						<div class="answerer-img"><img src=""/></div>
   						<div class="ans-content">
   							<a href="#" class="re-name">芦苇微微</a>:
   							<span class="main-content">微微忽然就觉得，自己纠结了半天的问题一点都不重要了。 就算哪天服务器真的关闭了也没关系。 
   							只要她记得他在何时何地跟她说了第一句话。 记得他们去哪里看了风景。 记得他们共乘白雕掠过了山山水水…… 
   							那些回忆并不会因为数据的消失而消失。 所以，就算将来这个游戏关闭了，这个世界上也永远会有一处地方——也许我心,
   							也许彼心，白衣红影并肩而立。 看落霞峰上，永不落霞。陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span>
   							<div class="ans-co-bottom">
   								<span class="ans-time">2016-8-18 22:15</span>
   								<a href="#">回复</a>
   							</div>
   						</div>
    					<div class="sub-add"><span class="comment">我要评论</span></div>
    					<div class="sub-edit">
    						<textarea class="add-re"></textarea>
    						<input type="button" value="发表" class="sub-post">
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>  -->
    	
    	<!-- <div class="page-div">
			<input type="button" value="上一页" id="next"  class="abtn">
			<a href="javascript:;">1</a>
			<a href="javascript:;">2</a>
			<span>第&nbsp;<span id="now">1</span>/<span id="total">2</span>&nbsp;页</span>
			<input type="button" value="下一页" id="next"  class="abtn">
		</div> -->
    	<div class="edit-div">
    		<textarea name="card" style="width:80%;height:200px;visibility:hidden;"></textarea>
    		<input type="button" value="发表" class="abtn" onclick="Post()">
    	</div>
    </div>
    <div class="icon-rtop">&#xe933;</div>
    <div id="returntop">返回顶部</div>
  </body>
</html>
