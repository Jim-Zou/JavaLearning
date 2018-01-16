package com.hwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

@WebServlet("/doShowUsers")
public class ShowUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取当前登录用户的用户id
		Object obj = request.getSession().getAttribute("user");
		User u = (User) obj;
		int id = u.getId();
		//2.调用业务逻辑代码获取其他所有用户集合
		IUserService ius = new UserServiceImpl();
		List<User> list = ius.showOtherUsers(id);
		//3.将结果存入域中，资源跳转到newMsg.jsp
		request.setAttribute("uList", list);
		request.getRequestDispatcher("newMsg.jsp").forward(request, response);
		System.out.println(list);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
