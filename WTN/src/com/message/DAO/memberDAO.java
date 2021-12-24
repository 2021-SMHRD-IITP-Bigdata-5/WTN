package com.message.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.message.DTO.memberDTO;

public class memberDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	int cnt = 0;
	memberDTO dto = null;
	private boolean check;
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스파일 로딩완료");
			
			// 3. DB에서 사용하는 id/pw를 인증
			String url ="jdbc:oracle:thin:@127.0.0.1:1521";
			String dbid = "hr";
			String dbpw = "hr";
			conn = DriverManager.getConnection(url,dbid,dbpw);
			//-----------------------DB연결
			if(conn!=null) {
				System.out.println("연결성공");
			}else {
				System.out.println("연결실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null){
				rs.close();
			}if(psmt!=null) {
				psmt.close();
			}if(conn!=null) {
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int Join(memberDTO dto) {
		
		try {
				getConn();
			String sql = "insert into tbl_member values(?, ?, ?, ?,sysdate,'N')";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMem_id());
			psmt.setString(2, dto.getMem_pw());
			psmt.setString(3, dto.getMem_name());
			psmt.setString(4, dto.getMem_tel());
			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	public int Update(String mem_id, String mem_pw, String mem_name, String mem_tel) {

		try {
			getConn();
			
			String sql = "Update tbl_member set mem_pw= ?, mem_name=?, mem_tel = ? where mem_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem_pw);
			psmt.setString(2, mem_name);
			psmt.setString(3, mem_tel);
			psmt.setString(4, mem_id); 
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	public memberDTO Login(memberDTO dto1) {
		
		try {
			getConn();
			String sql = "select * from tbl_member where mem_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto1.getMem_id());
			rs = psmt.executeQuery();
			
			if(rs.next()==true) {
			String getmem_pw = rs.getString(2);
			String getmem_name = rs.getString(3);
			String getmem_tel = rs.getString(4);
				if(dto1.getMem_pw().equals(getmem_pw)) {
					dto =new memberDTO(dto1.getMem_id(),getmem_name,getmem_tel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	
	public boolean check(String mem_id) {
		try {
			getConn();
			String sql = " select *from tbl_member where mem_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,mem_id);
			rs = psmt.executeQuery();
			check = rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		} 
		return check;
	}
	public ArrayList<memberDTO> selectMember() {
		ArrayList<memberDTO> arr = new ArrayList<memberDTO>();
	try {
		getConn();
		
		String sql = "select * from tbl_member";
		psmt = conn.prepareStatement(sql);
		rs =  psmt.executeQuery();

		while (rs.next() == true) {
			
			String mem_id = rs.getString(1);
			String mem_name = rs.getString(3);
			String mem_tel = rs.getString(4);
			dto = new memberDTO(mem_id, mem_name, mem_tel);
			arr.add(dto);
		}
		

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return arr;
	}
	public ArrayList<memberDTO> searchMember(String mem_id) {
		ArrayList<memberDTO> arr = new ArrayList<memberDTO>();
		try {
			getConn();
			
			String sql = "select * from tbl_member where mem_id like ?";
			psmt = conn.prepareStatement(sql);
			rs =  psmt.executeQuery();
			psmt.setString(1, "%"+ mem_id +"%");
			while (rs.next() == true) {
				
				String mem_mid = rs.getString(1);
				String mem_name = rs.getString(3);
				String mem_tel = rs.getString(4);
				dto = new memberDTO(mem_mid, mem_name, mem_tel);
				arr.add(dto);
			}

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return arr;
	}
	
	
	
}
