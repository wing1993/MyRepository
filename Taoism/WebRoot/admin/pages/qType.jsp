<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>问题类型管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="../css/admin.css">
	<style>
		.wrap-old li{padding:6px;margin-top:10px;background:#ddd;width:240px;}
		.wrap-old li span{display:inline-block;}
		.icon-d{float:right;text-align:right;cursor:pointer;color:#969090;}
		.icon-d:hover{color:#E84747;}
	</style>
  </head>
  
  <body>
    <div class="wrap-add">
    	<span>问题类型：</span>
    	<input type="text" id="new_qtype">
    	<input type="button" value="新增" id="add">
    </div>
    <ul class="wrap-old">
    	<li><span class="qType">应用解惑</span><span class="icon-d" title="删除">&#xe15c;</span></li>
    	<li><span class="qType">问事</span><span class="icon-d" title="删除">&#xe15c;</span></li>
    </ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script>
		$(function(){
		
			$(".wrap-old").on("click", ".icon-d", function(){
				if(confirm("确定要删除该问题类型吗？")){
					$(this).parent().remove();
				}
			});
			
			$("#new_qtype").keyup(function(e){
				if(e.keyCode == 13){
					$("#add").click();
				}
			});
			$("#add").click(function(){
				var new_type= $("#new_qtype").val();
				if(new_type == ""){
					alert("请输入问题类型");
				}else{
					var types = $("ul.wrap-old").find(".qType"),
						flag = 0;
					for(var i=0;i < types.length; i++){
						if(new_type == $(types[i]).text()){
							alert("该问题类型已存在");
							flag = 1;
							break;
						}
					}
					if(!flag){
						var str = '<li><span class="qType">'+ new_type +'</span><span class="icon-d" title="删除">&#xe15c;</span></li>';
						$("ul.wrap-old").append(str);
					}
				}				
			});
		});
	</script>
</html>
