package kr.spring.member.domain;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	// promember 항목
	private int m_num;
	@NotEmpty
	private String m_id;
	private String m_nickname;
	private int m_auth;
	
	// promember_detail 항목
	@NotEmpty
	private String m_name;
	@NotEmpty
	@Size(min=6, max=15)
	private String m_passwd;
	@NotEmpty
	private String m_postcode;
	@NotEmpty
	private String m_address;
	@NotEmpty
	private String m_detailaddress;
	@NotEmpty
	private String m_phone;
	private byte[] m_image;
	private MultipartFile upload;
	private String m_filename;
	private int m_public;
	private Date m_regdate;
	private String old_passwd;
	
	//비밀번호 인증
	public boolean isCheckedPasswd(String userPasswd) {
		if(/*m_auth > 1 &&*/ m_passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	public boolean isCheckedAuth(int userAuth) {
		if(userAuth > 1 || m_auth > 1) {
			return true;
		}
		return false;
	}
	
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_nickname() {
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	public int getM_auth() {
		return m_auth;
	}
	public void setM_auth(int m_auth) {
		this.m_auth = m_auth;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_passwd() {
		return m_passwd;
	}
	public void setM_passwd(String m_passwd) {
		this.m_passwd = m_passwd;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	public int getM_public() {
		return m_public;
	}
	public void setM_public(int m_public) {
		this.m_public = m_public;
	}
	public Date getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(Date m_regdate) {
		this.m_regdate = m_regdate;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) throws IOException{
		this.upload = upload;
		
		setM_image(upload.getBytes());
		setM_filename(upload.getOriginalFilename());
	}
	public byte[] getM_image() {
		return m_image;
	}
	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	public String getM_filename() {
		return m_filename;
	}
	public void setM_filename(String m_filename) {
		this.m_filename = m_filename;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getOld_passwd() {
		return old_passwd;
	}
	public void setOld_passwd(String old_passwd) {
		this.old_passwd = old_passwd;
	}
	public String getM_postcode() {
		return m_postcode;
	}
	public void setM_postcode(String m_postcode) {
		this.m_postcode = m_postcode;
	}
	public String getM_detailaddress() {
		return m_detailaddress;
	}
	public void setM_detailaddress(String m_detailaddress) {
		this.m_detailaddress = m_detailaddress;
	}
}