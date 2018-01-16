package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Message;
import com.hwua.service.IMessageService;
import com.hwua.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class ReadMsgServlet
 */
@WebServlet("/doReadMsg")
public class ReadMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取消息id
		String mid = request.getParameter("mid");
		//2.根据id读取消息记录业务处理
		IMessageService ims = new MessageServiceImpl();
		Message msg = ims.readMsg(mid);
		//3.将结果存入域中 转发至readMsg.jsp
		request.setAttribute("haha", msg);
		request.getRequestDispatcher("readMsg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
