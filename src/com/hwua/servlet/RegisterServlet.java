package com.hwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取请求携带的数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		//将数据封装成对象
		User user = new User(username, password, email);
		
		//2.业务逻辑处理
		IUserService ius = new UserServiceImpl();
		int result = ius.register(user);
		//3.根据结果进一步操作
		if(result == 1){
			/*resp.sendRedirect("index.jsp");*/
			resp.getWriter().write("OK");
		}else if(result == -1){
			resp.getWriter().write("ERROR_FOR_NAME");
		}else{
			resp.getWriter().write("ERROR");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}







