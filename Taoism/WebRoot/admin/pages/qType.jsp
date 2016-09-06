<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>问题类型管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../css/admin.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<style>
		.wrap-add{}
		.wrap-old{}
		.wrap-old-t{padding:6px;margin-top:10px;background:#ddd;width:240px;}
		.wrap-old-t span{display:inline-block;}
		.icon-d{float:right;text-align:right;cursor:pointer;color:#969090;}
		.icon-d:hover{color:#E84747;}
	</style>
	<script>
		$(function(){
			$(".icon-d").click(function(){
				if(confirm("确定要删除该问题类型吗？")){
					$(this).parent().remove();
				}
			});
		});
	</script>
  </head>
  
  <body>
    <div class="wrap-add">
    	<span>问题类型：</span>
    	<input type="text" id="new_qtype">
    	<input type="button" value="确定">
    </div>
    <ul class="wrap-old">
    	<li class="wrap-old-t">
    		<span class="qType">应用解惑</span><span class="icon-d" title="删除">&#xe15c;</span>
    	</li>
    	<li class="wrap-old-t">
    		<span class="qType">问事</span><span class="icon-d" title="删除">&#xe15c;</span>
    	</li>
    </ul>
  </body>
</html>
