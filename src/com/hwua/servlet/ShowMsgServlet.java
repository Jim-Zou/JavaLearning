package com.hwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.entity.Message;
import com.hwua.entity.Pager;
import com.hwua.entity.User;
import com.hwua.service.IMessageService;
import com.hwua.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class ShowMsgServlet
 */
@WebServlet("/doShowMsg")
public class ShowMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取当前下标页
		String page = request.getParameter("page");
		int pager = 1;//初始为第一页
		if(page!=null&&!"".equals(page)){
			pager = Integer.parseInt(page);
		}
		Pager p = new Pager(pager);
		//获取当前登录用户的id
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		User u = (User) obj;
		int uid = u.getId();
		//2.通过id&pager查询当前用户消息区间集合
		IMessageService ims = new MessageServiceImpl();
		List<Message> mList = ims.showList(uid,p);
		//存入request域中
		request.setAttribute("mList", mList);
		request.setAttribute("pager", p);
		//转发至main.jsp
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
