package kr.spring.mainboard.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MainBoardVO {
	//mainBoardContent
	private int mb_num;
	@NotEmpty
	private String mb_title;
	private int m_num;
	private MultipartFile upload;
	private byte[] mb_photo;
	private String mb_filename;
	private String mb_content;
	private Date mb_regdate;
	private Date mb_modifydate;
	
	//member
	public String m_id;
	private String m_nickname;
	private byte[] m_image;
	
	//mainBoardInfo
	private String mb_topinfo;
	private String mb_pantsinfo;
	private String mb_capinfo;
	private String mb_shoesinfo;

	public int getMb_num() {
		return mb_num;
	}

	public void setMb_num(int mb_num) {
		this.mb_num = mb_num;
	}

	public String getMb_title() {
		return mb_title;
	}

	public void setMb_title(String mb_title) {
		this.mb_title = mb_title;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		
		setMb_photo(upload.getBytes());
		setMb_filename(upload.getOriginalFilename());
	}
	public byte[] getMb_photo() {
		return mb_photo;
	}

	public void setMb_photo(byte[] mb_photo) {
		this.mb_photo = mb_photo;
	}
	public String getMb_filename() {
		return mb_filename;
	}

	public void setMb_filename(String mb_filename) {
		this.mb_filename = mb_filename;
	}

	public String getMb_content() {
		return mb_content;
	}

	public void setMb_content(String mb_content) {
		this.mb_content = mb_content;
	}

	public Date getMb_regdate() {
		return mb_regdate;
	}

	public void setMb_regdate(Date mb_regdate) {
		this.mb_regdate = mb_regdate;
	}

	public Date getMb_modifydate() {
		return mb_modifydate;
	}

	public void setMb_modifydate(Date mb_modifydate) {
		this.mb_modifydate = mb_modifydate;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getMb_topinfo() {
		return mb_topinfo;
	}

	public void setMb_topinfo(String mb_topinfo) {
		this.mb_topinfo = mb_topinfo;
	}

	public String getMb_pantsinfo() {
		return mb_pantsinfo;
	}

	public void setMb_pantsinfo(String mb_pantsinfo) {
		this.mb_pantsinfo = mb_pantsinfo;
	}

	public String getMb_capinfo() {
		return mb_capinfo;
	}

	public void setMb_capinfo(String mb_capinfo) {
		this.mb_capinfo = mb_capinfo;
	}

	public String getMb_shoesinfo() {
		return mb_shoesinfo;
	}

	public void setMb_shoesinfo(String mb_shoesinfo) {
		this.mb_shoesinfo = mb_shoesinfo;
	}
	
	public String getM_nickname() {
		return m_nickname;
	}

	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	public byte[] getM_image() {
		return m_image;
	}

	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	@Override
	public String toString() {
		return "MainBoardVO [mb_num=" + mb_num + ", mb_title=" + mb_title + ", m_num=" + m_num + ", upload=" + upload
				+ ", mb_filename=" + mb_filename + ", mb_content=" + mb_content + ", mb_regdate=" + mb_regdate
				+ ", mb_modifydate=" + mb_modifydate + ", m_id=" + m_id + ", m_nickname=" + m_nickname + ", mb_topinfo="
				+ mb_topinfo + ", mb_pantsinfo=" + mb_pantsinfo + ", mb_capinfo=" + mb_capinfo + ", mb_shoesinfo="
				+ mb_shoesinfo + "]";
	}
	
}
