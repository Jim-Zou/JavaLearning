<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>读短消息</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />

</head>
<body>
	<%-- <%
		Object obj = request.getAttribute("haha");
		pageContext.setAttribute("msg", obj);
	%> --%>
	<c:set value="${requestScope.haha}" var="msg" scope="page"></c:set>
	<div id="main">
		<div class="mainbox">
			<div class="title readMessage png"></div>
			<%@include file="head.jspf" %>
			<div class="content">
				<div class="message">
					<div class="tmenu">
						<ul class="clearfix">
							<li>题目：${msg.title}</li>
							<li>来自：${msg.sendUser.username}</li>
							<li>时间：${msg.msg_create_date}</li>
						</ul>
					</div>
					<div class="view">
						<p>${msg.msgcontent}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
