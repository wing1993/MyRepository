<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>注册审核</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<style>
		html{font-family:'Microsoft Yahei';font-size:15.1px;}
		.r-tb{border-collapse:collapse;text-align:center;}
		.r-tb thead{background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#fcfcfc), to(#EFEAEA));
			background:-moz-linear-gradient(top,#fcfcfc,#EFEAEA);/*火狐*/
			background:-ms-linear-gradient(top,#fcfcfc,#EFEAEA);/*ie10*/
			FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#fcfcfc,endColorStr=#EFEAEA); /*IE 6 7 8*/ }
		.r-tb td{border:1px solid #d0d0d0;height:25px;max-height:25px;padding:5px;}
		.operate{display:inline-block;text-decoration:none;border-radius:2px;border:1px solid blue;color:blue;padding:4px;}
		.operate:hover{background:#287fff;color:#fff;}
		.r-tb thead tr td:nth-of-type(7){width:47px;}
		.r-tb thead tr td:nth-of-type(3){width:47px;}
		.r-tb thead tr td:nth-of-type(8){width:84px;}
		.r-tb thead tr td:nth-of-type(12){width:90px;}
		.r-tb thead tr td:nth-of-type(13){width:90px;}
		.introduce{width:90px;height:25px;line-height:25px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}
	</style>
	<script>
		$(function(){
			$("#all").click(function(){
				var isChecked = $(this).prop("checked");
				$("input:checkbox").prop("checked",isChecked);
				/* if(this.checked){
					$("input:checkbox").prop("checked",true);
				}else{
					$("input:checkbox").prop("checked",false);
				} */
			});
		});
	</script>
</head>
<body>
	<table class="r-tb">
		<thead>
			<tr>
				<td>全选<input type="checkbox" id="all"></td>
				<td>用户名</td>
				<td>注册<br>类型</td>
				<td>性别</td>
				<td>居住城市</td>
				<td>手机</td>
				<td>真实<br>姓名</td>
				<td>生日</td>
				<td>qq</td>
				<td>微信</td>
				<td>邮箱</td>
				<td>个人简介</td>
				<td>操作</td>
			</tr>
		</thead>
		<c:forEach items="${findUnexamined}" var="unCheck">
			<tr>
				<td><input type="checkbox"></td>
				<td>${unCheck.username }</td>
				<td>${unCheck.userType }</td>
				<td>${unCheck.gender }</td>
				<td>${unCheck.con2}${unCheck.city }</td>
				<td>${unCheck.phone }</td>
				<td>${unCheck.realname }</td>
				<td>${unCheck.birthday }</td>
				<td>${unCheck.qq }</td>
				<td>${unCheck.weixin }</td>
				<td>${unCheck.mail }</td>
				<td title="${unCheck.introduce }"><div class="introduce">${unCheck.introduce }</div></td>
				<td>
					<a href="#" class="operate">通过</a>
					<a href="#" class="operate">拒绝</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td><td></td><td>老先生</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
		</tr>
	</table>
</body>
</html>