<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员登录</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<script src="../../js/jquery.min.js"></script>
	<script>
		function check(){
			if ($(".username").val()==""||$(".password").val()=="") {
				$(".msg_error").css("display","block");
			}else{
				$("form").submit();
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
		<form action="" method="post">
			<div class="item">
				<input type="text" placeholder="管理员账号" name="username" class="username" onblur="dis()">
			</div>
			<div class="item">
				<input type="password" placeholder="密码" name="password" class="password" onblur="dis()">
			</div>
			<span class="msg_error">账号和密码不能为空</span>
			<div class="item"><input type="button" value="登录" onclick="check()"></div>
		</form>
	</div>
</body>
</html>