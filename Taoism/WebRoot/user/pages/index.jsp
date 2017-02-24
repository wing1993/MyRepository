<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>老先生答疑</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/index.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/index.js"></script>
	<script type="text/javascript">
		function ask_pa(obj){
			checkLogin(obj,$('.hdashi_name').text());
		}
		function checkLogin(obj,dashi){
			if(${sessionScope.UsersfromActions==null}){
				alert("您还没有登录，不能向大师提问");
			}else{			
				$(obj).attr("href","${pageContext.request.contextPath }/user/pages/ask_question.jsp?username=${sessionScope.UsersfromActions.username }&askWho="+dashi);
			}
		}
		function checkLogin_Post(obj){
			if(${sessionScope.UsersfromActions==null}){
				alert("您还没有登录，不能发帖！");
			}else{			
				$(obj).attr("href","${pageContext.request.contextPath }/user/pages/post.jsp?username=${sessionScope.UsersfromActions.username }");
			}
		}
		
		//问题列表（glory）
		function findData(obj){
			//aSelected(obj);//无效？
			var Id=$(obj).attr("id");
			var qTitle = "";
			if(Id=="sharezone"||Id=="QTypeName"||Id=="QTime"||Id=="state"){
				$("#search_value").text("");
			}else{qTitle = $("#search_value").val();}
			$("#total").text("");
			$("#now").text("");
			$(".a-page").empty();$("#q_detail").empty();
			$.post("${pageContext.request.contextPath }/question_find_findByDynamicData.action",{sharezone:$("#sharezone").val(),QTypeName:$("#QTypeName").val(),
						QTime:$("#QTime").val(),state:$("#state").val(),QTitle:qTitle,currentPage:$(obj).text()},function(data){
					if(null!=data.qList){
					/*  for(var i=0;i<data.postList.length;i++){ */
						$.each(data.qList,function(i,value){
							if(value.state!=0){var icon="<td class='icon-td' title='已回复'>&#xe905;</td>";}
							else{var icon="<td class='icon-td' title='未回复'>&#xe906;</td>";}
							 var str = "<tr>"+icon
									+"<td><a href='${pageContext.request.contextPath }/question_find_findReplyByQId.action?QId="+value.QId+"&sharezone="+value.sharezone+"' title='"+value.QTitle+"'>"+value.QTitle+"</a></td>"
									+"<td>"+value.username+"<br>"+value.QTime.split('.')[0]+"</td><td>"+value.askWho+"</td><td>"+value.con1+"<br>"+value.con2+"</td>"
									+"<td>"+value.con3+"</td><td>"+value.visits+"</td>"
									+"<td>"+value.QTypeName+"</td></tr>";
							$("#q_detail").append(str);
						});
					/* }  */ 
					
						$.each(data.cList,function(i,value){
							var str = "";
							if(value.page==0){
							    str = str+"<a href='javascript:;' onclick='findData(this)'>"+data.page.currentPage+"</a>&nbsp;";}
							else if(value.page==-1){
								str = str+"<span>...</span>&nbsp;";}
							else {str = str+"<a href='javascript:;' onclick='findData(this)'>"+value.page+"</a>&nbsp;";}
							
							$(".a-page").append(str);
						});
						$("#nowSub").text(data.page.currentPage-1);
						$("#nowAdd").text(data.page.currentPage+1);
						$("#total").text(data.page.totalPage);
						$("#now").text(data.page.currentPage);
					
					}
					if($("#total").text()==""){
						$(".page-div").css("display","none");
						$(".no-data").css("display","block");
					}else{
						$(".no-data").css("display","none");
						$(".page-div").css("display","block");
					}
				    
				});
			$("body").scrollTop($("body").height());//滚动到最底部
		}
		
		//查找所有地区的大师
		function findDashi(obj){
			$(".master-w").empty();
			var action=null;
			var loc=null;
			if($(obj).text()=="所有地区"){
				action="dashi_findDashi!findAll.action";
				loc=null;
			}else{
				action="dashi_findDashi!findByLoc.action";
				loc=$(obj).text();
			}
			$.post(action,{"con2":loc},function(data){
				$.each(data.dashis,function(i,value){
					var str="<div class='master-data'><div class='picture'><img src='${pageContext.request.contextPath }/"+value.picture+"' onclick='showDetail(this)'></div>"+
							"<div class='master-detail'>法号：<label class='dashi_name'>"+value.username+"</label></div>"+
							"<div class='master-detail'>现居城市：<label class='now_city'>"+value.con2+value.city+"</label></div>"+
							"<div class='master-detail' class='detail1'><a href='${pageContext.request.contextPath }/user/pages/ask_question.jsp' target='_blank' class='ask'>我要提问" +
							"</a></div>";
					var hideinfo="<input type='hidden' class='h_gender' value='"+value.gender+"'><input type='hidden' class='h_phone' value='"+value.phone+"'>" +
							"<input type='hidden' class='h_birth' value='"+value.birthday+"'>" +
							"<input type='hidden' class='h_qq' value='"+value.qq+"'><input type='hidden' class='h_weixin' value='"+value.weixin+"'>" +
							"<input type='hidden' class='h_mail' value='"+value.mail+"'><input type='hidden' class='h_introduce' value='"+value.introduce+"'></div>";
					$(".master-w").append(str+hideinfo);
				});
			});
		}
		$(function(){
			findData();
		});
		
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="head">
		<c:choose>
			<c:when test="${sessionScope.UsersfromActions==null }">
			<div class="unlogin">
				<span><a href="${pageContext.request.contextPath }/user/pages/login.jsp">登录</a></span>&nbsp;&nbsp;|&nbsp;
				<span><a href="${pageContext.request.contextPath }/user/pages/register.jsp">注册</a></span>
			</div>
			</c:when>
			<c:otherwise>
			<div class="logined">
				Hi,<span id="username">${sessionScope.UsersfromActions.username }</span>
				<span><a href="${pageContext.request.contextPath }/message_findByAuthor.action">个人中心</a></span>&nbsp;&nbsp;|&nbsp;
				<span><a href="${pageContext.request.contextPath }/user_logout.action">退出</a></span>
			</div>
			</c:otherwise>
		</c:choose>			
		</div>
		<div class="wrap">
			<div class="top">
				<img src="${pageContext.request.contextPath }/images/home.png">&nbsp;&nbsp;
				<div class="top-box">
					<a href="#" class="first-page">首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<span>老先生答疑</span>
				</div>
			</div>

			<!--老先生简介-->
			<div class="boss">
				<div class="boss-title">老先生简介</div>
				<p>现为中华国际易经风水协会会长。被多家企业聘为顾问；从事道法传授；受理和合、改运、催财、风水、调整阴性信息等业务。<br><br>
				自我简介
				<p>	余，人称老先生，又号东北酒仙，自幼多病，身患软骨病等，无法正常行走。6岁时有缘遇道家龙门派恩师修行道法，半年后奇迹般痊愈之后跳墙还俗。80年又遇度师李真，81年皈依拜得佛家金顶毗卢派范纯朴（因文革后没有补戒的僧人）营口第一届老会长照普（补戒后更名）为师。84年前往藏地，学得噶玛葛举派大悲法门整体传承。余，遍访名师后参学正一、鸿一、茅山、闾山等多家有道高人。86年开设个体中医门诊，曾治愈肺癌晚期患者、肝癌、乳腺癌、败血症、对三椎疾病、阴性信息所致疾病等多种大小疑难病症不计其数。曾助一些大小企业、经商、仕途人士改运、催财、催官、催婚等得到满意效果。实践后后略有所得，常有社会各界有缘爱好者，慕名辗转前来求法，广结法友无数。今不揣冒昧愿与祝法友共同探讨切磋我中华传统之文化，起一抛砖引玉的作用。</p>
				</p>
			</div>
			<!--大师团队-->
			<div class="master">
				<div class="boss-title">大师团队<div class="fold_open" onclick="Fold_Open(this)" title="折叠">-</div></div>
				<div class="master-box">
					<!-- <ul class="location">
						<li><a href="#">所有地区</a></li>
						<li><a href="#">辽宁</a></li>
						<li><a href="#">北京</a></li>
						<li><a href="#">天津</a></li>
					</ul> -->
					<ul class="location">
						<li><a href="javascript:void(0);" onclick="findDashi(this)">所有地区</a></li>
						<c:forEach items="${loc}" var="locs">
						<li><a href="javascript:void(0);" onclick="findDashi(this)">${locs }</a></li>
						</c:forEach>
					</ul>
					<div class="master-sub-box">
						<span class="ico-left">‹</span>
						<div class="master-w">	
						<c:forEach items="${dashis }" var="dashis">
							<div class="master-data">
								<div class="picture"><img src="${pageContext.request.contextPath }/${dashis.picture}" onclick="showDetail(this)"></div>
								<div class="master-detail">法号：<label class="dashi_name">${dashis.username }</label></div>
								<div class="master-detail">现居城市：<label class="now_city">${dashis.con2 }${dashis.city }</label></div>
								<div class="master-detail">
									<a href="javascript:void(0);" target="_blank" class="ask" onclick="checkLogin(this,$(this).parent().prev().prev().children('.dashi_name').text())">我要提问</a>
								</div>
								<input type="hidden" value="${dashis.gender }" class="h_gender">
								<input type="hidden" value="${dashis.birthday }" class="h_birth">
								<input type="hidden" value="${dashis.phone }" class="h_phone">
								<input type="hidden" value="${dashis.qq }" class="h_qq">								
								<input type="hidden" value="${dashis.mail }" class="h_mail">								
								<input type="hidden" value="${dashis.weixin }" class="h_weixin">								
								<input type="hidden" value="${dashis.introduce }" class="h_introduce">								
							</div>
						</c:forEach>
						</div>	
						<span class="sico ico-right">›</span>										
					</div>
				</div>
			</div>
			<!--问题列表区-->
			<div class="question-zone">
				<div class="qz-title">问题列表区</div>
				<div class="qz-box">
				<%-- <form id="getquestion_form" action="${pageContext.request.contextPath }/question_find_findByDynamicData.action" >
				 --%>	<ul class="qz-list-ul">
						<li><select class="qz_list" id="sharezone" onchange="findData(this)">
							<c:if test="${sessionScope.UsersfromActions==null }">
								<option value="公开区">公开区</option>
							</c:if>
							<c:if test="${sessionScope.UsersfromActions.userType=='普通' }">
								<option value="所有问题">所有问题</option>
								<option value="我的问题">我的问题</option>
								<option value="公开区">公开区</option>
							</c:if>
							<c:if test="${sessionScope.UsersfromActions.userType=='学员' }">
								<option value="学员区">学员区</option>
								<option value="所有问题">所有问题</option>
								<option value="公开区">公开区</option>
								<option value="我的问题">我的问题</option>
							</c:if>
							<c:if test="${sessionScope.UsersfromActions.userType=='弟子' }">
								<option value="弟子区">弟子区</option>
								<option value="所有问题">所有问题</option>
								<option value="公开区">公开区</option>
								<option value="我的问题">我的问题</option>
								<option value="学员区">学员区</option>
								<option value="答疑区">答疑区</option>
							</c:if>
							<c:if test="${sessionScope.UsersfromActions.userType=='老先生'}">
								<option value="答疑区">答疑区</option>
								<option value="所有问题">所有问题</option>
								<option value="学员区">学员区</option>
								<option value="公开区">公开区</option>
								<option value="我的问题">我的问题</option>
								<option value="弟子区">弟子区</option>
							</c:if>
						</select></li>
						<!--问题分类初始化绑定，可由后台添加-->
						<li><select class="qz_type" id="QTypeName" onchange="findData(this)">
								<option value="">问题分类</option>
							<c:forEach items="${qtList }" var="qtList">
								<option value="${qtList.QTypeName }">${qtList.QTypeName }</option>
							</c:forEach>
						</select></li>
						<li><select class="deal_time" id="QTime" onchange="findData(this)">
							<option value="" >按时间</option>
							<option value="今天">今天</option>
							<option value="最近三天">最近三天</option>
							<option value="最近七天">最近七天</option>
							<option value="最近一个月">最近一个月</option>
						</select></li>
						<li><select class="state" id="state" onchange="findData(this)">
							<option value="">是否回复</option><option value="0">未回复</option>
							<option value="1">已回复</option>
						</select>
						<li><a href="javascript:void(0);" class="ask" target="_blank" onclick="checkLogin_Post(this)">发帖</a></li>
						<li style="float:right !important;margin-right:30px;">
							<input type="text" id="search_value">
							<input type="button" value="搜索" id="search" onclick="findData(this)">
						</li>
					</ul>
					<!-- </form> -->
					<table class="qz-tb1">
						<thead>
							<tr>
								<td>状态</td>
								<td>标题</td>
								<td>提问者</td>
								<td>大师</td>
								<td>最后回复</td>
								<td>回复人数</td>
								<td>人气</td>
								<td>问题分类</td>
							</tr>
						</thead>
					</table>
					<div id="showquestion">
					<table class="qz-tb1" id="q_detail">
					<c:forEach items="${qList }" var="question">
						<tr>
							<td class="icon-td" title="已回复">&#xe905;</td>
							<td><a href="${pageContext.request.contextPath }/user/pages/q_detail.jsp" title="$(this)">××××××××××××</a></td>
							<td>${question.username }<br>${question.QTime }</td>
							<td>${question.askWho }</td>
							<td>明顺<br>2014-11-28</td>
							<td>73</td>
							<td>${question.visits }</td>
							<td>${question.QTypeName }</td>
						</tr>
					</c:forEach>
						<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					</table></div>
					<div class="page-div">
						<input type="button" value="上一页" id="pre" onclick="findData($('#nowSub'))">
						<input type="hidden" id="nowSub">
						<span class="a-page">
						<%-- <a href="javascript:;" onclick="">1</a>
						<a href="javascript:;">2</a>--%></span> 
						<span>第&nbsp;<span id="now"></span>/<span id="total"></span>&nbsp;页</span>
						<input type="hidden" id="nowAdd">
						<input type="button" value="下一页" id="next" onclick="findData($('#nowAdd'))">
					</div> 
					<div class="no-data">暂无数据</div>
				</div>
			</div>
		</div>
	</div>
	<div class="popover">
		<div class="po-close" title="关闭"><div class="close-ico"></div></div>
		<div class="popover-head">大师简介</div>
		<div style="width:100%;height:172px;">
			<div class="po-pic-box"><img src="" style="width:100%;height:100%;"></div>
			<!--detail中span的text来自所点击的master-data中hidden元素的值-->
			<div class="po-detail">
				<div class="po-sub-de">
					法号：<span class="hdashi_name"></span>性别：<span class="gender"></span>
				</div>
				<div class="po-sub-de">
					现居城市：<span class="living_city"></span>出生年份：<span class="birthday"></span>
				</div>
				<div class="po-sub-de">
					手机：<span class="phone"></span>QQ：<span class="qq"></span>
				</div>
				<div class="po-sub-de">
					邮箱：<span class="mail"></span>
				</div>
				<div class="po-sub-de">
					微信：<span class="weixin"></span>
				</div>
			</div>
		</div>
		<div style="width:90%;margin-bottom:6px;text-align:left;">个人简介</div>
		<div class="po-introduce"></div><!--个人简介-->
		<div style="width：100%;text-align:center;margin-top:15px;">
			<a href="javascript:;" target="_blank" class="ask" onclick="ask_pa(this)">我要提问</a>
		</div>
	</div>
	<div class="r-box">
		<div class="r-box-top">招生传法信息&nbsp;<span class="icon-r">&#xe91a;</span></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-message"><a href="#">招生</a></div>
		<div class="r-more"><a href="${pageContext.request.contextPath }/message_findAll.action">更多</a></div>
	</div>
</body>
</html>