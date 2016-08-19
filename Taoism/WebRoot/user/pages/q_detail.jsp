<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/q_detail.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script>
		$(function(){
			$(".w-left").css("height",$(".w-right").height());
		});
	</script>
  </head>
  <body>
    <div class="bd"></div>
    <div class="main">
    	<div class="main-box">
    		<div class="q_title">微微一笑很倾城</div>
    	</div>
    	<div class="w-content-box">
    		<!-- <div class="w-left">
    			<div class="ans-intro"><img src=""></div>
    			<div class="respondent">*****</div>
    		</div> -->
    		<div class="w-right">
    			<div class="w-r-main"></div>
    			<div class="w-reply">
    				<div class="r-top">
    					<span class="r-time">2016-8-18 22:15</span>&nbsp;
    					<div class="r-fold">收起回复</div>
    				</div>
    				<div class="sub-reply">
   						<!-- <div class="answerer-img"><img src=""/></div> -->
   						<div class="ans-content">
   							<a href="#">芦苇微微</a>:
   							<span class="main-content">微微忽然就觉得，自己纠结了半天的问题一点都不重要了。 就算哪天服务器真的关闭了也没关系。 
   							只要她记得他在何时何地跟她说了第一句话。 记得他们去哪里看了风景。 记得他们共乘白雕掠过了山山水水…… 
   							那些回忆并不会因为数据的消失而消失。 所以，就算将来这个游戏关闭了，这个世界上也永远会有一处地方——也许我心,
   							也许彼心，白衣红影并肩而立。 看落霞峰上，永不落霞。陌上花开蝴蝶飞，江山犹是昔人非；遗民几度垂垂老，游女长歌缓缓归！</span>
   							<div class="ans-co-bottom">
   								<span class="ans-time">2016-8-18 22:15</span>
   								<a href="#">回复</a>
   							</div>
   						</div>
    					
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
</html>
