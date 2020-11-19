package kr.spring.qna.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.Nullable;

public class QnaVO {
 
	//PROQNABOARD
	private int qb_num;
	@NotEmpty
	private String qb_title;
	private int qb_usernum;
	private MultipartFile upload;
	private byte[] qb_photo;
	private String qb_filename;
	private String qb_content;
	private Date qb_regdate;
	private Date qb_modifydate;
	//PROQNAINFO
	private int qn_num;
	@Nullable
	private String qb_topinfo;
	@Nullable
	private	String qb_pantsinfo;
	@Nullable
	private	String qb_capinfo;
	@Nullable
	private	String qb_shoesinfo;
	//PROQNAREPLE
	private int qr_num;
	private	int m_num;
	private String qr_id;
	private	String qr_content;
	private	Date qr_regdate;
	//PROMEMBER
	private String m_id;
	private String m_nickname;
	//PROMEMBER_DETAIL
	private byte[] m_image;
	
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		setQb_photo(upload.getBytes());
		setQb_filename(upload.getOriginalFilename());
	}
	public int getQb_num() {
		return qb_num;
	}
	public void setQb_num(int qb_num) {
		this.qb_num = qb_num;
	}
	public String getQb_title() {
		return qb_title;
	}
	public void setQb_title(String qb_title) {
		this.qb_title = qb_title;
	}
	public int getQb_usernum() {
		return qb_usernum;
	}
	public void setQb_usernum(int qb_usernum) {
		this.qb_usernum = qb_usernum;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	
	public byte[] getQb_photo() {
		return qb_photo;
	}
	public void setQb_photo(byte[] qb_photo) {
		this.qb_photo = qb_photo;
	}
	public String getQb_filename() {
		return qb_filename;
	}
	public void setQb_filename(String qb_filename) {
		this.qb_filename = qb_filename;
	}
	public String getQb_content() {
		return qb_content;
	}
	public void setQb_content(String qb_content) {
		this.qb_content = qb_content;
	}
	public Date getQb_regdate() {
		return qb_regdate;
	}
	public void setQb_regdate(Date qb_regdate) {
		this.qb_regdate = qb_regdate;
	}
	public Date getQb_modifydate() {
		return qb_modifydate;
	}
	public void setQb_modifydate(Date qb_modifydate) {
		this.qb_modifydate = qb_modifydate;
	}
	public int getQn_num() {
		return qn_num;
	}
	public void setQn_num(int qn_num) {
		this.qn_num = qn_num;
	}
	public String getQb_topinfo() {
		return qb_topinfo;
	}
	public void setQb_topinfo(String qb_topinfo) {
		this.qb_topinfo = qb_topinfo;
	}
	public String getQb_pantsinfo() {
		return qb_pantsinfo;
	}
	public void setQb_pantsinfo(String qb_pantsinfo) {
		this.qb_pantsinfo = qb_pantsinfo;
	}
	public String getQb_capinfo() {
		return qb_capinfo;
	}
	public void setQb_capinfo(String qb_capinfo) {
		this.qb_capinfo = qb_capinfo;
	}
	public String getQb_shoesinfo() {
		return qb_shoesinfo;
	}
	public void setQb_shoesinfo(String qb_shoesinfo) {
		this.qb_shoesinfo = qb_shoesinfo;
	}
	public int getQr_num() {
		return qr_num;
	}
	public void setQr_num(int qr_num) {
		this.qr_num = qr_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getQr_id() {
		return qr_id;
	}
	public void setQr_id(String qr_id) {
		this.qr_id = qr_id;
	}
	public String getQr_content() {
		return qr_content;
	}
	public void setQr_content(String qr_content) {
		this.qr_content = qr_content;
	}
	public Date getQr_regdate() {
		return qr_regdate;
	}
	public void setQr_regdate(Date qr_regdate) {
		this.qr_regdate = qr_regdate;
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
	public byte[] getM_image() {
		return m_image;
	}
	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	@Override
	public String toString() {
		return "QnaVO [qb_num=" + qb_num + ", qb_title=" + qb_title + ", qb_usernum=" + qb_usernum + ", upload="
				+ upload + ", qb_filename=" + qb_filename + ", qb_content=" + qb_content + ", qb_regdate=" + qb_regdate
				+ ", qb_modifydate=" + qb_modifydate + ", qn_num=" + qn_num + ", qb_topinfo=" + qb_topinfo
				+ ", qr_num=" + qr_num + ", m_num=" + m_num + ", qr_id=" + qr_id + ", qr_content=" + qr_content
				+ ", qr_regdate=" + qr_regdate + "]";
	}
}	
	