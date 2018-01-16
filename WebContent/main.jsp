<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>海文 在线短信平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<style type="text/css">
	#footer_index{
		text-align: right;
	}
</style>
</head>
<body>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<%@include file="head.jspf" %>
			<!--错误信息  -->
			<div id="error"></div>
			<!--短消息列表  -->
			<div class="content messageList">
				<ul>
					<c:forEach items="${mList}" var="msg">
						<li class="${msg.state==1?'unReaded':''}">
							来自:${msg.sendUser.username}----标题:${msg.title}----时间:${msg.msg_create_date}
							<em><a href="doReadMsg?mid=${msg.id}" style="color:#ff0000">查看</a></em>
						</li>
					</c:forEach>
				</ul>
				<div id="footer_index">
					<a href="doShowMsg?page=${pager.prevPage}">上一页</a>
					<c:forEach items="${pager.groupList}" var="i">
						<c:if test="${i==pager.currentPage}">
							${i}
						</c:if>
						<c:if test="${i!=pager.currentPage}">
							<a href="doShowMsg?page=${i}">${i}</a>
						</c:if>
					</c:forEach>
					<a href="doShowMsg?page=${pager.nextPage}">下一页</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
