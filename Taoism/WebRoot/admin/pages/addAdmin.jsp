<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addAdmin</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/common.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/liger-ui.css?t=<%=System.currentTimeMillis()%>">
	<style type="text/css">
	.newAdmin{position:relative;}
	.existed{width:300px;}
	.existed li{padding:5px;background:#ddd;margin-top:6px;}
	.add-admin{background-color:#28A6FF;border:none;color:#fff;cursor:pointer;padding:6px 4px;border-radius:4px;
		position:absolute;top:0;margin-left:15px;
	}
	.add-admin:hover{box-shadow:0px 0px 3px #2999e8;}
	.add-input{height:27px;}
	.newAdmin{margin-left:40px;}
	.l-text-wrapper{display:inline-block;}
	</style>
  </head>
  <body>
    <span style="margin-left:40px;">已存在的系统管理员:</span>
	<ul class="existed">
		<li><span id="1">root1</span><span class="icon-d" title="删除">&#xe15c;</span></li>
		<li><span id="2">root2</span><span class="icon-d" title="删除">&#xe15c;</span></li>
		<li><span id="3">root3</span><span class="icon-d" title="删除">&#xe15c;</span></li>
	</ul>
	<div class="newAdmin">
		<input type="text" placeholder="请输入弟子的法号" class="add-input"> 
		<input type="button" class="add-admin" value="新增管理员">
	</div>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/ligerui.js?t=<%=System.currentTimeMillis()%>"></script>
  <script type="text/javascript">
  	$(function(){
  		var Data = [{"id":1,"username":"nmae"},{"id":2,"username":"hhh"},{"id":3,"username":"uii"},{"id":4,"username":"uyyi"}];
//   		$(".add-input").attr("data-data", Data);
  		$(".add-input").ligerComboBox({
  			width:150,
  			height:25,
  			valueField: 'id',
			textField: 'username',
  			data:Data,
  			is_clear:true,
  			Illegal_input:function(){
//   				$(".newAdmin").append("<div class='no-dizi' style='color:red;'>请输入弟子的法号</div>")
  			},
  			autocomplete: function(e) {
  				$(".l-box-select").find("tr").show();
				var data = Data, 
					tr_val = [],//过滤后的数据
					_key = $.trim(e.key);
				if(_key != "") {
					for(var i = 0; i < data.length; i++) {
						var str = data[i]['username'];
						var index = str.indexOf(_key);
						if(index != -1) {
							tr_val.push(data[i]['id']);
// 							$(".l-box-select").find("tr[value='" + tr_val + "'] span").addClass("l-highLight");
						}else{
							$(".l-box-select").find("tr[value='" + data[i]['id'] + "']").hide();
						}
					}
				} 
				
				for(var j = 0; j < tr_val.length; j++){
					$(".l-box-select").find("tr[value='" + tr_val[j] + "']").show();
				}
				e.show();
			}
  		});
		$(".existed").on("click", ".icon-d", function(){
			if(confirm("确定要删除该管理员吗？")){
				var id = $(this).prev().attr('id');
				$.post('', {userid: id}, function(data){
					if(1){ //测试
						$(this).parents('li').remove();
					}
				});
			}
		});
		
		$(".add-admin").click(function(){
			var new_admin = $(".add-input").val();
			if( new_admin== ''){
				alert("请输入弟子的法号！");
			}else{
				$.post('', {username: new_admin}, function(data){
					if(1){//测试
						$(".existed").append('<li><span>' + new_admin + '</span><span class="icon-d" title="删除">&#xe15c;</span></li>');
					}
				});
			}
		});
  	});
  </script>
</html>
