<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发布招生传法信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		html{font-family:'Microsoft YaHei'}
		.bd{position:fixed;z-index:-10;top:0;left:0;width:100%;height:100%;background-image:url(../../images/register_bmg1.jpg);
			background-repeat:no-repeat;background-size:cover;}
		.main{width:700px;margin:50px auto;background:#fff;border-radius:5px;padding:20px;}
		.top{margin-top:10px;font-size:20px;font-weight:bold;text-align:center;}
		textarea{resize:none;width:400px;height:200px;line-height:23px;}
		.tb{margin:20px 0 20px 30px;}
		.tb td:nth-of-type(1){vertical-align:top;}
		.tb td{height:32px;}
		.tip1,.tip2{color:red;display:none;}
		.btn{text-align:center;}
	</style>
	<script>
		function disappear(obj){
			if($(obj).val()!=""){
				$(obj).next().css("display","none");
			}
		}
		function check(){
			if($("#con1").val()==""){
				$(".tip1").css("display","inline-block");
			}else if($("#messageContent").val()==""){
				$(".tip2").css("display","block");
			}else{
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
				now_time=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		
				$("#publishTime").val(now_time);
				var obj={
					url:'/Taoism/message_sava.action',
					type:'post',
					success:function(data){
						if(data=="success"){
							alert("发布成功！");
						}else{
							alert("发布失败，请稍后再试！");
						}
					}
				};
				$("form").ajaxSubmit(obj);
			}
		}
	</script>
  </head>
  
  <body>
    <div class="bd"></div>
    <div class="main">
    	<div class="top">发布招生传法信息</div>
    	<form action="" method="post">
    		<table class="tb">
    			<tr>
    				<td>信息标题：</td><td><input type="text" name="con1" id="con1" onblur="disappear(this)"><span class="tip1">请输入标题</span></td>
    			</tr>
    			<tr>
    				<td>信息内容：</td>
    				<td><textarea name="messageContent" id="messageContent" onblur="disappear(this)"></textarea><div class="tip2">请输入内容</div></td>
    			</tr>
    		</table>
    		<input type="hidden" value="${sessionScope.UsersfromActions[0].username }" name="author">
    		<input type="hidden" name="publishTime" id="publishTime">
    		<div class="btn">
	    		<input type="button" value="发布" onclick="check()">&nbsp;&nbsp;
	    		<input type="button" value="取消" onclick="cancel()">
    		</div>
    	</form>
    </div>
  </body>
</html>
