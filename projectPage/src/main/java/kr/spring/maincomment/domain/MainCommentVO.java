package kr.spring.maincomment.domain;

import java.sql.Date;

public class MainCommentVO {
	private int mr_num;//댓글번호
	private int mb_num;//게시글 번호
	private int m_num;//댓글 작성자 번호
	private String mr_content;//댓글 내용
	private Date mr_regdate;//댓글 작성일자
	
	private String m_id;
	private String m_nickname;
	private byte[] m_image;
	

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
	public int getMr_num() {
		return mr_num;
	}
	public void setMr_num(int mr_num) {
		this.mr_num = mr_num;
	}
	public int getMb_num() {
		return mb_num;
	}
	public void setMb_num(int mb_num) {
		this.mb_num = mb_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getMr_content() {
		return mr_content;
	}
	public void setMr_content(String mr_content) {
		this.mr_content = mr_content;
	}
	public Date getMr_regdate() {
		return mr_regdate;
	}
	public void setMr_regdate(Date mr_regdate) {
		this.mr_regdate = mr_regdate;
	}
	public byte[] getM_image() {
		return m_image;
	}
	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	@Override
	public String toString() {
		return "MainCommentVO [mr_num=" + mr_num + ", mb_num=" + mb_num + ", m_num=" + m_num + ", mr_content="
				+ mr_content + ", mr_regdate=" + mr_regdate + ", m_id=" + m_id + ", m_nickname=" + m_nickname + "]";
	}
	

	
	
	
	
}
