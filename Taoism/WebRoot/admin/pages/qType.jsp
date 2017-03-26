<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>问题类型管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<c:if test="${admin.qtype == 1 }">
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/admin.css">
	<style>
		.wrap-add .tip{margin-left:80px;;color:red;display:none;}
		.wrap-old li{padding:6px;margin-top:10px;background:#ddd;width:240px;}
		.wrap-old li span{display:inline-block;}
		.icon-d{content:'\e9ac';float:right;text-align:right;cursor:pointer;color:#969090;}
		.icon-d:hover{color:#E84747;}
	</style>
	</c:if>
  </head>
  <c:if test="${admin.qtype == 0 }">
  <body><h2 style="color:red;">您没有问题类型管理的权限</h2></body>
  </c:if>
  <c:if test="${admin.qtype == 1 }">
  <body>
    <form action="">
	    <div class="wrap-add">
	    	<span>问题类型：</span>
	    	<input type="text" id="new_qtype" name="QTypeName">
	    	<input type="text" style="display:none;"><!-- 只有一个type="text"时，防止按下回车键直接提交 -->
	    	<input type="button" value="新增" id="add">
	    	<div class="tip">该问题类型已存在!</div>
	    </div>
	    <ul class="wrap-old">
	   	<c:forEach items="${questionTypeList}" var="questionType" >
	    	<li><span class="qType">${questionType.QTypeName}</span><span class="icon-d" title="删除" onclick="delType(${questionType.QTypeId}, this);">&#xe9ac;</span></li>
	  	</c:forEach> 
	  	</ul>
	  	<input type="hidden" name="QTypeId" value="">
  	</form>
  </body>
  <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
	<script>
		function delType(tid, obj){
			if(confirm("确定要删除该问题类型吗？")){
				$("input[name='QTypeId']").val(tid);
				$("form").attr("action", "<%=path %>/questionType_find_delete.action").submit();
			}
		}
		$(function(){
			
			$("#new_qtype").keyup(function(e){
				$(".wrap-add .tip").hide();
				if(e.keyCode == 13){
					$("#add").click();
				}
			});
			$("#add").click(function(){
				var new_type= $("#new_qtype").val();
				if(new_type == ""){
					$(".wrap-add .tip").text("请输入问题类型").show();
					
				}else{
					var lis = $(".wrap-old").find("li"),
						flag = 0;
						console.log(lis);
					for(var i = 0; i < lis.length; i ++){
						if($(lis[i]).find(".qType").text() == new_type){
							$("#new_qtype").focus();
							$(".wrap-add .tip").text("该问题类型已存在!").show();
							flag = 1;
							break;
						}
					}
					flag == 0 && $("form").attr("action", "<%=path %>/questionType_find_save.action").submit();
				}				
			});
		});
	</script>
	</c:if>
</html>
