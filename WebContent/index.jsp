<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>学士后 短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="loginTitle" class="png"></div>
	<div id="loginForm" class="userForm png">
		<form action="doLogin" method="post">
			<dl>
				<div id="error"></div>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="username" />
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input type="password" name="password" />
				</dd>
			</dl>
			<div class="buttons">
				<input class="btn-login png" type="button" name="submit" value=" " onclick="myLogin()"/>
				<a href="register.jsp"><input class="btn-reg png" type="button" name="register" value=" " /></a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function myLogin(){
			//发送一个ajax请求
			$.ajax({
				type:"post",
				url:"doLogin",
				data:$("form").serialize(),//表单数据
				async:false,//同步请求
				dataType:"text",
				success:function(msg){
					if(msg=="OK"){//登录成功
						alert("恭喜您,登录成功!");
						//注册成功后进入doShowMsg
						location.href = "doShowMsg";
					}else if(msg=="ERROR"){
						alert("登录失败,请重新登录");
					}
				},
				error:function(){
					alert("注册失败");
				}
			});
		}
	</script>
</body>
</html>

