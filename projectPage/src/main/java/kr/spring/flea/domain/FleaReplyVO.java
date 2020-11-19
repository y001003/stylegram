package kr.spring.flea.domain;

import java.sql.Date;

public class FleaReplyVO {
	private int fr_num;//댓글 번호
	private int fb_num;//게시글 번호
	private int m_num;//댓글 작성자 번호
	private String fr_content;//댓글 내용
	private Date fr_regdate;//댓글 작성일
	
	private String m_id;
	private String m_nickname;
	private byte[] m_image;
	
	public int getFr_num() {
		return fr_num;
	}
	public void setFr_num(int fr_num) {
		this.fr_num = fr_num;
	}
	public int getFb_num() {
		return fb_num;
	}
	public void setFb_num(int fb_num) {
		this.fb_num = fb_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getFr_content() {
		return fr_content;
	}
	public void setFr_content(String fr_content) {
		this.fr_content = fr_content;
	}
	public Date getFr_regdate() {
		return fr_regdate;
	}
	public void setFr_regdate(Date fr_regdate) {
		this.fr_regdate = fr_regdate;
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
	
}
