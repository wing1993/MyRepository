<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.form.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#login_btn").click(function(){
				var username=$("input[name='username']").val();
				var pwd=$("input[name='password']").val();
				if(username==""){
					$("#wrong_info").text("请输入用户名");
					//$("#wrong_info").addClass("wrong");
					if(pwd=="")
						$("#wrong_info").text("请输入用户名和密码");
				}else if(pwd==""){
					//$("#wrong_info").addClass("wrong");
					$("#wrong_info").text("请输入密码");
				}else if($("#valid_code").val()==""){
					$("#wrong_info").text("请输入验证码");
				}else {
					$("form").submit();
					/* var obj={
							url:'user_login.action',
							type:'post',
							success:function(str){
								
								if(str=="error"){
								alert("注册失败,用户名重复！");
								window.location.href="register.jsp";
								}else{
									alert("注册成功！");
									
								}
							}
						};
					$("#form").ajaxSubmit(obj); */
				}
			});
		});
		

		//点击验证码图案刷新
		function change_code(){}
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="main-top">用户登录</div>
		<form action="${pageContext.request.contextPath }/user_login.action" method="post">
		<div class="login-box">
			<div class="tip" id="wrong_info"></div>
			<div class="login-item">
				<div class="logo" id="user"></div>
				<input type="text" name="username" placeholder="用户名">
			</div>
			
			<div class="login-item">
				<div class="logo" id="lock"></div>
				<input type="password" name="password" placeholder="密码">
			</div>
			
			<div class="login-item">
				<input type="text" name="" id="valid_code" placeholder="验证码">
				<img src="user.jpg" id="valid_img" title="点击刷新验证码" onclick="change_code()">
			</div>
			<input type="button" value="登录" id="login_btn">
		</div>
		<div class="bottom">
			<a href="#" style="float:left;">忘记密码</a>
			<a href="register.jsp" style="float:right;">立即注册</a>
		</div>
		</form>
	</div>
</body>
</html>