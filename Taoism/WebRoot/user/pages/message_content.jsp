<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>消息查看</title>
	<style type="text/css">
		html{font-family:'Microsoft YaHei'}
		.bmg{width:900px;margin:80px auto;box-shadow: 2px 4px 10px #004B97;
			border-radius:10px;border:1px solid #ADADAD;}
		.main{width:800px;margin:40px auto;}
		.main input{border-radius:5px;width:60px;height:30px;background:#2894FF;color:#fff;cursor:pointer;}
		.top{text-align:center;border-bottom:2px solid #ADADAD;}
		.top-1{font-size:25px;font-family:SimHei;font-weight:bold; color:#FF0000;height:10%;margin-bottom:3%;}
		.top-2{margin-bottom:2%;font-size:15px;color:#6c6c6c;}
		.top2 span{display:inline-block;}
		.msg-content{text-indent:2em;margin-top:20px;min-height:300px;}
		.btn{text-align:center;}
		input[type=button]{outline:none;border:none;}
	</style>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			$("input").click(function(){
				window.close();
			});
		});
	</script>
</head>
<body>
	<div class="bmg">
		<div class="main">
			<div class="top">
				<div class="top-1">${param.message_title }</div>
				<div class="top-2">
					<c:set var="pTime" value="${param.publish_time }"></c:set>
					<span class="p-time">${fn:substring(pTime,0,19)}</span>&nbsp;
					<span class="author">发布人：${param.author }</span>
				</div>
			</div>
			<div class="msg-content">${param.message_content }</div>
			<div class="btn"><input type="button" value="关闭"></div>
		</div>
	</div>
</body>
</html>