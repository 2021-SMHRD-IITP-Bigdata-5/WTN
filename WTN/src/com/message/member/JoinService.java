package com.message.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inter.Command;
import com.message.DAO.memberDAO;
import com.message.DTO.memberDTO;

public class JoinService implements Command{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		String mem_pw = request.getParameter("mem_pw");
		String mem_name = request.getParameter("mem_name");
		String mem_tel = request.getParameter("mem_tel");
		
		memberDAO dao = new memberDAO();
		memberDTO dto = new memberDTO(mem_id, mem_pw, mem_name, mem_tel);
		int cnt = dao.Join(dto);
		if (cnt>0) {
			request.setAttribute("dto",dto);			
		    RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
			dis.forward(request, response);
			
			return null;
		    
		}else {
			String nextpage = "JoinFalse.jsp";
			
			return nextpage;
		}
	}
}
