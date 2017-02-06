<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/liger-ui.css?t=<%= System.currentTimeMillis()%>">
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/ligerui.js?t=<%= System.currentTimeMillis()%>"></script>
	<script>
		var dialog;
		$(function(){
			dialog = $.ligerDialog.open({
				width:700,
				height:400,
				title: '',
				url: '/Taoism/admin/pages/shieldReply.jsp'
			});
			$(".l-dialog-tc-inner").parents("tr").hide();
		});
	</script>
</head>
<body>

</body>
</html>