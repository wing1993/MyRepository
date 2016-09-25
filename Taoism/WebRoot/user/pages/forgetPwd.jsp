<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>找回密码</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<style type="text/css">
		.main{width:550px;height:400px;margin:80px auto;background:#fff;border-radius:5px;}
		.top{text-align:center;padding:10px;border-bottom:1px dashed #ccc;font-weight:bold;font-size:22px;
			margin-bottom:50px;}
		.m-wrap{width:100%;height:30px;line-height:30px;}
		.m-wrap span{display:inline-block;width:200px;height:30px;text-align:right;}
		.m-wrap input[type=text]{width:170px;height:30px;}
		.tip{margin-left:200px;margin-top:4px;height:25px;color:#f00000;font-weight:bold;}
		.btn-gradient{width:70px;height:35px;margin-left:230px;margin-top:60px;}
		#valid_img{width:100px;height:45px;border:1px solid #ccc;}
		.code{margin-left:200px;}
		.re{display:none;margin-top:100px;text-align:center;font-weight:bold;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
  	<script>
  		function change_code(){
			$("#valid_img").attr("src",
					"/Taoism/VerifyCodeServlet?a=" + new Date().getTime());
		}
		
		function check(){
			var code=$("#valid_code").val();
			var mail=$(".mail").val();
			if(mail==""){
				$("#tip1").text("邮箱不能为空！");
				return;
			}else{
				$("#tip1").text("");
			}
			if(!mail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)){
				$("#tip1").text("邮箱的格式不正确！");
				return;
			}
			if(code==""){
				$("#tip2").text("请输入验证码！");
				return;
			}else{
				$("#tip2").text("");
			}

				$.ajax({
					cache: false,
					async: false,
					url:'${pageContext.request.contextPath }/vcode.action',
					type:'post',
					data:{valid_code:$("#valid_code").val()},
					success:function(str){
						if(str=="success"){
							var obj={
									url:'${pageContext.request.contextPath }/sendmail.action',
									type:'post',
									//dataType : "json",
									success:function(str){							
										$("form").css("display","none");
										$(".re").css("display","block");
										$(".re").text(str);							
									}
								};
							$("form").ajaxSubmit(obj);
						}else{
							$("#tip2").text("验证码错误");
							$("#valid_code").select();
						}}
				});
			
		}
  	</script>
  </head>
  
  <body>
    <div class="bd"></div>
    <div class="main">
    	<div class="top">找回密码</div>
    <form action="" method="post">
    	<div class="m-wrap">
    		<span>邮箱：</span>
    		<input type="text" placeholder="请填入注册时填写的邮箱" name="mail" class="mail">
    	</div>
    	<div class="tip" id="tip1"></div>
    	<div class="m-wrap">
    		<span>验证码：</span>
    		<input type="text" placeholder="请输入验证码"  id="valid_code">
    	</div>
    	<div class="tip" id="tip2"></div>
    	<div class="m-wrap">
    		<span></span>
    		<img src="<c:url value='/VerifyCodeServlet'/>" id="valid_img">
    		<div class="code">看不清？<a href="javascript:;" onclick="change_code()">换一张</a></div>
    	</div>
    	
    	<div class="m-wrap"><input type="button" class="btn-gradient" value="提交" onclick="check()"></div>
    </form>
    <div class="re"></div>
    </div>
  </body>
</html>
