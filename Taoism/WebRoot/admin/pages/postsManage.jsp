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
	<div class="tip-wrap"><span class="tip"></span></div>
	<div class="main">
		<div class="top">
		<form action="${pageContext.request.contextPath }/listquestion_findByQTime.action?currentPage=1&rows=10" method="post">
			<div class="time">
				<span>发帖日期：</span>
				<input type="text" id="from_date" name="startTime" value="${startTime }"> &macr;
				<input type="text" id="to_date" name="endTime" value="${endTime }"> 
			</div>
			<input type="text" placeholder="发帖人" id="poster" name="username" value="${author}">
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
   				<div class="post-content <c:if test="${questions.con6 == 1 }">del-color</c:if>">
   					<div class="post-title">
   						<span class="post-top <c:if test="${questions.con4 == 0 || questions.con4 == null }">phide</c:if>" title="置顶">置顶</span>  
   						<span class="post-best <c:if test="${questions.con5 == 0 || questions.con5 == null }">phide</c:if>" title="精华帖">精</span>
   						<a href="javascript:void(0);" title="" class="title" id="${questions.QId }" data-sharezone="${questions.sharezone }">${questions.QTitle }</a>&nbsp;
   						<c:set var="qTime" value="${questions.QTime }"></c:set>
   						<span class="post-time">发帖时间：${fn:substring(qTime,0,10)}</span>
   						<span class="post-time" style="margin-left:20px;">发帖人：${questions.username}</span>
   						<div class="post-operate">
	   						<input type="hidden" value="${questions.QId }" class="qid">
	   						<a href="javascript:void(0);" class="reply-ope unset-top <c:if test="${questions.con4 == 0 ||questions.con4 ==null }">phide</c:if>">取消置顶</a>
	   						<a href="javascript:void(0);" class="reply-ope set-top <c:if test="${questions.con4 == 1 }">phide</c:if>" >置顶</a>
	   						<a href="javascript:void(0);" class="reply-ope unset-best <c:if test="${questions.con5 == 0 ||questions.con5 == null }">phide</c:if>">取消精华帖</a>
	   						<a href="javascript:void(0);" class="reply-ope set-best <c:if test="${questions.con5 == 1}">phide</c:if>">设置精华帖</a>
	   						<a href="javascript:void(0);" class="reply-ope delete <c:if test="${questions.con6 == 1}">phide</c:if>">删除</a>
	   						<a href="javascript:void(0);" class="reply-ope un-delete <c:if test="${questions.con6 == 0 ||questions.con6 == null}">phide</c:if>">恢复</a>
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
   			   			
		<c:if test="${null != qList }">
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
		</c:if>
		<c:if test="${null == qList }">
			<h2 style="color:red;text-align:center;">没有符合条件的记录</h2>
		</c:if>
	</div>
</div>
</body>
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/ligerui.js?t=<%= System.currentTimeMillis()%>"></script>
	<script src="<%=path %>/admin/js/postsManage.js?t=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript">
		var url = '<%=path %>';
		
	</script>
</c:if>
</html>