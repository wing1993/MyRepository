<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>注册页面</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../css/register.css">
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.form.js"></script>
	<script type="text/javascript" src="../js/register.js"></script>
	<script type="text/javascript">
		//注册
		function sign_up(){
			//检查所有的必填选项是否为空
			if(check_null()){
				//$("#register_form").submit();
				var suburl=null;
				if($("#user_type").val()=="弟子"){
					suburl='${pageContext.request.contextPath }/imgFile_uploadFile.action';
				}else{
					suburl='${pageContext.request.contextPath }/user_registry.action';
				}
				var obj={
						url:suburl,
						type:'post',
						//dataType : "json",
						success:function(str){	alert(str);					
							if(str=="success"){
								alert("注册成功！");
								window.location.href="login.jsp";
							}else if(str=="name_registered"){
								$("#registered_name").css("display","block");
							}else if(str=="mail_registered"){
								$("#registered_mail").css("display","block");
							}else{
								alert("注册失败！");
							}
						}
					};
				$("form").ajaxSubmit(obj);
			}
			
		}
		
		function back(){
			history.back();
		}
	</script>
	<style type="text/css"></style>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="register_box">
			<div class="top">
				<span><a href="${pageContext.request.contextPath }/dashi_load.action">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="login.jsp">登录</a></span>
			</div>
			<form id="register_form" <%-- action="${pageContext.request.contextPath }/imgFile_uploadFile.action" method="post" enctype="multipart/form-data" --%>>
				<table class="register_tb">
					<tr>
						<td>注册类型：</td>
						<td>
							<select name="userType" id="user_type" onchange="user_type_change()">
								<option value="普通">普通</option>
								<option value="学员">学员</option>
								<option value="弟子">弟子</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>
							<select name="gender">
								<option>男</option>
								<option>女</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>居住城市：</td>
						<td>
							<select placeholder="请选择省份" id="selProvince" onchange="provinceChange();" name="con2"></select>
							<select id="selCity" name="city"></select>
							<span class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请如实填写所您居住的城市名称，方便就近沟通</span>
						</td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td>
							<input type="text" name="username" onblur="check_name(this)">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">不能少于4个字符；各弟子请使用赐名注册</span>
							<div class="wrong_tip" id="wrong_name">用户名的长度不正确，请重新输入</div>
							<div class="wrong_tip" id="null_name">用户名不能为空！</div>
							<div class="wrong_tip" id="registered_name">该用户名已被注册</div>
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
							<input type="password" name="password" id="password1"  onblur="check_pw1()">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">请输入不少于6个字符的密码</span>
							<div class="wrong_tip" id="wrong_pw1">密码的长度不正确，请重新输入</div>
							<div class="wrong_tip" id="null_password">密码不能为空！</div>
						</td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td>
							<input type="password" id="password2" onblur="check_pw2()">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">请再次输入密码</span>
							<div class="wrong_tip" id="wrong_pw2">两次输入的密码不一致</div>
						</td>
					</tr>
					<tr>
						<td>手机：</td>
						<td>
							<input type="text" name="phone">
							<span class="red">&nbsp;*&nbsp;</span>
							<span class="tip">请如实填写您的手机号码</span>
						</td>
					</tr>
					<tr>
						<td>手机验证码：</td>
						<td>
							<input type="text" name="" id="">
							<input type="button" value="发送验证码" id="send_code">
						</td>
					</tr>
					
					<tr>
						<td>真实姓名：</td>
						<td>
							<input type="text" name="realname">
							<span class="red" id="realname"></span>
							<div class="wrong_tip" id="null_realname">请输入您的真实姓名</div>
						</td>
					</tr>
					<tr>
						<td>出生年月：</td>
						<td>
							<input type="date" name="birthdayData">
							<span class="red" id="birthday"></span>
							<div class="wrong_tip" id="null_date">请输入出生年月</div>
						</td>
					</tr>
					<tr class="picture">
						<td>照片：</td>
						<td>
							<input type="file" name="imgfile" id="picture" >
							<!-- <input type="text" name="picture" id="picture" >
							不能使用button标签，因为使用表单提交？？
							<input type="button" onclick="path.click()" class="valid" value="浏览">
							<input type="file" id="path" style="display:none;" onchange="picture.value=this.value"> -->						
						</td>
					</tr>
					<tr>
						<td>QQ：</td>
						<td><input type="text" name="qq"></td>
					</tr>
					<tr>
						<td>微信：</td>
						<td><input type="text" name="weixin"></td>
					</tr>
					<tr>
						<td>邮箱：</td>
						<td>
							<input type="text" name="mail" onblur="check_mail(this)">
							<span class="red">&nbsp;*&nbsp;</span>
							<div class="wrong_tip" id="wrong_mail">邮箱的格式不正确，请重新填写</div>
							<div class="wrong_tip" id="registered_mail">该邮箱已被注册</div>
						</td>
					</tr>
					<tr>
						<td>地址：</td>
						<td><input type="text" name="address"></td>
					</tr>
					<tr class="introduce">
						<td>个人简介：</td>
						<td><textarea name="introduce"></textarea></td>
					</tr>
				</table>
				<input type="hidden" name="state" value="0">
				<!-- <input type="hidden" name="con1" value="">
				<input type="hidden" name="con2" value="">
				<input type="hidden" name="con3" value="">
				<input type="hidden" name="con4" value="">
				<input type="hidden" name="con5" value="">
				<input type="hidden" name="con6" value="">
				<input type="hidden" name="con7" value=""> -->
				<div class="btn">
					<input type="button" value="注册" id="register" onclick="sign_up()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="取消" onclick="back()">
				</div>
			</form>
		</div>
		
	</div>
</body>
</html>