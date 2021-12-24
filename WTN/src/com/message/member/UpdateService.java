package com.message.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inter.Command;
import com.message.DAO.memberDAO;
import com.message.DTO.memberDTO;

public class UpdateService implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		Connection conn = null;
		PreparedStatement psmt = null;
		HttpSession session = request.getSession();
		memberDTO dto = (memberDTO)session.getAttribute("dto");
		memberDAO dao = new memberDAO();
		String mem_id=dto.getMem_id();
		String mem_pw = request.getParameter("mem_pw");
		String mem_name = request.getParameter("mem_name");
		String mem_tel = request.getParameter("mem_tel");
		int cnt = dao.Update(mem_id, mem_pw, mem_name, mem_tel);
		if (cnt>0) {
			memberDTO update_dto = new memberDTO(mem_id, mem_name, mem_tel);
			session.setAttribute("dto", update_dto);
			response.sendRedirect("main.jsp");
			return null;
		}else {
			String nextpage = "UpdateFalse.jsp";
			return nextpage;
		}
	}
	
}
