<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% String path = request.getContextPath(); %>   
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>帖子管理</title>
	<c:if test="${admin.postsManage == 1 }">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/liger-ui.css?t=<%= System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/common.css?t=<%= System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/postsManage.css?t=<%= System.currentTimeMillis()%>">
	</c:if>
</head>
<c:if test="${admin.postsManage == 0 }">
<body>
	<h2 style="color:red;">您没有帖子管理的权限</h2>
</body>
</c:if>
<c:if test="${admin.postsManage == 1 }">
<body style="margin:8px">
	<div class="main">
		<div class="top">
		<form action="${pageContext.request.contextPath }/listquestion_findByQTime.action?currentPage=1&rows=10" method="post">
			<div class="time">
				<span>发帖日期：</span><input type="text" id="from_date" name="startTime"> &macr; <input type="text" id="to_date" name="endTime"> 
			</div>
			<input type="text" placeholder="发帖人" id="poster" name="username">
			<input type="button" class="query" value="查询" id="query" onclick='queryQuestion()'>
		</form>
		</div>
		<%-- <c:out value="${qList}"></c:out>
		<c:out value="${cList}"></c:out>
		<c:out value="${page}"></c:out> --%>
		<div class="post">
			<c:forEach items="${qList}" var="questions">
			<div class="post-wrap">
   				<div class="rep-num" title="回复数">${questions.con3 }</div>
   				<div class="post-content">
   					<div class="post-title">
   						<c:if test="${questions.con4 == 1 }"><span class="post-top" title="置顶">置顶</span></c:if>  
   						<c:if test="${questions.con5 == 1 }"><span class="post-best" title="精华帖">精</span></c:if>
   						<a href="javascript:void(0);" title="" class="title" id="${questions.QId }" data-sharezone="${questions.sharezone }">${questions.QTitle }</a>&nbsp;
   						<c:set var="qTime" value="${questions.QTime }"></c:set>
   						<span class="post-time">发帖时间：${fn:substring(qTime,0,10)}</span>
   						<div class="post-operate">
   						<c:if test="${questions.con4 == 1 }">
   							<a href="" class="reply-ope unset-top">取消置顶</a>&nbsp;
   						</c:if>
   						<c:if test="${questions.con5 == 1 }">
   							<a href="" class="reply-ope unset-best">取消精华帖</a>&nbsp;
   						</c:if>
   						<c:if test="${questions.con4 == 0 || questions.con4 == null }">
   							<a href="" class="reply-ope set-top" >置顶</a>&nbsp;
   						</c:if>
   						<c:if test="${questions.con5 == 0 || questions.con5 == null}">
   							<a href="" class="reply-ope set-best">设置精华帖</a>&nbsp;
   						</c:if>
   							<a href="" class="reply-ope delete">删除</a>&nbsp;
   						</div>
   					</div>
   					<div class="post-rep">
    					<div class="first-rep">${questions.QContent }</div>
    					<div class="post-right">
    						<c:if test="${not empty questions.con1 }">
    							<span class="icon-message">&#xe929; </span>
    						</c:if>
    						<span class="last-replyer" title="最后回复人">${questions.con1 }</span>
    						<span class="last-rep-time">${questions.con2 }</span>
    					</div>
   					</div>
   				</div>
   			</div>
   			</c:forEach>   			

		<div class="page-div">
			<c:set var="pages" value="${page }"></c:set>
			<c:if test="${pages.hasPrePage }">
				<a href="<%=path %>/listquestion_findByQTime.action?currentPage=${pages.currentPage-1}&rows=10" class="abtn">上一页</a>
			</c:if>
			<c:forEach items="${cList }" var="cList">
				<c:choose>
				<c:when test="${cList.page==0 }">
					<a href="<%=path %>/listquestion_findByQTime.action?currentPage=${pages.currentPage }&rows=10" data-pagenum="${pages.currentPage }">${pages.currentPage }</a>
				</c:when>
				<c:otherwise>
					<a href="<%=path %>/listquestion_findByQTime.action?currentPage=${cList.page }&rows=10" data-pagenum="${cList.page }">${cList.page }</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<span>第&nbsp;<span id="now">${page.currentPage }</span>/<span id="total">${page.totalPage }</span>&nbsp;页</span>
			<c:if test="${pages.hasNextPage }">
				<a href="<%=path %>/listquestion_findByQTime.action?currentPage=${pages.currentPage+1}&rows=10" class="abtn">下一页</a>
			</c:if>
			<input type="number" min="0" style="width:50px;" class="jump-in">
			<a class="btn-sub" href="javascript:void(0);" onclick="Jump(this);"><span class="sure">确定</span></a>
		</div>
	</div>
</body>
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/ligerui.js?t=<%= System.currentTimeMillis()%>"></script>
	<script src="<%=path %>/admin/js/postsManage.js?t=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript">
		var url = '<%=path%>';
		$(function(){
			var pNum = $("#now").html();
			$(".jump-in").val(parseInt(pNum) + 1);
			$(".page-div").find('a[data-pagenum="'+pNum+'"]').addClass('now-page');
			$(".jump-in").keyup(function(e){
				if(e.keyCode == 13){
					$(".btn-sub").trigger("onclick");
					$(".sure").click();
				}
			});
						
		});
		
		function Jump(obj){
			var now_page = parseInt($(".jump-in").val()),//输入值
				max_page = parseInt($("#total").text()); //最大页数
			if($(".jump-in").val() == ""){
				$(".jump-in").focus();
			}else if(now_page > max_page){
				$(".jump-in").select();
			}else{
				$(obj).attr("href", "<%=path %>/listquestion_findByQTime.action?currentPage=" + $(".jump-in").val() + "&rows=10");
			}
		}
	</script>
</c:if>
</html>