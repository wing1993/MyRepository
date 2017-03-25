<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员登录</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<script src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.form.js"></script>
	<script>
		$(function(){
			$(".username").keyup(function(e){
				if(e.keyCode == 13){
				$(".password").focus();
				}
			});
			$(".password").keyup(function(e){
				if(e.keyCode == 13){
					check();
				}
			});
		});
		function check(){
			var adminName=$("input[name='adminName']").val();
			var pwd=$("input[name='password']").val();
			if(adminName==""){
				$(".msg_error").text("请输入用户名").show();
			if(pwd=="")
					$(".msg_error").text("请输入用户名和密码").show();
			}else if(pwd==""){
				$(".msg_error").text("请输入密码").show();
			}else {
				var obj={
						url:'${pageContext.request.contextPath }/admin_login.action',
						type:'post',
						success:function(str){
							console.log(str);
							str == "error" ? $(".msg_error").text("用户名或密码错误").show() : $("form").submit();							
						}
					};
				$("form").ajaxSubmit(obj);
			}
		}
		
		
		function dis(){
			if($(".username").val()!="" && $(".password").val()!=""){
				$(".msg_error").css("display","none");
			}
		}
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="top">管理员登录</div>
		<form action="${pageContext.request.contextPath }/admin_login.action" method="post">
			<div class="item">
				<input type="text" placeholder="管理员账号" name="adminName" class="username" onblur="dis()">
			</div>
			<div class="item">
				<input type="password" placeholder="密码" name="password" class="password" onblur="dis()">
			</div>
			<p class="msg_error" style="margin-top:0">账号和密码不能为空</p>
			<div class="item"><input type="button" value="登录" onclick="check()"></div>
		</form>
	</div>
</body>
</html>