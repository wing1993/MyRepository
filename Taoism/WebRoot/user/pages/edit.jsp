<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userinfo" value="${sessionScope.UsersfromActions }" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>修改个人信息</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../css/register.css">
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.form.js"></script>
	<script type="text/javascript" src="../js/register.js"></script>
	<style type="text/css"></style>
	<script>
		$(function(){
			$("#user_type option[value='${userinfo.userType}']").attr("selected","selected");
			$("#sex option[value='${useinfo.sex}']").attr("selected","selected");
			$("#selProvince option[value='${userinfo.con2}']").attr("selected","selected");
			$("#selCity option[value='${userinfo.city}']").attr("selected","selected");
			$("textarea").val("${userinfo.introduce}");
			if(${userinfo.username == '老先生'}){
				$("input[name='username']").attr('disabled', true);
			}
		});
		//修改个人信息
		function edit(){
			if(check_null()){
				var obj={
					url:'/Taoism/user_update.action',
					type:'post',
					success:function(data){
						if(data=="success"){
							alert("修改成功！");
						}else{
							alert("修改失败，请稍后再试！");
						}
					}
				};
				$("#edit_form").ajaxSubmit(obj);
			}
		}
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="register_box">
			<div style="text-align:center;font-size:25px;margin-bottom:10px;">修改个人信息</div>
			<form id="edit_form" method="post" action="">
				<table class="register_tb">
					<tr>
						<td>身份：</td>
						<td>
							<input type="hidden" name="userType" value="${userinfo.userType}">
							<select name="con1" id="user_type">
								<option value="普通">普通</option>
								<option value="学员">学员</option>
								<option value="弟子">弟子</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>
							<select name="gender" id="sex">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>居住城市：</td>
						<td>
							<select id="selProvince" onchange="provinceChange();" name="con2"></select>
							<select id="selCity" name="city"></select>
						</td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td>
							<input type="text" name="username" onblur="check_name(this)" value="${userinfo.username }">

							<div class="wrong_tip" id="wrong_name">用户名的长度不正确，请重新输入</div>
							<div class="wrong_tip" id="null_name">用户名不能为空！</div>
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
							<input type="password" name="password" id="password1"  onblur="check_pw1()" value="${userinfo.password }">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">请输入不少于6个字符的密码</span>
							<div class="wrong_tip" id="wrong_pw1">密码的长度不正确，请重新输入</div>
							<div class="wrong_tip" id="null_password">密码不能为空！</div>
						</td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td>
							<input type="password" id="password2" onblur="check_pw2()" value="${userinfo.password }">
							<div class="wrong_tip" id="wrong_pw2">两次输入的密码不一致</div>
						</td>
					</tr>
					<tr>
						<td>手机：</td>
						<td>
							<input type="text" name="phone" value="${userinfo.phone }">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">请如实填写您的手机号码</span>
						</td>
					</tr>
					
					<tr>
						<td>真实姓名：</td>
						<td>
							<input type="text" name="realname" value="${userinfo.realname }">
							<span class="red" id="realname"></span>
							<div class="wrong_tip" id="null_realname">请输入您的真实姓名</div>
						</td>
					</tr>
					<tr>
						<td>出生年月：</td>
						<td>
							<input type="date" name="birthday" value="${userinfo.birthday }">
							<span class="red" id="birthday"></span>
							<div class="wrong_tip" id="null_date">请输入出生年月</div>
						</td>
					</tr>
					<tr class="picture">
						<td>照片：</td>
						<td>
							<input type="text" name="picture" id="picture" value="${userinfo.picture }">
							<!--不能使用button标签，因为使用表单提交？？-->
							<input type="button" onclick="path.click()" class="valid" value="浏览">
							<input type="file" id="path" style="display:none;" onchange="picture.value=this.value">						
						</td>
					</tr>
					<tr>
						<td>QQ：</td>
						<td><input type="text" name="qq" value="${userinfo.qq }"></td>
					</tr>
					<tr>
						<td>微信：</td>
						<td><input type="text" name="weixin" value="${userinfo.weixin }"></td>
					</tr>
					<tr>
						<td>邮箱：</td>
						<td>
							<input type="text" name="mail" onblur="check_mail(this)" value="${userinfo.mail }">
							<div class="wrong_tip" id="wrong_mail">邮箱的格式不正确，请重新填写</div>
						</td>
					</tr>
					<c:if test="${userinfo.userType=='弟子' }">
					<tr>
						<td>个人简介：</td>
						<td><textarea name="introduce"></textarea></td>
					</tr>
					</c:if>
				</table>
				<input type="hidden" value="${userinfo.userId }" name="userId">
				<input type="hidden" value="${userinfo.state }" name="state"> 
				<div class="btn">
					<a href="javascript:;" id="edit" onclick="edit()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:window.close();">返回</a>
				</div>
			</form>
		</div>
		
	</div>
</body>
</html>