<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>老先生答疑</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/user/css/index.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/user/js/index.js"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="head">
		<c:choose>
			<c:when test="${requestScope.user==null }">
			<div class="unlogin">
				<span><a href="${pageContext.request.contextPath }/user/pages/login.jsp">登录</a></span>&nbsp;&nbsp;|&nbsp;
				<span><a href="${pageContext.request.contextPath }/user/pages/register.jsp">注册</a></span>
			</div>
			</c:when>
			<c:otherwise>
			<div class="logined">
				Hi,<span id="username">${user.username }</span>
				<span><a href="${pageContext.request.contextPath }/edit.jsp">修改个人资料</a></span>&nbsp;&nbsp;|&nbsp;
				<span><a href="#">退出</a></span>
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
									<a href="${pageContext.request.contextPath }/user/pages/ask_question.jsp" target="_blank" class="ask">我要提问</a>
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
					<ul class="qz-list-ul">
						<li><select class="qz_list"><option>问题列表区</option></select></li>
						<!--问题分类初始化绑定，可由后台添加-->
						<li><select class="qz_type"><option>问题分类</option></select></li>
						<li><a href="${pageContext.request.contextPath }/user/pages/post.jsp" class="ask" target="_blank">发帖</a></li>
						<li style="float:right !important;margin-right:30px;">
							<input type="text" id="search_value">
							<input type="button" value="搜索" id="search">
						</li>
					</ul>
					
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
					<table class="qz-tb1" id="q_detail">
						<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
						<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					</table>
					<div class="page-div">
						<input type="button" value="上一页" id="pre">
						<a href="javascript:;">1</a>
						<a href="javascript:;">2</a>
						<span>第&nbsp;<span id="now">1</span>/<span id="total">2</span>&nbsp;页</span>
						<input type="button" value="下一页" id="next">
					</div>
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
			<a href="${pageContext.request.contextPath }/user/pages/ask_question.jsp" target="_blank" class="ask">我要提问</a>
		</div>
	</div>
</body>
</html>