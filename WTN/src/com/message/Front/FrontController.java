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
		// 1. 모든 요청을 하나의 Servlet로 정의하는 패턴
		// 2. 중복되는 코드나 추적, 보안을 적용할 때 하나의 Servlet에서 정의되기 때문에
		//    유지보수에 용이
		
		
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		System.out.println(path);
		String command = uri.substring(path.length()+1);
		System.out.println("요청기능 : "+command);
		
		Command com = null;
		String nextpage = null;
		
		if(command.equals("LoginCon.do")) {
			
			com = new LoginService();
			nextpage = com.execute(request, response);//업캐스팅
			
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
