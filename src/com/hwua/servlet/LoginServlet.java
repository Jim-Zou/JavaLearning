package com.hwua.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取请求携带的数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//将数据封装成对象
		User user = new User(username, password);
		//2.业务处理
		IUserService ius = new UserServiceImpl();
		User result = ius.login(user);
		//3.根据结果进一步操作
		if(result==null){//登录失败
			resp.getWriter().write("ERROR");
		}else{//登录成功
			//将用户对象存放在session域中
			req.getSession().setAttribute("user",result);
			resp.getWriter().write("OK");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}







