package com.message.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inter.Command;
import com.message.DAO.memberDAO;
import com.message.DTO.memberDTO;

public class LoginService implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		String mem_pw = request.getParameter("mem_pw");
		
		memberDAO dao = new memberDAO();
		memberDTO dto1 = new memberDTO(mem_id, mem_pw);
		memberDTO dto = dao.Login(dto1);
		String nextpage = "";
		if(dto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("dto", dto);
			nextpage = "main.jsp";
		}else {
			nextpage = "LoginFalse.jsp";
		}
		return nextpage;
	}
}
