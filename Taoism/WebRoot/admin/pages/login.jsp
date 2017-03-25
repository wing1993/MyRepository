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
		
	function check(){
		var adminName=$("input[name='adminName']").val();
		var pwd=$("input[name='password']").val();
		console.log(adminName);console.log(pwd);
		if(adminName==""){
			$(".msg_error").text("请输入用户名");
		if(pwd=="")
				$(".msg_error").text("请输入用户名和密码");
		}else if(pwd==""){
			$(".msg_error").text("请输入密码");
		}else {
			var obj={
					url:'${pageContext.request.contextPath }/admin_login.action',
					type:'post',
					success:function(str){							
						$("form").submit();							
					},
					error : function(data) {  
			            $(".msg_error").text("用户名或密码错误");  
			        }
				};
			$("form").ajaxSubmit(obj);
			};
	}
	
	
		/* function check(){
			if ($(".username").val()==""||$(".password").val()=="") {
				$(".msg_error").css("display","block");
			}else{
				$("form").submit();
			}
		} */
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
			<span class="msg_error">账号和密码不能为空</span>
			<div class="item"><input type="button" value="登录" onclick="check()"></div>
		</form>
	</div>
</body>
</html>