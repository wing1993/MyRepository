<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
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
				<input type="text" name="valid_code" id="valid_code" placeholder="验证码">
				<img src="<c:url value='/VerifyCodeServlet'/>" id="valid_img" title="点击刷新验证码" onclick="change_code()">
			</div>	
			<input type="button" value="登录" id="login_btn">
		</div>
		<div class="bottom">
			<a href="${pageContext.request.contextPath }/user/pages/forgetPwd.jsp" style="float:left;">忘记密码</a>
			<a href="register.jsp" style="float:right;">立即注册</a>
		</div>
		</form>
	</div>
</body>
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
				$.ajax({
					cache: false,
					async: false,
					url:'${pageContext.request.contextPath }/vcode.action',
					type:'post',
					data:{valid_code:$("#valid_code").val()},
					success:function(str){
						if(str=="success"){
							var obj={
									url:'${pageContext.request.contextPath }/user_login.action',
									type:'post',
									//dataType : "json",
									success:function(str){							
										$("form").submit();							
									},
									error : function(data) {  
							            $("#wrong_info").text("用户名或密码错误(信息准确则请等待审核)");  
							        }
								};
							$("form").ajaxSubmit(obj);
						}else{
							$("#wrong_info").text("验证码错误");
							$("#valid_code").select();
						}}
				});
			}
		});
		
		$("#valid_code").keyup(function(e){
			if(e.keyCode == 13){
				$("#login_btn").click();	
			}
		});
	});
	

	//点击验证码图案刷新
	function change_code(){
		$("#valid_img").attr("src",
				"/Taoism/VerifyCodeServlet?a=" + new Date().getTime());
	}
</script>
</html>