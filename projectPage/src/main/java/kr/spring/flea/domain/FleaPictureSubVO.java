package kr.spring.flea.domain;

import org.springframework.web.multipart.MultipartFile;

public class FleaPictureSubVO {
	private int i_num;
	
	private MultipartFile uploadPicture;

	public int getI_num() {
		return i_num;
	}

	public void setI_num(int i_num) {
		this.i_num = i_num;
	}

	public MultipartFile getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(MultipartFile uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	@Override
	public String toString() {
		return "MainPictureSubVO [i_num=" + i_num + ", uploadPicture=" + uploadPicture + "]";
	}
}
