package kr.spring.flea.domain;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;


public class FleaPicturesVO {
	private int i_num;
	private int m_num;
	private int fb_num;
	private byte[] i_photo;
	private String i_filename;
	
	private MultipartFile[] uploadPicture;
	
	private FleaPictureSubVO[] fleaPictureSubVO;
	

	public FleaPictureSubVO[] getFleaPictureSubVO() {
		return fleaPictureSubVO;
	}
	public void setFleaPictureSubVO(FleaPictureSubVO[] fleaPictureSubVO) {
		this.fleaPictureSubVO = fleaPictureSubVO;
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
	public int getFb_num() {
		return fb_num;
	}
	public void setFb_num(int fb_num) {
		this.fb_num = fb_num;
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
		return "FleaPicturesVO [i_num=" + i_num + ", m_num=" + m_num + ", fb_num=" + fb_num + ", i_filename="
				+ i_filename + ", uploadPicture=" + Arrays.toString(uploadPicture) + ", fleaPictureSubVO="
				+ Arrays.toString(fleaPictureSubVO) + "]";
	}
}
