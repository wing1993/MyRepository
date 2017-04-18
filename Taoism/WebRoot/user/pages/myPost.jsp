<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% String path = request.getContextPath(); %>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'myPost.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/common.css?t=<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" type="text/css" href="<%=path %>/user/css/personal_center.css?t=<%=System.currentTimeMillis()%>">
	<style>
	.post-right{margin-top:10px;}
	.post-title .post-operate{
		float:right;
	}
	.post-title .reply-ope{
		text-decoration:none;
		background-color:#fff;
		color:#aaa;
		padding:3px 6px;
		border:1px solid #ccc;
		cursor:pointer;
		border-radius:4px;
	}
	.post-title .reply-ope:hover{
		border:1px solid #6B99E4;
		color:#6B99E4;
	}
	</style>
  </head>
  
  <body>
  <%-- <c:out value="${qList }"></c:out> --%>
  <c:forEach items="${qList }" var="question">
  	 <div class="post-wrap">
		<div class="rep-num" title="回复数">${question.con3 }</div>
		<div class="post-content">
			<div class="post-title">
				<a href="${pageContext.request.contextPath }/question_find_findReplyByQId.action?QId=${question.QId }&sharezone=${question.sharezone }" title="${question.QTitle }" target="_blank">${question.QTitle }</a>
				<div class="post-operate">
					<input type="hidden" value="${question.QId }" class="qid">
					<a href="javascript:void(0);" class="reply-ope delete">删除</a>
				</div>
			</div>
			<div class="post-rep">
				<div class="first-rep">${question.QContent }</div>
				<div class="post-right">
					<c:if test="${question.con1 != null && question.con1 != ''}"><span class="icon-message">&#xe929; </span></c:if>
					<span class="last-replyer" title="最后回复人">${question.con1 }</span>
					<span class="last-rep-time">${question.con2 }</span>
				</div>
			</div>
		</div>
	</div>
 </c:forEach>
 <c:if test="${null != qList }">
	<div class="page-div">
		<c:set var="pages" value="${page }"></c:set>
		<c:if test="${pages.hasPrePage }">
			<a href="<%=path %>/listquestion_findMyPosts.action?
    			username=${sessionScope.UsersfromActions.username}&currentPage=${pages.currentPage-1}&rows=10" class="abtn">上一页</a>
		</c:if>
		<c:forEach items="${cList }" var="cList">
			<c:choose>
			<c:when test="${cList.page==0 }">
				<a href="<%=path %>/listquestion_findMyPosts.action?
    			username=${sessionScope.UsersfromActions.username}&currentPage=${pages.currentPage }&rows=10" data-pagenum="${pages.currentPage }">${pages.currentPage }</a>
			</c:when>
			<c:otherwise>
				<a href="<%=path %>/listquestion_findMyPosts.action?
    			username=${sessionScope.UsersfromActions.username}&currentPage=${cList.page }&rows=10" data-pagenum="${cList.page }">${cList.page }</a>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<span>第&nbsp;<span id="now">${page.currentPage }</span>/<span id="total">${page.totalPage }</span>&nbsp;页</span>
		<c:if test="${pages.hasNextPage }">
			<a href="<%=path %>/listquestion_findMyPosts.action?
    			username=${sessionScope.UsersfromActions.username}&currentPage=${pages.currentPage+1}&rows=10" class="abtn">下一页</a>
		</c:if>
		<input type="number" min="0" style="width:50px;" class="jump-in">
		<a class="btn-sub" href="javascript:void(0);" onclick="Jump(this);"><span class="sure">确定</span></a>
	</div>
</c:if>
  </body>
  <script src="<%=path %>/js/jquery.min.js"></script>
  <script>
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
		
		$(".delete").click(function(){ //删除
			if(confirm("确定要删除该帖子吗？")){
				Delete(this, "1");
			}
		});
  	});
  	
  	function Delete(obj, str){//删帖
		var id = $(obj).prev().val();
		$.post('<%=path %>/question_delete.action',{"QId":id, "con6": str}, function(data){
			if(data == "success"){
				alert("删除成功");
				$(obj).parents(".post-wrap").remove();
			}else{
				alert("操作失败");
			}
		});
	}
  	function Jump(obj){
		var now_page = parseInt($(".jump-in").val()),//输入值
			max_page = parseInt($("#total").text()); //最大页数
		if($(".jump-in").val() == ""){
			$(".jump-in").focus();
		}else if(now_page > max_page){
			$(".jump-in").select();
		}else{
			$(obj).attr("href", "<%=path %>/listquestion_findMyPosts.action?username=${sessionScope.UsersfromActions.username}&currentPage=" + $(".jump-in").val() + "&rows=10");
		}
	}
  </script>
</html>
