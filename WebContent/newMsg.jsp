<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>海文 在线短信平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<form action="#" method="post">
		<div id="main">
			<div class="mainbox">
				<%@include file="head.jspf" %>
				<div class="content">
					<div class="message">

						<div class="tmenu">
							<ul class="clearfix">
								<li>发送给： <select name="toUser">
										<c:forEach items="${uList}" var="temp">
											<option value="${temp.id}">
												${temp.username}
											</option>
										</c:forEach>
								</select>
								</li>
								<li>标题：<input type="text" name="title" id="title" /></li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content" id="content"></textarea>
							<div class="send">
								<input type="button" name="submit" value=" " onclick="myNewMsg()"/>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		function myNewMsg(){
			//发送一个ajax请求
			$.ajax({
				type:"post",
				url:"doNewMsg",
				data:$("form").serialize(),//表单数据
				async:false,//同步请求
				dataType:"text",
				success:function(msg){
					if(msg=="OK"){//注册成功
						alert("消息发送成功!");
						//location.href = "doShowUsers";//增加服务器压力，不推荐
						$("form")[0].reset();//使用js DOM方法重置表单
					}else if(msg=="ERROR"){
						alert("消息发送失败");
					}
				},
				error:function(){
					alert("消息发送失败");
				}
			});
		}
	</script>
</body>
</html>
