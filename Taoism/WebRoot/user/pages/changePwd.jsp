<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<style type="text/css">
		.main{width:550px;height:370px;margin:80px auto;background:#fff;border-radius:5px;}
		.top{text-align:center;padding:10px;border-bottom:1px dashed #ccc;font-weight:bold;font-size:22px;
			margin-bottom:50px;}
		.m-wrap{width:100%;height:30px;line-height:30px;}
		.m-wrap span{display:inline-block;width:200px;height:30px;text-align:right;}
		.m-wrap input[type=password]{width:170px;height:30px;}
		.tip{margin-left:200px;margin-top:4px;height:22px;color:#f00000;font-weight:bold;}
		.btn-gradient{width:100px;height:35px;line-height:35px;margin-left:230px;margin-top:20px;display:block;}
		#valid_img{width:100px;height:45px;border:1px solid #ccc;}
		.code{margin-left:200px;}
		.result{margin-top:100px;text-align:center;font-weight:bold;display:none;}
		#back{display:none;}
		/* form{display:none;} */
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
	<script>
		function changeKey(){
			var p1=$(".p1").val();
			var p2=$(".p2").val();
			if(p1==""){
				$("#tip1").text("请输入密码！");
			}else if(p1.length<6){
				$("#tip1").text("密码的长度不能小于6");
			}else if(p2==""||p1!=p2){
				$("#tip1").text("两次输入的密码不一致！");
			}else{
				$("#tip1").text("");
				var obj={
					url:'${pageContext.request.contextPath}/changeKey.action',
					type:'post',
					success:function(str){
						$("form").css("display","none");
						$(".result").css("display","block");
						if(str=="success"){
							$(".re-info").text("密码修改成功！");
						}else{
							$(".re-info").text("修改失败！");
							$("#login").css("display","none");
							$("#back").css("display","block");
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
    	<div class="top">修改密码</div>
    	<form>
    	<div class="m-wrap">
    		<span>新密码：</span>
    		<input type="password" name="password" class="p1">
    	</div>
    	<div class="tip"></div>
    	<div class="m-wrap">
    		<span>密码确认：</span>
    		<input type="password" class="p2">
    	</div>
    	<div class="tip" id="tip1"></div>
    	<input type="hidden" value="${param.userId }" name="userId">
    	<input type="button" value="提交" class="btn-gradient" onclick="changeKey()">
    	</form>
    	<div class="result">
	    	<div class="re-info">找回密码失败</div>
	    	<a href="${pageContext.request.contextPath }/user/pages/login.jsp" class="btn-gradient" id="login">立即登陆</a>
	    	<a href="${pageContext.request.contextPath }/user/pages/forgetPwd.jsp" class="btn-gradient" id="back">返回</a>
	    </div>
    </div>
    
  </body>
</html>
