package com.message.DTO;

public class memberDTO {
	
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_tel;
	private String admin_yesno;
	
	public memberDTO(String mem_id, String mem_pw, String mem_name, String mem_tel) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
	}
	public memberDTO(String mem_id, String mem_pw, String mem_name, String mem_tel,String admin_yesno) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.admin_yesno = admin_yesno;
	}
	

	public memberDTO(String mem_id, String mem_name, String mem_tel) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
	}
	
	


	public memberDTO(String mem_id, String mem_pw) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}


	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getAdmin_yesno() {
		return admin_yesno;
	}

	public void setAdmin_yesno(String admin_yesno) {
		this.admin_yesno = admin_yesno;
	}
	
}
