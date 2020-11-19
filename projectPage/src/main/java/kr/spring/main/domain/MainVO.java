package kr.spring.main.domain;

public class MainVO {
	//mainBoard
	private int mb_num;
	private String m_id;
	private String m_nickname;
	private String mb_title;
	private String mb_content;
	private byte[] mb_photo;

	//mainBoardInfo
	private String mb_topinfo;
	private String mb_pantsinfo;
	private String mb_capinfo;
	private String mb_shoesinfo;
	
	//member
	private int m_num;
	private byte[] m_image;
	
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
	public String getMb_content() {
		return mb_content;
	}
	public void setMb_content(String mb_content) {
		this.mb_content = mb_content;
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
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public byte[] getM_image() {
		return m_image;
	}
	public void setM_image(byte[] m_image) {
		this.m_image = m_image;
	}
	public byte[] getMb_photo() {
		return mb_photo;
	}
	public void setMb_photo(byte[] mb_photo) {
		this.mb_photo = mb_photo;
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
}