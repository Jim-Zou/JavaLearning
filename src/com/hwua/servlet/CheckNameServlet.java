package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

@WebServlet("/doCheckName")
public class CheckNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求数据
		String name = request.getParameter("name");
		//2.调用业务逻辑验证用户名是否存在
		IUserService ius = new UserServiceImpl();
		User user = ius.checkName(name);
		//3.响应结果
		if(user==null){//用户名不存在
			response.getWriter().write("ok");
		}else{//用户名存在
			response.getWriter().write("error");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
