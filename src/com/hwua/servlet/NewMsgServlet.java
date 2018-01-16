package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.entity.Message;
import com.hwua.entity.User;
import com.hwua.service.IMessageService;
import com.hwua.service.impl.MessageServiceImpl;

@WebServlet("/doNewMsg")
public class NewMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单数据
		String title = request.getParameter("title");
		String msgcontent = request.getParameter("content");
		String toUserId = request.getParameter("toUser");
		if(toUserId==null||"".equals(toUserId)){
			return;
		}
		int receiveid = Integer.parseInt(toUserId);
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		User u = (User) obj;
		int sendid = u.getId();
		//将数据封装成对象
		Message msg = new Message(sendid, title, msgcontent, receiveid);
		//2.业务处理-发送消息
		IMessageService ims = new MessageServiceImpl();
		int result = ims.sendMsg(msg);
		//3.响应结果
		if(result==1){
			response.getWriter().write("OK");
		}else{
			response.getWriter().write("ERROR");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
