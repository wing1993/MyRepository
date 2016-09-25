<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/q_detail.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/q_detail.js"></script>
	<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="card"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
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
		$(function(){
			//发表评论
			$(".sub-post").click(function(){
				$.psot('',{},function(data){//待实现
					if(data=="success"){
						var now=new Date();
						var year=now.getFullYear();
						var month=now.getMonth()+1;
						var day=now.getDate();
						var hour=now.getHours();
						var minute=now.getMinutes();
						var second=now.getSeconds();
						var now_time=null;
						if(month<10)
							month="0"+month;
						if(day<10)
							day="0"+day;
						now_time=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;//计算当前时间
						
						/* var obj="<div class='ans-content'><a href='#' class='re-name'>"+${sessionScope.UsersfromActions[0].username}+"</a>"+
						"<span class='main-content'>"+$(this).prev().val()+"</span><div class='ans-co-bottom'></div><span class='ans-time'>"+
						now_time+"</span></div>";//未完待续
						
						$(this).parent().prev().prev().append(obj); */
					}else{
						alert("评论失败");
					}
				});			
			});
		});
	</script>
  </head>
  <body>
    <div class="bd"></div>
    <div class="main">
    	<div class="main-box"></div>
    	<div class="main-box-fixed">
    		<div class="q_title">微微一笑很倾城</div>
    		<a class="btn" id="forward">转发</a>
    		<a class="btn" id="btn">回复</a>
    	</div>
    	<div class="w-content-box">
    		<!-- <div class="w-left">
    			<div class="ans-intro"><img src=""></div>
    			<div class="respondent">*****</div>
    		</div> -->
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
   						<!-- <div class="answerer-img"><img src=""/></div> -->
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
    						<input type="button" value="发表" class="post">
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    	
    	<div class="w-content-box">
    		<!-- <div class="w-left">
    			<div class="ans-intro"><img src=""></div>
    			<div class="respondent">*****</div>
    		</div> -->
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
   						<!-- <div class="answerer-img"><img src=""/></div> -->
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
    	</div>
    	
    	<div class="page-div">
			<input type="button" value="上一页" id="next"  class="abtn">
			<a href="javascript:;">1</a>
			<a href="javascript:;">2</a>
			<span>第&nbsp;<span id="now">1</span>/<span id="total">2</span>&nbsp;页</span>
			<input type="button" value="下一页" id="next"  class="abtn">
		</div>
    	<div class="edit-div">
    		<textarea name="card" style="width:80%;height:200px;visibility:hidden;"></textarea>
    		<input type="button" value="发表" class="abtn">
    	</div>
    </div>
    <div class="icon-rtop">&#xe933;</div>
    <div id="returntop">返回顶部</div>
  </body>
</html>
