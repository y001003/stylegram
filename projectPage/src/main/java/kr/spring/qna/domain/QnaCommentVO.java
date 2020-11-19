package kr.spring.qna.domain;

import java.sql.Date;

public class QnaCommentVO {
	private int qr_num;//댓글번호
	private int qb_num;//게시글 번호
	private int m_num;//댓글 작성자 번호
	private String qr_content;//댓글 내용
	private Date qr_regdate;//댓글 작성일자
	
	private String m_id;
	private String m_nickname;
	private byte[] m_image;
	
	public byte[] getM_image() {
		return m_image;
	}
	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	public int getQr_num() {
		return qr_num;
	}
	public void setQr_num(int qr_num) {
		this.qr_num = qr_num;
	}
	public int getQb_num() {
		return qb_num;
	}
	public void setQb_num(int qb_num) {
		this.qb_num = qb_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
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
	
	@Override
	public String toString() {
		return "QnaCommentVO [qr_num=" + qr_num + ", qb_num=" + qb_num + ", m_num=" + m_num + ", qr_content="
				+ qr_content + ", qr_regdate=" + qr_regdate + ", m_id=" + m_id + ", m_nickname=" + m_nickname + "]";
	}
	
	
	
}
