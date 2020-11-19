package kr.spring.flea.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.Nullable;

public class FleaVO {
	private int fb_num;
	@NotEmpty
	private String fb_title;
	private int fb_usernum;
	private MultipartFile upload;
	private byte[] fb_photo;
	private String fb_filename;
	private int fb_price;
	private String fb_content;
	private Date fb_regdate;
	private Date fb_modifydate;
	@Nullable
	private String fb_topcheck;
	@Nullable
	private String fb_bottomcheck;
	@Nullable
	private String fb_hatcheck;
	@Nullable
	private String fb_shoescheck;
	private int fb_topchecknum;
	private int fb_bottomchecknum;
	private int fb_hatchecknum;
	private int fb_shoeschecknum;
	
	private String m_id;
	private String m_nickname;
	private byte[] m_image;
	
	public int getFb_topchecknum() {
		return fb_topchecknum;
	}
	public void setFb_topchecknum(int fb_topchecknum) {
		this.fb_topchecknum = fb_topchecknum;
	}
	public int getFb_bottomchecknum() {
		return fb_bottomchecknum;
	}
	public void setFb_bottomchecknum(int fb_bottomchecknum) {
		this.fb_bottomchecknum = fb_bottomchecknum;
	}
	public int getFb_hatchecknum() {
		return fb_hatchecknum;
	}
	public void setFb_hatchecknum(int fb_hatchecknum) {
		this.fb_hatchecknum = fb_hatchecknum;
	}
	public int getFb_shoeschecknum() {
		return fb_shoeschecknum;
	}
	public void setFb_shoeschecknum(int fb_shoeschecknum) {
		this.fb_shoeschecknum = fb_shoeschecknum;
	}
	public String getFb_topcheck() {
		return fb_topcheck;
	}
	public void setFb_topcheck(String fb_topcheck) {
		this.fb_topcheck = fb_topcheck;
	}
	public String getFb_bottomcheck() {
		return fb_bottomcheck;
	}
	public void setFb_bottomcheck(String fb_bottomcheck) {
		this.fb_bottomcheck = fb_bottomcheck;
	}
	public String getFb_hatcheck() {
		return fb_hatcheck;
	}
	public void setFb_hatcheck(String fb_hatcheck) {
		this.fb_hatcheck = fb_hatcheck;
	}
	public String getFb_shoescheck() {
		return fb_shoescheck;
	}
	public void setFb_shoescheck(String fb_shoescheck) {
		this.fb_shoescheck = fb_shoescheck;
	}
	public int getFb_num() {
		return fb_num;
	}
	public void setFb_num(int fb_num) {
		this.fb_num = fb_num;
	}
	public String getFb_title() {
		return fb_title;
	}
	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}
	public int getFb_usernum() {
		return fb_usernum;
	}
	public void setFb_usernum(int fb_usernum) {
		this.fb_usernum = fb_usernum;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		//byte[]로 변환
		setFb_photo(upload.getBytes());
		//파일명 구하기
		setFb_filename(upload.getOriginalFilename());
	}
	public byte[] getFb_photo() {
		return fb_photo;
	}
	public void setFb_photo(byte[] fb_photo) {
		this.fb_photo = fb_photo;
	}
	public String getFb_filename() {
		return fb_filename;
	}
	public void setFb_filename(String fb_filename) {
		this.fb_filename = fb_filename;
	}
	public int getFb_price() {
		return fb_price;
	}
	public void setFb_price(int fb_price) {
		this.fb_price = fb_price;
	}
	public String getFb_content() {
		return fb_content;
	}
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	public Date getFb_regdate() {
		return fb_regdate;
	}
	public void setFb_regdate(Date fb_regdate) {
		this.fb_regdate = fb_regdate;
	}
	public Date getFb_modifydate() {
		return fb_modifydate;
	}
	public void setFb_modifydate(Date fb_modifydate) {
		this.fb_modifydate = fb_modifydate;
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
		return "FleaVO [fb_num=" + fb_num + ", fb_title=" + fb_title + ", fb_usernum=" + fb_usernum + ", upload="
				+ upload + ", fb_filename=" + fb_filename + ", fb_price=" + fb_price + ", fb_content=" + fb_content
				+ ", fb_regdate=" + fb_regdate + ", fb_modifydate=" + fb_modifydate + ", fb_topcheck=" + fb_topcheck
				+ ", fb_bottomcheck=" + fb_bottomcheck + ", fb_hatcheck=" + fb_hatcheck + ", fb_shoescheck="
				+ fb_shoescheck + ", fb_topchecknum=" + fb_topchecknum + ", fb_bottomchecknum=" + fb_bottomchecknum
				+ ", fb_hatchecknum=" + fb_hatchecknum + ", fb_shoeschecknum=" + fb_shoeschecknum + "]";
	}
	
}
