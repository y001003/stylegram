package kr.spring.mainboard.domain;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MainPicturesVO {
	private int i_num;
	private int m_num;
	private int mb_num;
	private byte[] i_photo;
	private String i_filename;
	
	private MultipartFile[] uploadPicture;
	
	private MainPictureSubVO[] mainPictureSubVO;
	

	public MainPictureSubVO[] getMainPictureSubVO() {
		return mainPictureSubVO;
	}
	public void setMainPictureSubVO(MainPictureSubVO[] mainPictureSubVO) {
		this.mainPictureSubVO = mainPictureSubVO;
	}
	public MultipartFile[] getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(MultipartFile[] uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	public int getI_num() {
		return i_num;
	}
	public void setI_num(int i_num) {
		this.i_num = i_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getMb_num() {
		return mb_num;
	}
	public void setMb_num(int mb_num) {
		this.mb_num = mb_num;
	}

	public byte[] getI_photo() {
		return i_photo;
	}
	public void setI_photo(byte[] i_photo) {
		this.i_photo = i_photo;
	}
	public String getI_filename() {
		return i_filename;
	}
	public void setI_filename(String i_filename) {
		this.i_filename = i_filename;
	}
	@Override
	public String toString() {
		return "MainPicturesVO [i_num=" + i_num + ", m_num=" + m_num + ", mb_num=" + mb_num + ", i_filename="
				+ i_filename + ", uploadPicture=" + Arrays.toString(uploadPicture) + ", mainPictureSubVO="
				+ Arrays.toString(mainPictureSubVO) + "]";
	}
}
