<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>海文 在线短信平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<style type="text/css">
	#error{
		color:#ff0000;
	}
</style>
</head>

<body>
	<div id="regTitle" class="png"></div>
	<div id="regForm" class="userForm png">

		<form action="doRegister" method="post">
			<dl>
				<div id="error"></div>
				<dt>用 户 名：</dt>
				<dd>
					<input id="ip_1" type="text" name="username" onblur="checkName(this)"/>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input type="password" name="password" />
				</dd>
				<dt>确认密码：</dt>
				<dd>
					<input type="password" name="affirm" />
				</dd>
				<dt>邮 箱：</dt>
				<dd>
					<input type="text" name="email" />
				</dd>
			</dl>
			<div class="buttons">
				<input class="btn-reg png" type="button" name="register" value=" " onclick="myFormPost()" />
				<!-- <input class="btn-reg png" type="submit" name="register" value=" " onmousedown="myFormPost()" /> -->
				<input class="btn-reset png" type="reset" name="reset" value=" " />
			</div>
			<div class="goback">
				<a href="index.jsp" class="png">返回登录页</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var flag = false;//用户名是否存在
		/*ajax验证用户名*/
		function checkName(element){
			$.ajax({
				type:"get",
				url:"doCheckName?name="+$(element).val(),
				success:function(msg){
					if(msg=="ok"){
						$("#error").text("用户名可以使用");
						flag = true;
					}else if(msg=="error"){
						$("#error").text("用户名已存在");
						flag = false;
					}
				}
			});
		}
		/*注册*/
		function myFormPost(){
			if(flag){
				//发送一个ajax请求
				$.ajax({
					type:"post",
					url:"doRegister",
					data:$("form").serialize(),//表单数据
					async:false,//同步请求
					dataType:"text",
					success:function(msg){
						if(msg=="OK"){//注册成功
							alert("恭喜您,注册成功!");
							//注册成功后进入登录页面
							location.href = "index.jsp";
						}else if(msg=="ERROR"){
							alert("注册失败,请重新注册");
						}
					},
					error:function(){
						alert("注册失败");
					}
				});
			}else{
				alert("数据有误，请检查。");
			}
		}
		/* function myFormPost(){
			//对于属性修改
			//$().attr();//适用于自定义的属性
			//$().prop();//适用于标签原有的属性
			if(!flag){
				alert("数据有误，请检查。");
				return false;
			}
		} */
	</script>
</body>
</html>











