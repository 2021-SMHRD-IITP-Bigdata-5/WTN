package com.message.Front;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.inter.Command;
import com.message.DAO.memberDAO;
import com.message.DTO.memberDTO;
import com.message.member.JoinService;
import com.message.member.LoginService;
import com.message.member.LogoutService;
import com.message.member.UpdateService;

@WebServlet("*.do") // avtion -> a.do, b.do
public class FrontController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// FrontController Pattern
		// 1. ��� ��û�� �ϳ��� Servlet�� �����ϴ� ����
		// 2. �ߺ��Ǵ� �ڵ峪 ����, ������ ������ �� �ϳ��� Servlet���� ���ǵǱ� ������
		//    ���������� ����
		
		
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		System.out.println(path);
		String command = uri.substring(path.length()+1);
		System.out.println("��û��� : "+command);
		
		Command com = null;
		String nextpage = null;
		
		if(command.equals("LoginCon.do")) {
			
			com = new LoginService();
			nextpage = com.execute(request, response);//��ĳ����
			
		}else if(command.equals("JoinCon.do")) {
			com = new JoinService();
			nextpage = com.execute(request, response);

		}else if(command.equals("LogoutCon.do")) {
			
			com = new LogoutService();
			nextpage = com.execute(request, response);
			
			
		}else if(command.equals("UpdateCon.do")){
		
			com = new UpdateService();
			nextpage = com.execute(request, response);
			
			
		}else if(command.equals("check.do")) {
			String mem_id = request.getParameter("mem_id");
			PrintWriter out = response.getWriter();
			
			memberDAO dao = new memberDAO();
			boolean yn = dao.check(mem_id);
			
			out.print(yn);
		}
		if(nextpage != null) {
			
			response.sendRedirect(nextpage);
		}
	}

}
