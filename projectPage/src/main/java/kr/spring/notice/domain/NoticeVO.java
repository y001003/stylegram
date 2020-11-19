package kr.spring.notice.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {

	private int nb_num;
	private String nb_title;
	private String nb_content;
	private MultipartFile upload1;
	private byte[] nb_file1;
	private String nb_filename1;
	private MultipartFile upload2;
	private byte[] nb_file2;
	private String nb_filename2;
	private Date nb_regdate;
	private int m_num;
	private String m_id;
	
	public void setUpload1(MultipartFile upload1) throws IOException {
		this.upload1 = upload1;
		setNb_file1(upload1.getBytes());
		setNb_filename1(upload1.getOriginalFilename());
	}
	
	public void setUpload2(MultipartFile upload2) throws IOException {
		this.upload2 = upload2;
		setNb_file2(upload2.getBytes());
		setNb_filename2(upload2.getOriginalFilename());
	}
	
	
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getNb_num() {
		return nb_num;
	}
	public void setNb_num(int nb_num) {
		this.nb_num = nb_num;
	}
	public String getNb_title() {
		return nb_title;
	}
	public void setNb_title(String nb_title) {
		this.nb_title = nb_title;
	}
	public String getNb_content() {
		return nb_content;
	}
	public void setNb_content(String nb_content) {
		this.nb_content = nb_content;
	}
	public MultipartFile getUpload1() {
		return upload1;
	}
	public byte[] getNb_file1() {
		return nb_file1;
	}
	public void setNb_file1(byte[] nb_file1) {
		this.nb_file1 = nb_file1;
	}
	public String getNb_filename1() {
		return nb_filename1;
	}
	public void setNb_filename1(String nb_filename1) {
		this.nb_filename1 = nb_filename1;
	}
	public MultipartFile getUpload2() {
		return upload2;
	}
	public byte[] getNb_file2() {
		return nb_file2;
	}
	public void setNb_file2(byte[] nb_file2) {
		this.nb_file2 = nb_file2;
	}
	public String getNb_filename2() {
		return nb_filename2;
	}
	public void setNb_filename2(String nb_filename2) {
		this.nb_filename2 = nb_filename2;
	}
	public Date getNb_regdate() {
		return nb_regdate;
	}
	public void setNb_regdate(Date nb_regdate) {
		this.nb_regdate = nb_regdate;
	}

}
